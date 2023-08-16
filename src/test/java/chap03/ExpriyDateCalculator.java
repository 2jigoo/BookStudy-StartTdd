package chap03;

import java.time.LocalDate;

public class ExpriyDateCalculator {

    public LocalDate calculateExpriyDate(PayData payData){
        return payData.getBillingData().plusMonths(1);
    }

}
