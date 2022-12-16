package net.krak.quickapp.playload.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler 
{

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<Map<String,String>> details = new ArrayList<Map<String,String>>();
    
    ex.getBindingResult().getFieldErrors().forEach(error->{
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put(error.getField(), error.getDefaultMessage());
		
		details.add(errorMap);
	});
    
    ErrorResponse error = new ErrorResponse("Validation Failed", details);
    
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
   ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
    return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
 
  @ExceptionHandler(RecordNotFoundException.class)
  public final ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
    return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }
  
  
  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
	    ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
	    return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }
  
  
  
  
  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(ex.getMostSpecificCause().getLocalizedMessage());
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }
  
  
  @ExceptionHandler(NoSuchElementException.class)
  protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage());
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }
  
}