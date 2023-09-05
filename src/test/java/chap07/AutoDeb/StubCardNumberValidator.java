/*
package chap07.AutoDeb;

public class StubCardNumberValidator extends CardNumberValidator {
    private String invalidNo;
    private String theftNo;

    public void setInvalidNo(String invalidNo){
        this.invalidNo = invalidNo;
    }
    public void setTheftNo(String theftNo){
        this.invalidNo = invalidNo;
    }

    @Override
    public CardValidity validity(String cardNumber){
        if (invalidNo != null && invalidNo.equals(cardNumber)){
            return CardValidty.INVALID;
        }
        if (theftNo != null && theftNo.equals(cardNumber)){
            return CardValidty.THEFT;
        }
        return CardValidty.VALID;
    }
}
*/
