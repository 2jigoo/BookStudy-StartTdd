package chap03;

import java.time.LocalDate;

public class ExpriyDateCalculator {

    public LocalDate calculateExpriyDate(LocalDate billingDate, int payAmount){
        return billingDate.plusMonths(1);
    }

}
