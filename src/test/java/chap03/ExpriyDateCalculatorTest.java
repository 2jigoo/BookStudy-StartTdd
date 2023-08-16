package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExpriyDateCalculatorTest {
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){
        assertExpriyDate(LocalDate.of(2019,3,1), 10_000, LocalDate.of(2019,4,1));
        assertExpriyDate(LocalDate.of(2019,5,5), 10_000, LocalDate.of(2019,6,5));
    }

    private void assertExpriyDate(LocalDate billingDate, int payAmount, LocalDate expectedExpriyDate){
        ExpriyDateCalculator cal = new ExpriyDateCalculator();
        LocalDate realExpriyDate = cal.calculateExpriyDate(billingDate,payAmount);
        assertEquals(expectedExpriyDate, realExpriyDate);
    }

}