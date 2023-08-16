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

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();
        assertExpriyDate(payData,LocalDate.of(2019,3,31));
        //첫 납부일과 만료일 일자가 같지 않은 사례 추가
        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,30))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();
        assertExpriyDate(payData2,LocalDate.of(2019,3,30));

        //첫 납부일과 만료일 일자가 같지 않은 또 다른 사례 추가
        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,5,31))
                .billingDate(LocalDate.of(2019,6,30))
                .payAmount(10_000)
                .build();
        assertExpriyDate(payData3,LocalDate.of(2019,7,31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산(){
        assertExpriyDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,3,1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019,5,1));
        //3만원
        assertExpriyDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,3,1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019,6,1));
    }

    private void assertExpriyDate(PayData payData , LocalDate expectedExpriyDate){
        ExpriyDateCalculator cal = new ExpriyDateCalculator();
        LocalDate realExpriyDate = cal.calculateExpriyDate(payData);
        assertEquals(expectedExpriyDate, realExpriyDate);
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부(){
        assertExpriyDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(20019,1,31))
                        .billingDate(LocalDate.of(2019,2,28))
                        .payAmount(40_000)
                        .build(),
                LocalDate.of(2019,6,30));
        //3만원
        assertExpriyDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019,3,31))
                        .billingDate(LocalDate.of(2019,4,30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019,7,31));
    }

}