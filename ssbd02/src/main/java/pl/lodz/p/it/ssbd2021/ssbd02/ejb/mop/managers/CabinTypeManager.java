package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers;

import pl.lodz.p.it.ssbd2021.ssbd02.ejb.AbstractManager;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers.interfaces.CabinTypeManagerLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.CabinType;

import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Manager typów kajut
 *
 * @author Wojciech Sowa
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@RolesAllowed({"DEFINITELY_NOT_A_REAL_ROLE"})
public class CabinTypeManager extends AbstractManager implements CabinTypeManagerLocal, SessionSynchronization {

    @Override
    @RolesAllowed({"EMPLOYEE"})
    public List<CabinType> getAllCabinTypes() {
        return null;
    }
}
