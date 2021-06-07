package pl.lodz.p.it.ssbd2021.ssbd02.utils.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.mop.CruiseDetailsDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.mop.CruiseGeneralDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mok.Account;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cruise;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Ferry;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Route;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Seaport;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CruiseMapperTest {

    Account accountModifiedBy;
    Account accountCreatedBy;
    Cruise cruise;

    @BeforeEach
    void setUp() {
        accountModifiedBy = new Account();
        accountModifiedBy.setLogin("ModifiedLogin");
        accountCreatedBy = new Account();
        accountCreatedBy.setLogin("CreatedLogin");
        cruise = createCruise();
    }

    @Test
    void createCruiseGeneralDTOFromEntity() {
        CruiseGeneralDTO cruiseGeneralDTO = CruiseMapper.createCruiseGeneralDTOFromEntity(cruise);

        assertAll(
                () -> assertEquals(cruise.getStartDate(), cruiseGeneralDTO.getStartDate()),
                () -> assertEquals(cruise.getEndDate(), cruiseGeneralDTO.getEndDate()),
                () -> assertEquals(FerryMapper.createFerryGeneralDTOFromEntity(cruise.getFerry()), cruiseGeneralDTO.getFerry()),
                () -> assertEquals(cruise.getNumber(), cruiseGeneralDTO.getNumber()),
                () -> assertEquals(cruise.getVersion(), cruiseGeneralDTO.getVersion())
        );
    }

    private Ferry createFerry() {
        Ferry ferry = new Ferry();
        ferry.setName("Prom");
        ferry.setOnDeckCapacity(50);
        ferry.setVehicleCapacity(20);
        ferry.setVersion(1L);
        return ferry;
    }

    private Cruise createCruise() {
        Cruise cruise = new Cruise();
        cruise.setStartDate(Timestamp.valueOf("2020-09-23 10:10:10.0"));
        cruise.setEndDate(Timestamp.valueOf("2020-09-24 10:10:10.0"));
        cruise.setFerry(createFerry());
        cruise.setRoute(createRoute());
        cruise.setNumber("BARVEN000002");
        cruise.setModificationDate(Timestamp.from(Instant.now()));
        cruise.setModifiedBy(accountModifiedBy);
        cruise.setCreationDate(Timestamp.valueOf("2020-03-25 11:21:15"));
        cruise.setCreatedBy(accountCreatedBy);
        cruise.setVersion(1L);
        return cruise;
    }

    private Route createRoute() {
        Route route = new Route();
        Seaport start = new Seaport();
        start.setCity("Start");
        start.setCode("ST");
        route.setStart(start);
        Seaport end = new Seaport();
        end.setCity("Destination");
        end.setCode("DEST");
        route.setDestination(end);
        route.setCode("RCODE");
        return route;
    }

    @Test
    void createCruiseDetailsDTOFromEntity() {
        CruiseDetailsDTO cruiseDetailsDTO = CruiseMapper.createCruiseDetailsDTOFromEntity(cruise);

        assertEquals(cruise.getVersion(), cruiseDetailsDTO.getVersion());
        assertEquals(cruise.getStartDate(), cruiseDetailsDTO.getStartDate());
        assertEquals(cruise.getEndDate(), cruiseDetailsDTO.getEndDate());
        assertEquals(FerryMapper.createFerryGeneralDTOFromEntity(cruise.getFerry()), cruiseDetailsDTO.getFerry());
        assertEquals(RouteMapper.createRouteGeneralDTOFromEntity(cruise.getRoute()), cruiseDetailsDTO.getRoute());
        assertEquals(cruise.getNumber(), cruiseDetailsDTO.getNumber());
        assertEquals(cruise.getModificationDate(), cruiseDetailsDTO.getModificationDate());
        assertEquals(cruise.getCreationDate(), cruiseDetailsDTO.getCreationDate());
        assertEquals(AccountMapper.createAccountGeneralDTOFromEntity(cruise.getModifiedBy()), cruiseDetailsDTO.getModifiedBy());
        assertEquals(AccountMapper.createAccountGeneralDTOFromEntity(cruise.getCreatedBy()), cruiseDetailsDTO.getCreatedBy());
    }
}
