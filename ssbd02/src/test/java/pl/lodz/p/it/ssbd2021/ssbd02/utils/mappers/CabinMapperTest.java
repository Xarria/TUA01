package pl.lodz.p.it.ssbd2021.ssbd02.utils.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.mok.AccountGeneralDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.mop.CabinDetailsDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.dto.mop.CabinGeneralDTO;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mok.Account;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Cabin;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.CabinType;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Ferry;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CabinMapperTest {

    private Ferry ferry;
    private Cabin cabin;
    private Account accountModifiedBy;
    private AccountGeneralDTO accountDTOModifiedBy;
    private Account accountCreatedBy;
    private CabinType cabinType;
    private CabinDetailsDTO cabinDetailsDTO;

    @BeforeEach
    public void setUp() {
        ferry = createFerry();
        accountCreatedBy = new Account();
        accountCreatedBy.setLogin("CreatedByLogin");
        accountModifiedBy = new Account();
        accountModifiedBy.setLogin("ModifiedByLogin");
        accountDTOModifiedBy = new AccountGeneralDTO();
        accountDTOModifiedBy.setLogin("ModifiedByLoginDTO");
        cabinType = createCabinType();
        cabin = createCabin();
        cabinDetailsDTO = createCabinDetailsDTO();
    }

    @Test
    void createCabinDetailsDTOFromEntity() {
        CabinDetailsDTO cabinDTO = CabinMapper.createCabinDetailsDTOFromEntity(cabin);

        assertAll(
                () -> assertEquals(cabin.getVersion(), cabinDTO.getVersion()),
                () -> assertEquals(cabin.getCapacity(), cabinDTO.getCapacity()),
                () -> assertEquals(cabinType.getCabinTypeName(), cabinDTO.getCabinType()),
                () -> assertEquals(cabin.getNumber(), cabinDTO.getNumber()),
                () -> assertEquals(cabin.getModificationDate(), cabinDTO.getModificationDate()),
                () -> assertEquals(AccountMapper.createAccountGeneralDTOFromEntity(cabin.getModifiedBy()), cabinDTO.getModifiedBy()),
                () -> assertEquals(cabin.getCreationDate(), cabinDTO.getCreationDate()),
                () -> assertEquals(AccountMapper.createAccountGeneralDTOFromEntity(cabin.getCreatedBy()), cabinDTO.getCreatedBy())
        );
    }

    @Test
    void createCabinGeneralDTOFromEntity() {
        CabinGeneralDTO cabinDTO = CabinMapper.createCabinGeneralDTOFromEntity(cabin);

        assertAll(
                () -> assertEquals(cabin.getVersion(), cabinDTO.getVersion()),
                () -> assertEquals(cabin.getCapacity(), cabinDTO.getCapacity()),
                () -> assertEquals(cabinType.getCabinTypeName(), cabinDTO.getCabinType()),
                () -> assertEquals(cabin.getNumber(), cabinDTO.getNumber())
        );
    }

    @Test
    void createEntityFromCabinDetailsDTO() {
        Cabin cabin = CabinMapper.createEntityFromCabinDetailsDTO(cabinDetailsDTO, cabinType);
        assertAll(
                () -> assertEquals(cabin.getVersion(), cabinDetailsDTO.getVersion()),
                () -> assertEquals(cabin.getCapacity(), cabinDetailsDTO.getCapacity()),
                () -> assertEquals(cabinType.getCabinTypeName(), cabinDetailsDTO.getCabinType()),
                () -> assertEquals(cabin.getNumber(), cabinDetailsDTO.getNumber())
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

    private CabinType createCabinType() {
        CabinType cabinType = new CabinType();
        cabinType.setCabinTypeName("First class");
        return cabinType;
    }

    private Cabin createCabin() {
        Cabin cabin = new Cabin();
        cabin.setFerry(ferry);
        cabin.setCapacity(100);
        cabin.setCabinType(cabinType);
        cabin.setNumber("J123");
        cabin.setModificationDate(Timestamp.from(Instant.now()));
        cabin.setModifiedBy(accountModifiedBy);
        cabin.setCreationDate(Timestamp.valueOf("2021-06-01 11:11:11"));
        cabin.setCreatedBy(accountCreatedBy);
        cabin.setVersion(1L);
        return cabin;
    }

    private CabinDetailsDTO createCabinDetailsDTO() {
        CabinDetailsDTO cabin = new CabinDetailsDTO();
        cabin.setCapacity(100);
        cabin.setCabinType(cabinType.getCabinTypeName());
        cabin.setNumber("J123");
        cabin.setModificationDate(Timestamp.from(Instant.now()));
        cabin.setModifiedBy(accountDTOModifiedBy);
        cabin.setCreationDate(Timestamp.valueOf("2021-06-01 11:11:11"));
        cabin.setCreatedBy(accountDTOModifiedBy);
        cabin.setVersion(1L);
        return cabin;
    }

}
