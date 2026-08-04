package com.vinsguru.playground.sec04.advice;

import com.vinsguru.playground.sec04.exceptions.CustomerNotFoundException;
import com.vinsguru.playground.sec04.exceptions.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleException(CustomerNotFoundException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Custom not found");
        return problem;
    }

    @ExceptionHandler(InvalidInputException.class)
    public ProblemDetail handleException(InvalidInputException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Invalid input");
        return problem;
    }
}
