package ru.dvornikov.SpringBoot2.service;


import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.dvornikov.SpringBoot2.exception.ValidationFailedException;
import ru.dvornikov.SpringBoot2.exception.ValidationFailedException.UnsupportedCodeException;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult, String uid) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            if (uid.equals("123")) {
                throw new UnsupportedCodeException("Unsupported uid: 123");
            }
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {

    }
}