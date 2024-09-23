package ru.dvornikov.SpringBoot2.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Systems {
    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Management System");

    private final String description;

    Systems(String description) {
        this.description = description;
    }

    @JsonValue
    public String getName() {
        return name();
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static Systems fromValue(String value) {
        for (Systems system : Systems.values()) {
            if (system.name().equalsIgnoreCase(value)) {
                return system;
            }
        }
        throw new IllegalArgumentException("Unknown value for systemName: " + value);
    }
}