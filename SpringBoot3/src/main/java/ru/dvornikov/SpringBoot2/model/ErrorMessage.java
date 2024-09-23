package ru.dvornikov.SpringBoot2.model;

import com.fasterxml.jackson. annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonValue;
public enum ErrorMessage {

    EMPTY(""),
    VALIDATION("Ошибка валидации"),
    UNSUPPORTED("произошла непредвиденная ошибка"),
    UNKNOWN("не поддерживаемая ошибка");

    private final String descriprion;


    ErrorMessage(String descriprion) {
        this.descriprion = descriprion;
    }

    @JsonValue
    public String getName(){
        return descriprion;
    }
}