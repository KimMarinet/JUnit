package org.koreait.test.member;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.member.controllers.RequestJoin;
import org.koreait.member.services.JoinService;
import org.koreait.member.validators.JoinValidator;

import java.lang.reflect.Method;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원 가입 기능 테스트")
public class JoinServiceTest {

    private  JoinService service;

    private RequestJoin getSuccessData(){
        Faker faker = new Faker(Locale.KOREAN);
        return RequestJoin.builder()
                .email(faker.internet().emailAddress())
                .password("123456789")
                .build();
    }

    private RequestJoin getFailureData(String field, String value){
        RequestJoin obj = getSuccessData();
        for (Method method : RequestJoin.class.getDeclaredMethods()){
            System.out.println(method.getName());
            String name = method.getName();
            if(name.equalsIgnoreCase("set" + field)){
                try {
                    method.invoke(obj, value);
                }catch (Exception e){
                    break;
                }
            }
        }
        return obj;
    }

    @BeforeEach
    void init(){
        service = new JoinService(new JoinValidator());
        System.out.println(getSuccessData());
    }

    @Test
    @DisplayName("회원 가입 성공 시 예외가 발생하지 않는다")
    void successTest(){
        //JoinService service = new JoinService(new JoinValidator());
        //RequestJoin form = new RequestJoin();
//        form.setEmail("user01@test.org");
//        form.setPassword("123456789");
        assertDoesNotThrow(()->{
            service.process(getSuccessData());
        });
    }

    @Test
    @DisplayName("필수 항목 입력 X => 예외 처리 발생")
    void requiredFieldValidationTest(){
        assertAll(
                () -> assertThrows(BadRequestException.class, ()->{
                service.process(getFailureData("email", null));
                }),

                () -> assertThrows(BadRequestException.class, ()->{
                service.process(getFailureData("password", null));
                })
        );
    }
}
