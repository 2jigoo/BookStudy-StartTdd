package chap08.subscription.service;

import chap08.subscription.dao.ProductDao;
import chap08.subscription.dao.SubscriptionDao;
import chap08.subscription.entity.Grade;
import chap08.subscription.entity.Product;
import chap08.subscription.entity.Subscription;
import chap08.subscription.entity.User;
import chap08.subscription.exception.NoSubscriptionException;

import java.time.LocalDate;

public class UserPointCalculator {

    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao, ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    public int calculatePoint(User user) {
        Subscription subscription = subscriptionDao.selectByUser(user.getId());
        if (subscription == null) {
            throw new NoSubscriptionException();
        }

        LocalDate now = LocalDate.now();
        Product product = productDao.selectById(subscription.getProductId());

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
