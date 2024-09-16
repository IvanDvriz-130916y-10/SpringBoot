package ru.dvornikov.SpringBoot2.service;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.dvornikov.SpringBoot2.exception.ValidationFailedException;
@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult, String uid) throws ValidationFailedException;

    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}