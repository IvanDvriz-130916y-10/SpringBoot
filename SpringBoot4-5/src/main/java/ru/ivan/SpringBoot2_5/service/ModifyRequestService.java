package ru.ivan.SpringBoot2_5.service;


import org.springframework.stereotype.Service;
import ru.ivan.SpringBoot2_5.model.Request;

@Service
public interface ModifyRequestService {

    void modify(Request request);
}
