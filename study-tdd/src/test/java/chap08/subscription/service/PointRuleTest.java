package chap08.subscription.service;

import chap08.subscription.entity.Grade;
import chap08.subscription.entity.Product;
import chap08.subscription.entity.Subscription;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class PointRuleTest {

    @Test
    void beforeExpiredGive130PointsToGoldGrade() {
        PointRule pointRule = new PointRule();
        Subscription subscription = new Subscription(
                LocalDate.of(2019, 5, 5),
                Grade.GOLD
        );
        Product product = new Product();
        product.setDefaultPoint(20);

        int point = pointRule.calculate(subscription, product, LocalDate.of(2019, 5, 1));

        assertEquals(130, point);
    }

}
