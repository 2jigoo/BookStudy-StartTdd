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

    private PointRule pointRule = new PointRule();
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao, ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    // 테스트 코드에서 대역으로 계산 기능을 대체할 수 있음
    public void setPointRule(PointRule pointRule) {
        this.pointRule = pointRule;
    }

    public int calculatePoint(User user) {
        Subscription subscription = subscriptionDao.selectByUser(user.getId());
        if (subscription == null) {
            throw new NoSubscriptionException();
        }

        LocalDate now = LocalDate.now();
        Product product = productDao.selectById(subscription.getProductId());

        return pointRule.calculate(subscription, product, now);
    }
}
