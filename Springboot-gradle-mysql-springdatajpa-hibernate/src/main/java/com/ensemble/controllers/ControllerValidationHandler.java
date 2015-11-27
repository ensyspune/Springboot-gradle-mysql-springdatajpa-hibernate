package com.ensemble.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.ensemble.dto.*;

@ControllerAdvice /*Serves as a specialization of @Component, allowing for implementation classes to be autodetected through classpath scanning.
It is typically used to define @ExceptionHandler, @InitBinder, and @ModelAttribute methods that apply to all @RequestMapping methods.*/
public class ControllerValidationHandler {
  @Autowired
  private MessageSource msgSource;

  @ExceptionHandler(MethodArgumentNotValidException.class) //Exception to be thrown when validation on an argument annotated with @Valid fails.
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public MessageDTO processValidationError(MethodArgumentNotValidException ex) {
    System.out.println("public MessageDTO processValidationError(MethodArgumentNotValidException ex)______in__"+this.getClass().getName());
    BindingResult result = ex.getBindingResult();
    FieldError error = result.getFieldError();
    return processFieldError(error);
  }

  private MessageDTO processFieldError(FieldError error) {
    System.out.println("MessageDTO processFieldError(FieldError error)____________in___"+this.getClass().getName());
    MessageDTO message = null;
    if (error != null) {
      Locale currentLocale = LocaleContextHolder.getLocale();
      String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
      message = new MessageDTO(MessageType.ERROR, msg);
    }
    return message;
  }
  
  
}

