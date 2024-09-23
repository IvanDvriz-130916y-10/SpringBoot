package ru.dvornikov.SpringBoot2.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class Request {

    @NotNull(message = "uid не должен быть пустым")
    private String uid;

    @NotNull(message = "requestSystem не должен быть пустым")
    private String requestSystem;

    @NotNull(message = "requestTimeStamp не должен быть пустым")
    private String requestTimeStamp;

    @NotNull(message = "operationUid не должен быть пустым")
    private String operationUid;

    @NotNull(message = "systemName не должен быть пустым")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Systems systemName;
}