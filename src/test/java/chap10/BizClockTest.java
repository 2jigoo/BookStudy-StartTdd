package chap10;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BizClockTest {
    TestBizClock testBizClock = new TestBizClock();

    @AfterEach
    void resetClock(){
        testBizClock.reset();
    }

    @Test
    void notExpired() {
        testBizClock.setNow(LocalDateTime.of(2019,12,31,0,0,0));
        LocalDateTime expiry = LocalDateTime.of(2019,12,31,0,0,0);
        Member m = Member.builder().expiryDate(expiry).build();
        assertFalse(m.isExpired());
    }

}