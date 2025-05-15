package org.koreait.test.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.member.controllers.RequestJoin;
import org.koreait.member.services.JoinService;
import org.koreait.member.validators.JoinValidator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("회원 가입 기능 테스트")
public class JoinServiceTest {

    private  JoinService service;

    @BeforeEach
    void init(){
        service = new JoinService(new JoinValidator());
    }

    @Test
    @DisplayName("회원 가입 성공 시 예외가 발생하지 않는다")
    void successTest(){
        //JoinService service = new JoinService(new JoinValidator());
        RequestJoin form = new RequestJoin();
        form.setEmail("user01@test.org");
        form.setPassword("123456789");
        assertDoesNotThrow(()->{
            service.process(form);
        });
    }

    @Test
    @DisplayName("필수 항목 입력 X => 예외 처리 발생")
    void requiredFieldValidationTest(){
        RequestJoin form = new RequestJoin();
        assertThrows(BadRequestException.class, ()->{
           service.process(form);
        });
    }
}
