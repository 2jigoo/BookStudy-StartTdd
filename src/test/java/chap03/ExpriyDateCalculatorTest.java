package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExpriyDateCalculatorTest {
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){
        assertExpriyDate(PayData.builder()
                .billingDate(LocalDate.of(2019,3,1))
                .payAmount(10_000)
                .build(), LocalDate.of(2019,4,1));

        assertExpriyDate(PayData.builder()
                .billingDate(LocalDate.of(2019,5,5))
                .payAmount(10_000)
                .build(), LocalDate.of(2019,6,5));

        assertExpriyDate(PayData.builder()
                .billingDate(LocalDate.of(2019,5,31))
                .payAmount(10_000)
                .build(), LocalDate.of(2019,6,30));

        assertExpriyDate(PayData.builder()
                .billingDate(LocalDate.of(2020,1,31))
                .payAmount(10_000)
                .build(), LocalDate.of(2020,2,29));
    }

    @Test
    void 납부일과_힌달_뒤_일자가_같지_않음(){
        assertExpriyDate(PayData.builder()
                .billingDate(LocalDate.of(2019,1,31))
                .payAmount(10_000)
                .build(), LocalDate.of(2019,2,28));
    }

    private void assertExpriyDate(PayData payData , LocalDate expectedExpriyDate){
        ExpriyDateCalculator cal = new ExpriyDateCalculator();
        LocalDate realExpriyDate = cal.calculateExpriyDate(payData);
        assertEquals(expectedExpriyDate, realExpriyDate);
    }

}