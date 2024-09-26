package ru.ivan.SpringBoot2_5.service;

import org.springframework.stereotype.Service;
import ru.ivan.SpringBoot2_5.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}

