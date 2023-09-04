package chap07.debit.entity;

import java.time.LocalDateTime;

public class AutoDebitInfo {

    private String userId;
    private String cardNumber;
    private LocalDateTime registerDate;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime now) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.registerDate = now;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

}
