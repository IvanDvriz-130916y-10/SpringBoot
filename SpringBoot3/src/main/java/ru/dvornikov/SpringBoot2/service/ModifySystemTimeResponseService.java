package ru.dvornikov.SpringBoot2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.dvornikov.SpringBoot2.model.Response;
import ru.dvornikov.SpringBoot2.util.DateTimeUtil;
import java.util.Date;

@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {

        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date ()));
        return response;
    }
}