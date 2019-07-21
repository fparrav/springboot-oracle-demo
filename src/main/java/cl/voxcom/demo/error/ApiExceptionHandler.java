package cl.voxcom.demo.error;

/**
 * Created by Felipe Parra V. on July, 2019
 * Email : fparrav@icloud.com
 * Github: fparrav
 */
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleException(ApiNotFoundException ane){
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ane.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ApiErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex){
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ApiErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler
    protected ResponseEntity<ApiErrorResponse> handleConstraintViolation(DuplicateKeyException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ApiErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}