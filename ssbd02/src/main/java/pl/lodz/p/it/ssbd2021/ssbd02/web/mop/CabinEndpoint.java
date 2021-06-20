package pl.lodz.p.it.ssbd2021.ssbd02.web.mop;

import pl.lodz.p.it.ssbd2021.ssbd02.dto.mop.CabinDetailsDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.mop.CabinGeneralDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers.interfaces.CabinManagerLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers.interfaces.CabinTypeManagerLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cabin;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.CabinType;
import pl.lodz.p.it.ssbd2021.ssbd02.exceptions.CommonExceptions;
import pl.lodz.p.it.ssbd2021.ssbd02.exceptions.GeneralException;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.mappers.CabinMapper;
import pl.lodz.p.it.ssbd2021.ssbd02.utils.signing.DTOIdentitySignerVerifier;

import javax.annotation.security.RolesAllowed;
import javax.ejb.AccessLocalException;
import javax.ejb.EJBAccessException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa ziarna CDI o zasięgu żądania.
 * Zawiera metody obsługujące żądania związane z rejsami.
 *
 * @author Kacper Świercz
 */
@RequestScoped
@Path("cabins")
@RolesAllowed({"DEFINITELY_NOT_A_REAL_ROLE"})
public class CabinEndpoint {

    @Inject
    private CabinManagerLocal cabinManager;
    @Inject
    private CabinTypeManagerLocal cabinTypeManager;

    /**
     * Metoda dodająca nową kajutę.
     *
     * @param cabinDTO        Tworzona kajuta
     * @param securityContext Interfejs wstrzykiwany w celu pozyskania tożsamości aktualnie uwierzytelnionego użytkownika
     * @param ferryName       Nazwa promu, do którego zostanie przypisana kajuta
     * @return Kod 202 w przypadku poprawnego dodania
     */
    @POST
    @Path("{ferryName}/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed({"EMPLOYEE"})
    public Response addCabin(@Valid CabinDetailsDTO cabinDTO, @Context SecurityContext securityContext, @PathParam("ferryName") String ferryName) {
        if (cabinDTO.getCapacity() == null || cabinDTO.getCabinType() == null || cabinDTO.getNumber() == null) {
            throw CommonExceptions.createConstraintViolationException();
        }
        try {
            cabinManager.createCabin(
                    CabinMapper.createEntityFromCabinDetailsDTO(cabinDTO, cabinTypeManager.getCabinTypeByName(cabinDTO.getCabinType())),
                    securityContext.getUserPrincipal().getName(),
                    ferryName);
            return Response.accepted()
                    .build();
        } catch (GeneralException generalException) {
            throw generalException;
        } catch (EJBAccessException | AccessLocalException accessExcept) {
            throw CommonExceptions.createForbiddenException();
        } catch (Exception e) {
            throw CommonExceptions.createUnknownException();
        }
    }

    /**
     * Metoda udostępniająca szczegółowe informacje dotyczące kajuty o podanym numerze i znajdującej się na podanym promie.
     *
     * @param ferryName   Nazwa promu, na którym znajduje się kajuta
     * @param cabinNumber Numer wyszukiwanej kajuty
     * @return Szczegółowe informacje o kajucie
     */
    @GET
    @Path("details/{ferry}/{number}")
    @RolesAllowed({"EMPLOYEE"})
    public Response getCabin(@PathParam("ferry") String ferryName, @PathParam("number") String cabinNumber) {
        try {
            CabinDetailsDTO cabinDetailsDTO = CabinMapper
                    .createCabinDetailsDTOFromEntity(cabinManager.getCabinByFerryAndNumber(ferryName, cabinNumber));

            return Response.ok()
                    .entity(cabinDetailsDTO)
                    .tag(DTOIdentitySignerVerifier.calculateDTOSignature(cabinDetailsDTO))
                    .build();
        } catch (GeneralException generalException) {
            throw generalException;
        } catch (EJBAccessException | AccessLocalException accessExcept) {
            throw CommonExceptions.createForbiddenException();
        } catch (Exception e) {
            throw CommonExceptions.createUnknownException();
        }
    }

    @GET
    @Path("ferry/{name}")
    @RolesAllowed({"CLIENT", "EMPLOYEE"})
    public Response getCabinsByFerry(@PathParam("name") String name) {
        return null;
    }

    /**
     * Metoda udostępniająca listę ogólnych informacji o wolnych kajutach dla danego rejsu
     *
     * @param cruiseNumber Rejs, dla którego wyszukiwane są kajuty
     * @return Lista ogólnych informacji o kajutach
     */
    @GET
    @Path("cruise/free/{number}")
    @RolesAllowed({"CLIENT"})
    public Response getFreeCabinsOnCruise(@PathParam("number") String cruiseNumber){
        try {
            List<CabinGeneralDTO> cabinGeneralDTOList = cabinManager.getFreeCabinsOnCruise(cruiseNumber).stream()
                    .map(CabinMapper::createCabinGeneralDTOFromEntity).collect(Collectors.toList());

            return Response.ok()
                    .entity(cabinGeneralDTOList)
                    .build();
        } catch (GeneralException generalException) {
            throw generalException;
        } catch (EJBAccessException | AccessLocalException accessExcept) {
            throw CommonExceptions.createForbiddenException();
        } catch (Exception e) {
            throw CommonExceptions.createUnknownException();
        }
    }

    /**
     * Metoda usuwająca kajutę.
     *
     * @param number          Numer identyfikujący kajutę, która będzie usuwana
     * @param securityContext Interfejs wstrzykiwany w celu pozyskania tożsamości aktualnie uwierzytelnionego użytkownika
     * @return Kod 200 w przypadku udanego usunięcia kajuty
     */
    @DELETE
    @Path("remove/{number}")
    @RolesAllowed({"EMPLOYEE"})
    public Response removeCabin(@PathParam("number") String number, @Context SecurityContext securityContext) {
        if (number == null || number.isBlank() || !number.matches("[A-Z][0-9]{3}")) {
            throw CommonExceptions.createConstraintViolationException();
        }

        try {
            cabinManager.removeCabin(number, securityContext.getUserPrincipal().getName());
            return Response.ok()
                    .build();
        } catch (GeneralException generalException) {
            throw generalException;
        } catch (EJBAccessException | AccessLocalException accessExcept) {
            throw CommonExceptions.createForbiddenException();
        } catch (Exception e) {
            throw CommonExceptions.createUnknownException();
        }
    }

    @PUT
    @Path("update/{ferry}")
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed({"EMPLOYEE"})
    public Response updateCabin(@Valid CabinDetailsDTO cabinDTO, @PathParam("ferry") String ferryName,
                                @Context SecurityContext securityContext, @HeaderParam("If-Match") @NotNull @NotEmpty String eTag) {

        if (cabinDTO.getNumber() == null || cabinDTO.getVersion() == null) {
            throw CommonExceptions.createPreconditionFailedException();
        }
        if (!DTOIdentitySignerVerifier.verifyDTOIntegrity(eTag, cabinDTO)) {
            throw CommonExceptions.createPreconditionFailedException();
        }
        try {
            CabinType cabinType = cabinTypeManager.getCabinTypeByName(cabinDTO.getCabinType());
            cabinManager.updateCabin(CabinMapper.createEntityFromCabinDetailsDTO(cabinDTO, cabinType),
                    securityContext.getUserPrincipal().getName(), ferryName);
            return Response.ok()
                    .build();
        } catch (GeneralException generalException) {
            throw generalException;
        } catch (EJBAccessException | AccessLocalException accessExcept) {
            throw CommonExceptions.createForbiddenException();
        } catch (Exception e) {
            throw CommonExceptions.createUnknownException();
        }
    }
}
