package ru.ivan.SpringBoot2_5.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.ivan.SpringBoot2_5.exception.ValidationFailedException;
import ru.ivan.SpringBoot2_5.service.RequestValidationService;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()){
            throw new
                    ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}