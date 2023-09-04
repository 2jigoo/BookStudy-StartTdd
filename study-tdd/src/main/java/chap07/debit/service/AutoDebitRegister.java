package chap07.debit.service;

import chap07.debit.entity.AutoDebitInfo;
import chap07.debit.dto.AutoDebitReq;
import chap07.debit.entity.CardValidity;
import chap07.debit.dto.RegisterResult;
import chap07.debit.repository.AutoDebitInfoRepository;
import chap07.debit.validator.CardNumberValidator;

import java.time.LocalDateTime;

public class AutoDebitRegister {

    private CardNumberValidator validator;
    private AutoDebitInfoRepository repository;

    public AutoDebitRegister(CardNumberValidator validator, AutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) {
        CardValidity validity = validator.validate(req.getCardNumber());
        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }

        AutoDebitInfo info = repository.findOne(req.getUserId());
        if (info != null) {
            info.changeCardNumber(req.getCardNumber());
        } else {
            AutoDebitInfo newInfo = new AutoDebitInfo(req.getUserId(), req.getCardNumber(), LocalDateTime.now());
            repository.save(newInfo);
        }

        return RegisterResult.success();
    }

}
