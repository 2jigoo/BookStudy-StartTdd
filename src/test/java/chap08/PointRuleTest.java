package chap08;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PointRuleTest {

    @Test
    void 만료전_GOLD등급은_130포인트() {
        PointRule rule = new PointRule();
        Subscription s = new Subscription(
                LocalDate.of(2019,5,5),
                Grade.GOLD);
        Product p = new Product();
        p.setDefaultPoint(20);
       int point = rule.calculate(s, LocalDate.of(2019,5,1));
       assertEquals(130,point);
    }
}