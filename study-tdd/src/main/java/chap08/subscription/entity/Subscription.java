package chap08.subscription.entity;

import java.time.LocalDate;

public class Subscription {

    private Long productId;
    private LocalDate expirationDate;
    private Grade grade;

    public Subscription(LocalDate expirationDate, Grade grade) {
        this.expirationDate = expirationDate;
        this.grade = grade;
    }

    public Long getProductId() {
        return productId;
    }

    public boolean isFinished(LocalDate now) {
        return expirationDate.isBefore(now);
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
