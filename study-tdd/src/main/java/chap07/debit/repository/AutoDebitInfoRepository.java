package chap07.debit.repository;

import chap07.debit.entity.AutoDebitInfo;

public interface AutoDebitInfoRepository {

    void save(AutoDebitInfo newInfo);

    AutoDebitInfo findOne(String userId);

}
