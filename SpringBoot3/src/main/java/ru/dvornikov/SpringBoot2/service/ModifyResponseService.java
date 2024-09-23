package ru.dvornikov.SpringBoot2.service;


import org.springframework. stereotype.Service;
import ru.dvornikov.SpringBoot2.model.Response;

    @Service
    public interface ModifyResponseService {
        Response modify (Response response);
    }
