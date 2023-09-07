package chap08.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDate;

public class DailyBatchLoaderTest {

    private Times mockTimes = Mockito.mock(Times.class);
    private final DailyBatchLoader loader = new DailyBatchLoader();

    @BeforeEach
    void setUp() {
        loader.setBasePath("src/test/resources");
        loader.setTimes(mockTimes);
    }

    @Test
    void loadCount() {
        BDDMockito.given(mockTimes.today()).willReturn(LocalDate.of(2019, 1, 1));
        int result = loader.load();
        assertEquals(3, result);
    }
    
}
