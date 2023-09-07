package chap08.subscription.service;

import chap08.subscription.entity.Grade;
import chap08.subscription.entity.Product;
import chap08.subscription.entity.Subscription;

import java.time.LocalDate;

public class PointRule {

    public int calculate(Subscription subscription, Product product, LocalDate now) {
        int point = product.getDefaultPoint();

        if (!subscription.isFinished(now)) {
            point += 10;
        }

        if (subscription.getGrade() == Grade.GOLD) {
            point += 100;
        }

        return point;
    }

}
