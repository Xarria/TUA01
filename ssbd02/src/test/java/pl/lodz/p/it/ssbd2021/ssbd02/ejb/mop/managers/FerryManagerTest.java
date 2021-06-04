package pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.managers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import pl.lodz.p.it.ssbd2021.ssbd02.ejb.mop.facades.interfaces.FerryFacadeLocal;
import pl.lodz.p.it.ssbd2021.ssbd02.entities.mop.Ferry;
import pl.lodz.p.it.ssbd2021.ssbd02.exceptions.CommonExceptions;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FerryManagerTest {

    @Mock
    FerryFacadeLocal ferryFacadeLocal;

    @InjectMocks
    FerryManager ferryManager;

    @Spy
    Ferry ferry1 = new Ferry();
    @Spy
    Ferry ferry2 = new Ferry();

    private final String ferryName1 = "ferry1";
    private List<Ferry> ferries;


    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);

        ferries = new ArrayList<>();
        ferries.addAll(Arrays.asList(ferry1, ferry2));
    }

    @Test
    void getAllFerriesTest() {
        when(ferryFacadeLocal.findAll()).thenReturn(ferries);

        assertEquals(ferries.hashCode(), ferryManager.getAllFerries().hashCode());
    }

    @Test
    void getAllFerriesExceptionTest() {
        when(ferryFacadeLocal.findAll()).thenReturn(null);

        WebApplicationException exception = assertThrows(CommonExceptions.class, () -> ferryManager.getAllFerries());

        assertAll(
                () -> assertEquals(CommonExceptions.createNoResultException().getResponse().getStatus(), exception.getResponse().getStatus()),
                () -> assertEquals(CommonExceptions.createNoResultException().getMessage(), exception.getMessage())
        );
    }

    @Test
    void getFerryByNameTest() {
        when(ferryFacadeLocal.findByName(ferryName1)).thenReturn(ferry1);
        assertDoesNotThrow(() -> ferryManager.getFerryByName(ferryName1));
        assertEquals(ferry1, ferryManager.getFerryByName(ferryName1));
        assertEquals(ferry1.hashCode(), ferryManager.getFerryByName(ferryName1).hashCode());
        verify(ferryFacadeLocal, times(3)).findByName(ferryName1);
    }

    @Test
    void getFerryByNameExceptionTest() {
        when(ferryFacadeLocal.findByName(ferryName1)).thenReturn(null);
        assertThrows(CommonExceptions.class, () -> ferryManager.getFerryByName(ferryName1));
        verify(ferryFacadeLocal).findByName(ferryName1);
    }
}
