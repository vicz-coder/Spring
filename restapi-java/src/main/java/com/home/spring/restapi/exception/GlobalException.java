package com.home.spring.restapi.exception;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.home.spring.restapi.entity.ErrorResponse;

@ControllerAdvice //  ResponseEntityExceptionHandler is a convenient base class for @ControllerAdvice classes that wish to provide centralized exception handling across all @RequestMapping methods through @ExceptionHandler methods.
public class GlobalException extends ResponseEntityExceptionHandler {

	@Autowired
	MessageSource messageSource;
	
	@ExceptionHandler(AccessException.class)
	public ResponseEntity<ErrorResponse> handleAccessDenied(AccessException ae,WebRequest req){
		List<String> details = new ArrayList<String>();
		details.add(ae.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("INNCORRECT REQUEST", details);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(RecordNotFound.class)
	public ResponseEntity<ErrorResponse> handleNoUserFound(RecordNotFound ex, WebRequest req){
		List<String> details = new ArrayList<String>();
		Object[] args = new Object[] {"test"};
		String msg = messageSource.getMessage("username.student.NotFound",  args, null);
		details.add(msg);
		ErrorResponse error = new ErrorResponse("INNCORRECT REQUEST", details);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<String>();
		for(FieldError err : ex.getBindingResult().getFieldErrors()) {
			System.out.println(err);
			
			//System.out.println(messageSource.getMessage("NotEmpty.student.firstname", null,Locale.ENGLISH));
			details.add(messageSource.getMessage(err.getCodes()[0], null, null));
		}
		//details.add(ex.getBindingResult().getAllErrors());
		ErrorResponse error = new ErrorResponse("BAD_REQUEST",details);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		System.out.println("Mismatch");
		System.out.println(ex.getLocalizedMessage());
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		System.out.println("MissingPathVariable");
		System.out.println(ex.getLocalizedMessage());
		return super.handleMissingPathVariable(ex, headers, status, request);
	}
	
	
	
	
	
	
	
}
