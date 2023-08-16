package chap03;

import java.time.LocalDate;

public class ExpriyDateCalculator {

    public LocalDate calculateExpriyDate(PayData payData) {
        if (payData.getFirstBillingDate() != null) {
            LocalDate candidateExp = payData.getBillingData().plusMonths(1);
            if (payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()){
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingData().plusMonths(1);
    }
}
