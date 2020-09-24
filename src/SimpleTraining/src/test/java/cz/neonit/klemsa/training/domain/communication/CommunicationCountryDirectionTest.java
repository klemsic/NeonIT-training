package cz.neonit.klemsa.training.domain.communication;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CommunicationCountryDirectionTest {


    @Test
    public void valueOf_num_num() {
        assertEquals(new CommunicationCountryDirection(420,421),CommunicationCountryDirection.valueOf("420-421"));
    }

    @Test
    public void valueOf_null_num() {
        assertEquals(new CommunicationCountryDirection(null,421),CommunicationCountryDirection.valueOf("-421"));
    }

    @Test
    public void valueOf_num_null() {
        assertEquals(new CommunicationCountryDirection(420,null),CommunicationCountryDirection.valueOf("420-"));
    }
}
