package org.koreait.member.services;

import org.koreait.global.validators.RequiredFieldValidator;
import org.koreait.member.controllers.RequsetJoin;

public class JoinService implements RequiredFieldValidator {
    public void process(RequsetJoin form){
        String email = form.getEmail();
//        if(email == null || email.isBlank()){
//            throw new BadRequestException("이메일을 입력하세요.");
//        }
        checkString(email, "이메일을 입력하세요.");
    }
}
