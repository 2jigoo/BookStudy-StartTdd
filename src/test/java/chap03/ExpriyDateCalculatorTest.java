package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExpriyDateCalculatorTest {
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){
        LocalDate billingDate = LocalDate.of(2019,3,1);
        int payAmount = 10_000;

        ExpriyDateCalculator cal = new ExpriyDateCalculator();
        LocalDate expriyDate = cal.calculateExpriyDate(billingDate,payAmount);

        assertEquals(LocalDate.of(2019,4,1), expriyDate);
    }

}