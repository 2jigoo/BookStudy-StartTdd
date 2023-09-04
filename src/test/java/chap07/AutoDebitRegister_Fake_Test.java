package chap07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AutoDebitRegister_Fake_Test {

    private AutoDebitRegister register;
    private StubCardNumberValidator cardNumberValidator;
    private MemoryAutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator,repository);
    }


    @Test
    void notYetRegistered_newInfoUpdated(){
        AutoDebitReq req = new AutoDebitReq("user1","1234123412341234");
        RegisterResult result = register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("1234123412341234",saved.getCardNumber());
    }
}