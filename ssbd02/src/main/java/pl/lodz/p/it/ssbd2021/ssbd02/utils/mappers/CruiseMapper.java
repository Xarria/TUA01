package pl.lodz.p.it.ssbd2021.ssbd02.utils.mappers;

import pl.lodz.p.it.ssbd2021.ssbd02.dto.mop.CruiseDetailsDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.mop.CruiseGeneralDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cruise;

/**
 * Klasa mapująca obiekty rejsów pomiędzy encjami a DTO
 *
 * @author Wojciech Sowa
 */
public class CruiseMapper {

    /**
     * Metoda mapująca obiekt typu {@link Cruise} na obiekt typu {@link CruiseGeneralDTO}.
     *
     * @param cruise Obiekt typu {@link Cruise}, który będzie mapowany
     * @return Wynik mapowania, obiekt typu {@link CruiseGeneralDTO}
     */
    public static CruiseGeneralDTO createCruiseGeneralDTOFromEntity(Cruise cruise) {
        if (cruise == null) {
            return null;
        }

        CruiseGeneralDTO cruiseGeneralDTO = new CruiseGeneralDTO();
        cruiseGeneralDTO.setStartDate(cruise.getStartDate());
        cruiseGeneralDTO.setEndDate(cruise.getEndDate());
        cruiseGeneralDTO.setRoute(RouteMapper.createRouteGeneralDTOFromEntity(cruise.getRoute()));
        cruiseGeneralDTO.setFerry(FerryMapper.createFerryGeneralDTOFromEntity(cruise.getFerry()));
        cruiseGeneralDTO.setNumber(cruise.getNumber());
        cruiseGeneralDTO.setPopularity(cruise.getPopularity());
        cruiseGeneralDTO.setVersion(cruise.getVersion());
        return cruiseGeneralDTO;
    }

    /**
     * Metoda mapująca obiekt typu {@link Cruise} na obiekt typu {@link CruiseDetailsDTO}.
     *
     * @param cruise Obiekt typu {@link Cruise}, który będzie mapowany
     * @return Wynik mapowania, obiekt typu {@link CruiseDetailsDTO}
     */
    public static CruiseDetailsDTO createCruiseDetailsDTOFromEntity(Cruise cruise) {
        if (cruise == null) {
            return null;
        }

        CruiseDetailsDTO cruiseDetailsDTO = new CruiseDetailsDTO();
        cruiseDetailsDTO.setStartDate(cruise.getStartDate());
        cruiseDetailsDTO.setEndDate(cruise.getEndDate());
        cruiseDetailsDTO.setRoute(RouteMapper.createRouteGeneralDTOFromEntity(cruise.getRoute()));
        cruiseDetailsDTO.setFerry(FerryMapper.createFerryGeneralDTOFromEntity(cruise.getFerry()));
        cruiseDetailsDTO.setNumber(cruise.getNumber());
        cruiseDetailsDTO.setPopularity(cruise.getPopularity());
        cruiseDetailsDTO.setModificationDate(cruise.getModificationDate());
        cruiseDetailsDTO.setModifiedBy(AccountMapper.createAccountGeneralDTOFromEntity(cruise.getModifiedBy()));
        cruiseDetailsDTO.setCreationDate(cruise.getCreationDate());
        cruiseDetailsDTO.setCreatedBy(AccountMapper.createAccountGeneralDTOFromEntity(cruise.getCreatedBy()));
        cruiseDetailsDTO.setVersion(cruise.getVersion());
        return cruiseDetailsDTO;

    }

    /**
     * Metoda mapująca obiekt typu {@link CruiseDetailsDTO} na obiekt typu {@link Cruise}.
     *
     * @param cruiseDetailsDTO Obiekt typu {@link CruiseDetailsDTO}, który będzie mapowany
     * @return Wynik mapowania, obiekt typu {@link Cruise}
     */
    public static Cruise createCruiseFromCruiseDetailsDTO(CruiseDetailsDTO cruiseDetailsDTO) {
        if (cruiseDetailsDTO == null) {
            return null;
        }

        Cruise cruise = new Cruise();
        cruise.setStartDate(cruiseDetailsDTO.getStartDate());
        cruise.setEndDate(cruiseDetailsDTO.getEndDate());
        cruise.setRoute(RouteMapper.createRouteFromRouteGeneralDTO(cruiseDetailsDTO.getRoute()));
        cruise.setFerry(FerryMapper.createFerryFromFerryGeneralDTO(cruiseDetailsDTO.getFerry()));
        cruise.setNumber(cruiseDetailsDTO.getNumber());
        cruise.setPopularity(cruiseDetailsDTO.getPopularity());
        cruise.setModificationDate(cruiseDetailsDTO.getModificationDate());
        cruise.setModifiedBy(AccountMapper.createAccountFromAccountGeneralDTO(cruiseDetailsDTO.getModifiedBy()));
        cruise.setCreationDate(cruiseDetailsDTO.getCreationDate());
        cruise.setCreatedBy(AccountMapper.createAccountFromAccountGeneralDTO(cruiseDetailsDTO.getCreatedBy()));
        cruise.setVersion(cruiseDetailsDTO.getVersion());
        return cruise;
    }
}
