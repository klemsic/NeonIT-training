package cz.neonit.klemsa.training;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MockTest {
    @Mock Random random;
    // Random random = new SimpleRandom();

    @Test
    public void printRandomNumberTest() {
        Mockito.when(random.get()).thenReturn(1);
        RandomNumberPrinter rnp = new RandomNumberPrinter();
        rnp.print(random);
        Assertions.assertEquals(1,1);
    }

    public class RandomNumberPrinter {
        public void print(Random random) {
            System.out.println(random.get());
        }
    }

    public class Random {
        public Integer get() {
            java.util.Random random = new java.util.Random();
            return random.nextInt();
        }
    }
}

