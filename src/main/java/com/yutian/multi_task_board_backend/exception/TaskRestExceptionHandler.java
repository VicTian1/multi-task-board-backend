package com.yutian.multi_task_board_backend.exception;


import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@Slf4j
@ControllerAdvice
public class TaskRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleException(TaskNotFoundException exc){
        TaskErrorResponse error = new TaskErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleException(IllegalArgumentException exc) {
        TaskErrorResponse error = new TaskErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }





    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<TaskErrorResponse> handleBadRequestException(Exception exc) {
        TaskErrorResponse error = new TaskErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Invalid request body. Please check your request format and field values.");
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<TaskErrorResponse> handleValidationException(Exception exc){
        TaskErrorResponse error=new TaskErrorResponse();

        String message= "Validation failed";
        if(exc instanceof MethodArgumentNotValidException methodEx){
            var fieldError = methodEx.getBindingResult().getFieldError();
            if(fieldError!=null){
                message=fieldError.getDefaultMessage();
            }
        }else if(exc instanceof ConstraintViolationException constrainEx){
            var violations=constrainEx.getConstraintViolations();
            if(!violations.isEmpty()){
                message=violations.iterator().next().getMessage();
            }
        }
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(message);
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


    }


    @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleException(Exception exc){
        log.error("Unhandled exception: ",exc);
        TaskErrorResponse error= new TaskErrorResponse();

        error.setMessage("An unexpected error occurred. Please try again later.");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



