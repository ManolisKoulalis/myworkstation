
package com.customers.customerapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCustomerNotFound(CustomerNotFoundException exception,Model model) {

        model.addAttribute("errorMessage",
                "Ο πελάτης που ζητήσατε δεν βρέθηκε."
        );

        return "error/404";
    }

    @ExceptionHandler(WorkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleWorkNotFound(WorkNotFoundException exception,Model model) {

        model.addAttribute("errorMessage",
                "Η εργασία που ζητήσατε δεν βρέθηκε."
        );

        return "error/404";
    }
}
