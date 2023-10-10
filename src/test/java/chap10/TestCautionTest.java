package chap10;

import chap07.join.passwordcheck.WeakPasswordException;
import net.bytebuddy.asm.Advice;
import org.apache.catalina.User;
import org.apache.catalina.util.Introspection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.text.Format;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

class TestCautionTest {

    private List<Integer> answers = Arrays.asList(1,2,3,4);
    private Long respondentId = 100L;

    @DisplayName("답변에 성공하면 결과 저장함")
    @Test
    public void saveAnswerSuccessfully(){
        //답변할 설문이 존재
        Survey survey = SurveyFactory.createApprovedSurvey(1L);
        surveyRepository.save(survey);

        SurveyAnswerRequest surveyAnswer = SurveyAnswerRequest.builder()
                .surveyId(survey.getId())
                .reapondentId(respondentId)
                .answers(answers)
                .build();

        svc.answerSuvey(surveyAnswer);

        //저장 결과 확인
        SurverAnswer savedAnswer =
                memoryRepository.findBySurveyAndRespondent(
                        survey.getId(),respondentId);
        assertAll(
                () -> assertEquals(respondentId, savedAnswer.getRespondentId()),
                () -> assertEquals(answers.size(), savedAnswer.getAnswer().size()),
                () -> assertEquals(answers.get(0), savedAnswer.getAnswer().get(0)),
                () -> assertEquals(answers.get(1), savedAnswer.getAnswer().get(1)),
                () -> assertEquals(answers.get(2), savedAnswer.getAnswer().get(2)),
                () -> assertEquals(answers.get(2), savedAnswer.getAnswer().get(3))
        );
    }

    @Test
    void dateFormat(){
    LocalDate date = LocalDate.of(1945,8,15);
    String dateStr = formatDate(date);
    assertEquals(date.getYear() + "년" +
            date.getMonthValue() + "월" +
            date.getDayOfMonth() + "일", dateStr);
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    void noDupId_RegisterSuccess(){
        userRegister.register("id", "pw", "email");

        User savedUser = fakeRepository.finById("id");
        assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail(){
        userRegister.register("id", "pw", "email@email.com");

        Advice.ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier).should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
    }

    @DisplayName("약한 암호면 가입 싶패")
    @Test
    void weakPassword(){
        BDDMockito
                .given(mockPasswordChecker.checkPasswordWeak(Mockito.anyString()))
                .willReturn(true);

        assertThrows(WeakPasswordException.class, ()->{
            userRegister.register("id","pw","email");
        });
    }

}