package chap08;


import jdk.jshell.JShell;

import java.time.LocalDate;

public class UserPointCalculator {
    private PointRule pointRule = new PointRule();
    private SubscriptionDao subscriptionDao;;
    private ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao, ProductDao productDao){
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    /*public int calculatePoint(User u){
        Subscription s = subscriptionDao.selectByUser(u.getId());
        if (s == null) throw new NoSuchFieldException();
        Product p = productDao.selectById(s.getProductId());
        LocalDate now = LocalDate.now();
        int point = 0;
        if (s.isFinished(now)){
            point += p.getDefaultPoint();
        }else {
            point += p.getDefaultPoint();
        }
        if (s.getGrade() == GOLD){
            point += 100;
        }
        return point;
    }*/

    public void setPointRule(PointRule pointRule){
        this.pointRule =pointRule;
    }
    public int calculatePoint(User u){
        Subscription s = subscriptionDao.selectByUser(u.getId());
        if (s == null) throw new NoSuchFieldException();
        Product p = productDao.selectById(s.getProductId());
        LocalDate now = LocalDate.now();
        return new PointRule().calculate(s,p,now);
    }
}
