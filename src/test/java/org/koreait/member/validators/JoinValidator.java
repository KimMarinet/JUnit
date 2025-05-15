package org.koreait.member.validators;

import org.koreait.global.validators.RequiredFieldValidator;
import org.koreait.global.validators.Validator;
import org.koreait.member.controllers.RequestJoin;

public class JoinValidator implements Validator<RequestJoin>, RequiredFieldValidator {
    @Override
    public void check(RequestJoin form) {
        String email = form.getEmail();
        String password = form.getPassword();
//        if(email == null || email.isBlank()){
//            throw new BadRequestException("이메일을 입력하세요.");
//        }
        checkString(email, "이메일을 입력하세요.");
        checkString(password, "비밀번호를 입력하세요.");
    }
}
