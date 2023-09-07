package chap08;

import java.time.LocalDate;

public class PointRule {
    public int calculatePoint(Subscription s, Product p, LocalDate now){
        int point = 0;
        if (s.isFinished(now)){
            point += p.getDefaultPoint();
        }else {
            point += p.getDefaultPoint() + 10;
        }
        if (s.getGrade() == GOLD){
            point += 100;
        }
        return point;
    }
}
