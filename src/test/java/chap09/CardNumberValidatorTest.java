package chap09;

import chap07.AutoDeb.CardNumberValidator;
import chap07.AutoDeb.CardValidity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardNumberValidatorTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp(){
        wireMockServer = new WireMockServer(options().port(8089));
        wireMockServer.stsrt();
    }

    @AfterEach
    void tearDown(){
        wireMockServer.stop();
    }

    @Test
    void valid(){
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .withRequestBody(equalTo("1234567890"))
                .wilReturn(aResponse()
                        .withHeader("Content-Type", "text/pain")
                        .withBody("ok")));
        CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.VALID,validity);
    }
    @Test
    void timeout(){
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .wilReturn(aResponse()
                        .withFixedDelay(5000)));

        CardNumberValidator validator = new CardNumberValidator()("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.TIMEOUT, validity);
    }
}