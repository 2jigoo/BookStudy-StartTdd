package chap03;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

public class ExpriyDateCalculator {

    public LocalDate calculateExpriyDate(PayData payData) {
        int addedMonths = payData.getPayAmount() == 100_000 ? 12 : payData.getPayAmount() /10_000;
        if (payData.getFirstBillingDate() != null) {
            return expriyDateUsingFirstBillingDate(payData, addedMonths);
        }else {
            return payData.getBillingData().plusMonths(addedMonths);
        }
    }

    private LocalDate expriyDateUsingFirstBillingDate(PayData payData, int addedMonths){
        LocalDate candidateExp = payData.getBillingData().plusMonths(addedMonths);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        if (dayOfFirstBilling != candidateExp.getDayOfMonth()){
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
            if (dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        }else {
            return candidateExp;
        }
    }
}
