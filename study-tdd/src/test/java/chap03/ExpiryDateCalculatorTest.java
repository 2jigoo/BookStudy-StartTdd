package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        LocalDate billingDate = LocalDate.of(2019, 3, 1);
        int payAmount = 10_000;

        ExpiryDateCalculator calculator = new ExpiryDateCalculator();
        LocalDate expiryDate = calculator.calculateExpiryDate(billingDate, payAmount);

        assertEquals(LocalDate.of(2019, 4, 1), expiryDate);
    }
}
