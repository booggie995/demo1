package com.example.demo.exception;

import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestValidationHandler {

    private MessageSource messageSource;

    @Autowired
    public RestValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<FieldValidationErrorDetails> handleValidationError(MethodArgumentNotValidException exception,
                                                                             HttpServletRequest request) {

        FieldValidationErrorDetails errorDetails = new FieldValidationErrorDetails();

        errorDetails.setErrorTimeStamp(new Date().getTime());
        errorDetails.setErrorStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setErrorTitle("Field Validation Error");
        errorDetails.setErrorDetail("Inut Field Validation Failed");
        errorDetails.setErrorDeveloperMessage(exception.getClass().getName());
        errorDetails.setErrorPath(request.getRequestURI());

        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError error : fieldErrors) {
            FieldValidationError fieldError = processFieldError(error);
            List<FieldValidationError> errorList = errorDetails.getErrors().get(error.getField());
            if (errorList == null) {
                errorList = new ArrayList<>();
            }
            errorList.add(fieldError);
            errorDetails.getErrors().put(error.getField(), errorList);
        }

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // method to process field error
    private FieldValidationError processFieldError(final FieldError error) {
        FieldValidationError fieldValidationError =
                new FieldValidationError();
        if (error != null) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String msg = messageSource.getMessage(error.getDefaultMessage(), null, currentLocale);
            fieldValidationError.setFiled(error.getField());
            fieldValidationError.setType(MessageType.ERROR);
            fieldValidationError.setMessage(msg);
        }
        return fieldValidationError;
    }
}
