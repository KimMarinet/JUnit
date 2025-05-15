package org.koreait.test.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.member.controllers.RequsetJoin;
import org.koreait.member.services.JoinService;

import java.util.prefs.BackingStoreException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원 가입 기능 테스트")
public class JoinServiceTest {
    @Test
    @DisplayName("회원 가입 성공 시 예외가 발생하지 않는다")
    void successTest(){
        JoinService service = new JoinService();
        RequsetJoin form = new RequsetJoin();
        assertDoesNotThrow(()->{
            service.process(form);
        });
    }

    @Test
    @DisplayName("필수 항목 입력 X => 예외 처리 발생")
    void requiredFieldValidationTest(){
        JoinService service = new JoinService();
        RequsetJoin form = new RequsetJoin();
        assertThrows(BackingStoreException.class, () ->{
            //form.setEmail("user01@test.rog");
            service.process(form);
        });
    }
}
