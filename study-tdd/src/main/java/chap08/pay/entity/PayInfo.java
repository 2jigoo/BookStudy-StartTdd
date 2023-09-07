package chap08.pay.entity;

public class PayInfo {

    private String column1;
    private String column2;
    private int column3;

    public PayInfo(String column1, String column2, int column3) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
    }

    public String getColumn1() {
        return column1;
    }

    public String getColumn2() {
        return column2;
    }

    public int getColumn3() {
        return column3;
    }

}
