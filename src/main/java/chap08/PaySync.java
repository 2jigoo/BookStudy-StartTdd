package chap08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class PaySync {
    private PayInfoDao payInfoDao = new PayInfoDao();

    public void sync() throws IOException{
        Path path = Paths.get("D:\\data\\pay\\cp0001.csv");
        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    PayInfo payInfo = new PayInfo(
                            data[0],data[1],Integer.parseInt(data[2])
                    );
                    return payInfo;
                })
                .collect(Collections.toList());
        payInfos.forEach(pi -> payInfoDao.insert(pi));
    }
}
