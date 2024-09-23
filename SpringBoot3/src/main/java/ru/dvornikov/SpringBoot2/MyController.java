package ru.dvornikov.SpringBoot2;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dvornikov.SpringBoot2.exception.UnsupportedCodeException;
import ru.dvornikov.SpringBoot2.exception.ValidationFailedException;
import ru.dvornikov.SpringBoot2.*;
import ru.dvornikov.SpringBoot2.model.*;
import ru.dvornikov.SpringBoot2.service.ModifyResponseService;
import ru.dvornikov.SpringBoot2.service.ValidationService;
import ru.dvornikov.SpringBoot2.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        log.info("Получен запрос: {}", request);

        // Создаем начальный ответ
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessage.EMPTY)
                .build();

        log.info("Инициализирован ответ: {}", response);

        try {
            // Проверяем валидность запроса
            validationService.isValid(bindingResult);
            log.info("Запрос прошел валидацию: {}", request);
        } catch (ValidationFailedException e) {
            // Обработка ошибки валидации
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessage.VALIDATION);

            // Логируем ошибки из bindingResult
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(error -> {
                    log.error("Ошибка валидации: {}", error.getDefaultMessage());
                });
            }

            log.error("Ошибка валидации для запроса {}: {}", request, e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Обработка неизвестных ошибок
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessage.UNKNOWN);

            log.error("Неизвестная ошибка при обработке запроса {}: {}", request, e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Модифицируем ответ с помощью сервиса
        Response modifiedResponse = modifyResponseService.modify(response);
        log.info("Ответ после модификации: {}", modifiedResponse);

        return new ResponseEntity<>(modifiedResponse, HttpStatus.OK);
    }
}