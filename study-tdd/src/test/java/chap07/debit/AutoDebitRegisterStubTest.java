package chap07.debit;

import chap07.debit.dto.AutoDebitReq;
import chap07.debit.dto.RegisterResult;
import chap07.debit.entity.CardValidity;
import chap07.debit.repository.MemoryAutoDebitInfoRepository;
import chap07.debit.service.AutoDebitRegister;
import chap07.debit.validator.StubCardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoDebitRegisterStubTest {

    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository;


    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    void invalidCard() {
        // 유효하지 않은 카드번호 상황 흉내
        cardNumberValidator.setInvalidNo("111122223333");

        AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
        RegisterResult result = register.register(req);
        assertEquals(CardValidity.VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        cardNumberValidator.setTheftNo("1234567890123456");

        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = register.register(req);
        assertEquals(CardValidity.THEFT, result.getValidity());
    }

}
