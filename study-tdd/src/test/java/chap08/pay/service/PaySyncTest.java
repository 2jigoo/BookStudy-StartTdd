package chap08.pay.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class PaySyncTest {

    @Test
    void someTest() throws IOException {
        PaySync paySync = new PaySync();

        // 1. setter로 받기
//        paySync.setFilePath("src/test/resources/c0111.csv");

        // 2. 메서드 파라미터로 받기
        paySync.sync("src/test/resources/c0111.csv");

        // then ...
    }

}
