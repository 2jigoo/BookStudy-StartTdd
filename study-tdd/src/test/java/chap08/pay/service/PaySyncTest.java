package chap08.pay.service;

import chap08.pay.dao.MemoryPayInfoDao;
import chap08.pay.entity.PayInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

class PaySyncTest {

    private MemoryPayInfoDao memoryPayInfoDao = new MemoryPayInfoDao();

    @Test
    void someTest() throws IOException {
        PaySync paySync = new PaySync(memoryPayInfoDao);

        // 1. setter로 받기
//        paySync.setFilePath("src/test/resources/c0111.csv");

        // 2. 메서드 파라미터로 받기
        paySync.sync("src/test/resources/c0111.csv");

        // then ...
    }

    @Test
    void allDataSaved() throws IOException {
        PaySync paySync = new PaySync(memoryPayInfoDao);
        paySync.sync("src/test/resources/c0111.csv");

        List<PayInfo> savedInfos = memoryPayInfoDao.getAll();

        assertEquals(2, savedInfos.size());
    }

}
