package chap07.debit.dto;

import chap07.debit.entity.CardValidity;

public class RegisterResult {

    private CardValidity cardValidity;

    public static RegisterResult error(CardValidity validity) {
        return null;
    }

    public static RegisterResult success() {
        return null;
    }

    public CardValidity getValidity() {
        return cardValidity;
    }

}
