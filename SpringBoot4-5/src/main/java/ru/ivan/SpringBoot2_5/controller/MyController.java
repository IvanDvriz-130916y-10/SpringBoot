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
import ru.ivan.SpringBoot2_5.service.ModifyRequestService;
import ru.ivan.SpringBoot2_5.service.ValidationService;
import ru.ivan.SpringBoot2_5.util.DateTimeUtil;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyOperationUidResponseService modifyResponceService;

    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService, ModifyOperationUidResponseService modifyResponceService, ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyResponceService = modifyResponceService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("Request received: {}", request);

        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - request.getTimestamp();
        log.info("Time difference from Service 1 to Service 2: {} ms", timeDifference);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            validationService.isValid(bindingResult);

            if (request.getUid().equals("123")) {
                throw new UnsupportedCodeException("Unsupported uid: 123");
            }

            modifyResponceService.modify(response);
            log.info("Response sent: {}", response);
        } catch (UnsupportedCodeException e) {
            log.error("Unsupported uid: 123", e);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ValidationFailedException e) {
            log.error("Validation failed: {}", bindingResult.getFieldErrors(), e);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unknown exception occurred", e);
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}