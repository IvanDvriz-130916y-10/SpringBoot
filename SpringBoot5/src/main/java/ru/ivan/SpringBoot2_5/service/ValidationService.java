package ru.ivan.SpringBoot2_5.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.ivan.SpringBoot2_5.exception.ValidationFailedException;
@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}