package com.goodfood.api.exceptions;

import com.goodfood.api.exceptions.employees.EmployeeValidationException;
import com.goodfood.api.exceptions.products.ProductsValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", details);
        return new ResponseEntity(error, error.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getReason());
        ErrorResponse error = new ErrorResponse(ex.getStatus(), "Constraint violation", details);
        return new ResponseEntity(error, error.getStatus());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<Object> handleStatusException(ResponseStatusException ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getReason());
        ErrorResponse error = new ErrorResponse(ex.getStatus(), "", details);
        return new ResponseEntity(error, error.getStatus());
    }

    @ExceptionHandler(EmployeeValidationException.class)
    public final ResponseEntity<Object> handleEmployeeValidationException(EmployeeValidationException ex,
                                                                         WebRequest request )
    {
        List<String> details = new ArrayList<>();
        details.add( ex.getReason() );
        ErrorResponse error = new ErrorResponse( ex.getStatus(), "Employee not validated", details );
        return new ResponseEntity( error, error.getStatus() );
    }

    @ExceptionHandler(ProductsValidationException.class)
    public final ResponseEntity<Object> handleProductsValidationException(ProductsValidationException ex,
                                                                          WebRequest request )
    {
        List<String> details = new ArrayList<>();
        details.add( ex.getReason() );
        ErrorResponse error = new ErrorResponse( ex.getStatus(), "Product not validated", details );
        return new ResponseEntity( error, error.getStatus() );
    }

}
