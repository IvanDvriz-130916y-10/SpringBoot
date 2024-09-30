package ru.ivan.SpringBoot2_5.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ivan.SpringBoot2_5.exception.UnsupportedCodeException;
import ru.ivan.SpringBoot2_5.exception.ValidationFailedException;
import ru.ivan.SpringBoot2_5.model.*;
import ru.ivan.SpringBoot2_5.service.ModifyOperationUidResponseService;
import ru.ivan.SpringBoot2_5.service.ValidationService;
import ru.ivan.SpringBoot2_5.util.DateTimeUtil;
import java.util.Date;
@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyOperationUidResponseService modifyResponceService;
    @Autowired
    public MyController(ValidationService validationService, ModifyOperationUidResponseService modifyResponceService) {
        this.validationService = validationService;
        this.modifyResponceService = modifyResponceService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("Request received: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessages(ErrorMessages.EMPTY).build();

        try {
            validationService.isValid(bindingResult);
            // Проверка на uid = 123
            if (request.getUid().equals("123")) {
                throw new UnsupportedCodeException("Unsupported uid: 123");
            }
            modifyResponceService.modify(response);
            log.info("Response sent: {}", response);
        } catch (UnsupportedCodeException e) {
            log.error("Unsupported uid: 123", e);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessages(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ValidationFailedException e) {
            log.error("Validation failed: {}", bindingResult.getFieldErrors(), e);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessages(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unknown exception occurred", e);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessages(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
