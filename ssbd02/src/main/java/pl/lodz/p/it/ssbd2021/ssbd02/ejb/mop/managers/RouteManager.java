package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers;

import org.apache.commons.lang3.tuple.Pair;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.AbstractManager;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades.interfaces.AccountMopFacadeLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades.interfaces.CruiseFacadeLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades.interfaces.RouteFacadeLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades.interfaces.SeaportFacadeLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers.interfaces.RouteManagerLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cruise;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Route;
import pl.lodz.p.it.ssbd2021.ssbd02.exceptions.CommonExceptions;
import pl.lodz.p.it.ssbd2021.ssbd02.exceptions.mop.RouteExceptions;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.interceptors.TrackerInterceptor;

import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * Manager tras
 *
 * @author Wojciech Sowa
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed({"DEFINITELY_NOT_A_REAL_ROLE"})
@Interceptors(TrackerInterceptor.class)
public class RouteManager extends AbstractManager implements RouteManagerLocal, SessionSynchronization {

    @Inject
    private RouteFacadeLocal routeFacadeLocal;

    @Inject
    private CruiseFacadeLocal cruiseFacadeLocal;

    @Inject
    private SeaportFacadeLocal seaportFacadeLocal;

    @Inject
    private AccountMopFacadeLocal accountMopFacadeLocal;

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public List<Route> getAllRoutes() {
        return routeFacadeLocal.findAll();
    }

    @Override
    @RolesAllowed({"EMPLOYEE", "CLIENT"})
    public Route getRouteByCode(String code) {
        return routeFacadeLocal.findByCode(code);
    }

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public Pair<Route, List<Cruise>> getRouteAndCruisesByRouteCode(String code) {
        Route route = routeFacadeLocal.findByCode(code);
        List<Cruise> cruiseList = cruiseFacadeLocal.findAllByRoute(route);

        return Pair.of(route, cruiseList);
    }

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public void createRoute(Route route, String startCode, String destCode, String createdBy) {
        route.setStart(seaportFacadeLocal.findByCode(startCode));
        route.setDestination(seaportFacadeLocal.findByCode(destCode));
        route.setCreationDate(Timestamp.from(Instant.now()));
        route.setCreatedBy(accountMopFacadeLocal.findByLogin(createdBy));
        route.setVersion(0L);

        routeFacadeLocal.create(route);

        logger.info("The user with login {} has created route from {} to {}",
                createdBy, startCode, destCode);
    }

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public void removeRoute(String code, String login) {
        try {
            Route route = routeFacadeLocal.findByCode(code);
            routeFacadeLocal.remove(route);
            logger.info("The user with login {} has removed the route from {} to {}",
                    login, route.getStart().getCity(), route.getDestination().getCity());
        } catch (CommonExceptions ex) {
            if (ex.getResponse().getStatus() == Response.Status.BAD_REQUEST.getStatusCode()
                    && ex.getResponse().getEntity() == CommonExceptions.ERROR_CONSTRAINT_VIOLATION) {
                throw RouteExceptions.createConflictException(RouteExceptions.ERROR_ROUTE_USED_BY_CRUISE);
            } else {
                throw ex;
            }
        }

    }
}
