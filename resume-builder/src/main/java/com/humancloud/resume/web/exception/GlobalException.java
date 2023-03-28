package com.humancloud.resume.web.exception;

import com.humancloud.resume.web.utils.BaseResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponseDTO> handleBaseException(BaseException exception) {
        BaseResponseDTO responseDTO = BaseResponseDTO.builder()
                .code(exception.getCode())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BaseResponseDTO dto=new BaseResponseDTO(String.valueOf(HttpStatusCode.valueOf(400)), ex.getLocalizedMessage());
        return new ResponseEntity<>(dto,status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors=new HashMap<>();
        ValidationErrorMessage vm=new ValidationErrorMessage();
        ex.getBindingResult().getFieldErrors().forEach(e->{
                errors.put(((FieldError)e).getField(),((FieldError)e).getDefaultMessage());
        });

        vm.setStatus(HttpStatus.BAD_REQUEST);
        vm.setError(errors);
        return new ResponseEntity(vm,status);
    }

}
