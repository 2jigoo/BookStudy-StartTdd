package chap08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class DailyBatchLoaderTest {
    private Times mockTimes = Mockito.mock(Times.class);
    private final DailyBatchLoader loader = new DailyBatchLoader();

    @BeforeEach
    void setUp(){
        loader.setBasePath("src/test/resources");
        loader.setTimes(mockTimes);
    }

    @Test
    void loadCount(){
        given(mockTimes.today()).willReturn(LocalDate.of(2019,1,1));
        int ret = loader.load();
        assertEquals(3,ret);
    }

}