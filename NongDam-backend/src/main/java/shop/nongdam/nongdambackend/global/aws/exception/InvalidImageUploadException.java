package shop.nongdam.nongdambackend.global.aws.exception;

import shop.nongdam.nongdambackend.global.error.exception.InvalidGroupException;

public class InvalidImageUploadException extends InvalidGroupException {

    public InvalidImageUploadException(String message) {
        super(message);
    }

    public InvalidImageUploadException() {
        this("이미지 업로드 중 오류가 발생했습니다.");
    }
}
