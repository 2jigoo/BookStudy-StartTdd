package chap07.debit;

import chap07.debit.dto.AutoDebitReq;
import chap07.debit.dto.RegisterResult;
import chap07.debit.entity.CardValidity;
import chap07.debit.repository.AutoDebitInfoRepository;
import chap07.debit.repository.JpaAutoDebitInfoRepository;
import chap07.debit.service.AutoDebitRegister;
import chap07.debit.validator.CardNumberValidator;
import chap07.debit.validator.StubCardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoDebitRegisterTest {

    private AutoDebitRegister register;


    @BeforeEach
    void setUp() {
        CardNumberValidator cardNumberValidator = new StubCardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    void validCard() {
        // 업체에서 받은 테스트용 유효한 카드번호
        AutoDebitReq req = new AutoDebitReq("user1", "1234123421341234");
        RegisterResult result = register.register(req);
        assertEquals(CardValidity.VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        // 업체에서 받은 테스트용 도난 카드번호
        AutoDebitReq req = new AutoDebitReq("user1", "1234123421341234");
        RegisterResult result = register.register(req);
        assertEquals(CardValidity.THEFT, result.getValidity());
    }

}
