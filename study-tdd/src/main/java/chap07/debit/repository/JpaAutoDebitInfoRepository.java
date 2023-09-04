package chap07.debit.repository;

import chap07.debit.entity.AutoDebitInfo;

public class JpaAutoDebitInfoRepository implements AutoDebitInfoRepository {

    @Override
    public void save(AutoDebitInfo newInfo) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }

}
