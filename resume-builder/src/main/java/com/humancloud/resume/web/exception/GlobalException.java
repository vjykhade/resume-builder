package com.humancloud.resume.web.exception;

import com.humancloud.resume.web.utils.BaseResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponseDTO> handleBaseException(BaseException exception){
        BaseResponseDTO responseDTO = BaseResponseDTO.builder()
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}
