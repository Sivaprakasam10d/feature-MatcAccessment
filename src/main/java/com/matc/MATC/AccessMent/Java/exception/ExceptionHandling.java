package com.matc.MATC.AccessMent.Java.exception;

import com.matc.MATC.AccessMent.Java.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling 
{
	 @ExceptionHandler(value = EmployeeNotExistsException.class)
	 public ResponseEntity<Object> exception( EmployeeNotExistsException exception)
	 {
		 ErrorResponse errorResponse = new ErrorResponse();
		 errorResponse.setStatus(404);
		 errorResponse.setMessage("Employee not found");
		 errorResponse.setSuccess(false);
		 return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(value = UserNotFoundException.class)
	 public ResponseEntity<Object> exception(UserNotFoundException exception)
	 {
		 ErrorResponse errorResponse = new ErrorResponse();
		 errorResponse.setStatus(404);
		 errorResponse.setMessage("User not found");
		 errorResponse.setSuccess(false);
		 return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	 }

	@ExceptionHandler(value = UserAlreadyExistException.class)
	public ResponseEntity<Object> exception(UserAlreadyExistException exception)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(409);
		errorResponse.setMessage("user Already Exist");
		errorResponse.setSuccess(false);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = AddressNotFoundException.class)
	public ResponseEntity<Object> exception(AddressNotFoundException exception)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(400);
		errorResponse.setMessage("Address not found");
		errorResponse.setSuccess(false);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PersonNotFoundException.class)
	public ResponseEntity<Object> exception(PersonNotFoundException exception)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(404);
		errorResponse.setMessage("person not found");
		errorResponse.setSuccess(false);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
