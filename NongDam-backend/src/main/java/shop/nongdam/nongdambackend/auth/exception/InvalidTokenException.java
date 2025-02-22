package shop.nongdam.nongdambackend.auth.exception;

import shop.nongdam.nongdambackend.global.error.exception.AuthGroupException;

public class InvalidTokenException extends AuthGroupException {
    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException() {
        this("Error: 토큰이 유효하지 않습니다.");
    }
}
