package com.humancloud.resume.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorMessage extends RuntimeException{
    private HttpStatus status;
    private Map<String,String> error=new HashMap<>();


}
