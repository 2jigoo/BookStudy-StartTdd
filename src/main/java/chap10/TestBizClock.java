package chap10;

import java.time.LocalDateTime;

public class TestBizClock {
    private LocalDateTime now;

    public TestBizClock(){
        setInstance(this);
    }

    public void setNow(LocalDateTime now){
        this.now = now;
    }

    @Override
    public LocalDateTime timeNow(){
        return now !=null ? now : super.now();
    }
}
