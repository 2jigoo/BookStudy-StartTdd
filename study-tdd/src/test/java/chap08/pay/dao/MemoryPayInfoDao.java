package chap08.pay.dao;

import chap08.pay.entity.PayInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPayInfoDao extends PayInfoDao {

    private Map<String, PayInfo> infos = new HashMap<>();

    @Override
    public void insert(PayInfo payInfo) {
        infos.put(payInfo.getColumn1(), payInfo);
    }

    @Override
    public List<PayInfo> getAll() {
        return (List<PayInfo>) infos.values();
    }

}
