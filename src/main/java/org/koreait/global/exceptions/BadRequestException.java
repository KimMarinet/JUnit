package org.koreait.global.exceptions;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
