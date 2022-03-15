package com.Demointelli.demo2.ControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice  {

    @ExceptionHandler(NullPointerException.class)
   public ResponseEntity<?> handleNullPointerException(NullPointerException e)
    {
        return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
    }

}
