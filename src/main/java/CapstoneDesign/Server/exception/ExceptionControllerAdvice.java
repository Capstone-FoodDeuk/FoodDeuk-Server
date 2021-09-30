package CapstoneDesign.Server.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResult> validExceptionHandle(MethodArgumentNotValidException ex) {
        log.error("[exceptionHandle] ex", ex);
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST,
                "유효성 검사 실패 : " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST); // 2
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResult> BadRequestExceptionHandle(RuntimeException ex) {
        log.error("[exceptionHandle] ex", ex);
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
}
