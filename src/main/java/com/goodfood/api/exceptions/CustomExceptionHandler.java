package com.goodfood.api.exceptions;

import com.goodfood.api.exceptions.comments.CommentsNotFoundException;
import com.goodfood.api.exceptions.customers.CustomersNotFoundException;
import com.goodfood.api.exceptions.customers.CustomersValidationException;
import com.goodfood.api.exceptions.employees.EmployeeValidationException;
import com.goodfood.api.exceptions.employees.EmployeesNotFoundException;
import com.goodfood.api.exceptions.products.ProductsNotFoundException;
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

    @ExceptionHandler(CommentsNotFoundException.class)
    public final ResponseEntity<Object> handleCommentNotFoundException(CommentsNotFoundException ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add( ex.getReason() );
        ErrorResponse error = new ErrorResponse( ex.getStatus(), "Comment not found", details );
        return new ResponseEntity( error, error.getStatus() );
    }

    @ExceptionHandler(CustomersNotFoundException.class)
    public final ResponseEntity<Object> handleCustomerNotFoundException(CustomersNotFoundException ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add( ex.getReason() );
        ErrorResponse error = new ErrorResponse( ex.getStatus(), "Customer not found", details );
        return new ResponseEntity( error, error.getStatus() );
    }

    @ExceptionHandler(CustomersValidationException.class)
    public final ResponseEntity<Object> handleCustomerValidationException(CustomersValidationException ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add( ex.getReason() );
        ErrorResponse error = new ErrorResponse( ex.getStatus(), "Customer not validated", details );
        return new ResponseEntity( error, error.getStatus() );
    }

    @ExceptionHandler(EmployeesNotFoundException.class)
    public final ResponseEntity<Object> handleEmployeeNotFoundException(EmployeesNotFoundException ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add( ex.getReason() );
        ErrorResponse error = new ErrorResponse( ex.getStatus(), "Employee not found", details );
        return new ResponseEntity( error, error.getStatus() );
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

    @ExceptionHandler(ProductsNotFoundException.class)
    public final ResponseEntity<Object> handleProductNotFoundException(ProductsNotFoundException ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add( ex.getReason() );
        ErrorResponse error = new ErrorResponse( ex.getStatus(), "Product not found", details );
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
