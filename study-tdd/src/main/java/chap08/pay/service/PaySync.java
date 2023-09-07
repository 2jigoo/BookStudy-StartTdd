package chap08.pay.service;

import chap08.pay.dao.PayInfoDao;
import chap08.pay.entity.PayInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PaySync {

    private PayInfoDao payInfoDao = new PayInfoDao();

    public void sync() throws IOException {
        Path path = Paths.get("/Users/jigoo/data/pay/cp0001.csv");
        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    return new PayInfo(
                            data[0], data[1], Integer.parseInt(data[2])
                    );
                })
                .collect(Collectors.toList());
        payInfos.forEach(pi -> payInfoDao.insert(pi));
    }

}
