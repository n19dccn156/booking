package com.group.booking.Exception;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.group.booking.Common.Const;
import com.group.booking.Common.Message;
import com.group.booking.Models.Addons.ResponseObject;

@RestControllerAdvice
public class ApplicationExceptionHandle {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObject> handleInvalidArgument(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject(Const.STATUS_FAILED, ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), "")
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseObject> handleBindException(BindException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject(Const.STATUS_FAILED, ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), "")
        );
    }

    @ResponseStatus(HttpStatus.FORBIDDEN) 
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseObject> handleAuthenticationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ResponseObject(Const.STATUS_FAILED, Message.FORBIDDEN, "")
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseObject> handleDBException(Exception ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
            new ResponseObject(Const.STATUS_FAILED, Message.BAD_REQUEST, "")
        );
    }

    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ResponseObject> createCourseDeclarativeWithCheckedException(Exception ex) throws SQLException {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(
            new ResponseObject(Const.STATUS_FAILED, Message.REQUEST_TIMEOUT, "")
        );
    }

    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseObject> exception(Exception ex) throws SQLException {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject(Const.STATUS_FAILED, ex.getMessage(), "")
        );
    }
}
