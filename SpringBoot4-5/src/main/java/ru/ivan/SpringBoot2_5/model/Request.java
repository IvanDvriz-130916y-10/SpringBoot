package ru.ivan.SpringBoot2_5.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank
    private String uid;

    private String operationUid;
    private String systemName;
    private String systemTime;
    private String source;
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;
    private long timestamp;

    @Override

    public String toString() {
        return "{" +
                "uid= '" + uid + '\'' +
                ", operationUid= '" + operationUid + '\'' +
                ", systemName= '" + systemName + '\'' +
                ", systemTime= '" + systemTime + '\'' +
                ", source= '" + source + '\'' +
                ", communicationId= '" + communicationId + '\'' +
                ",  templateId= '" + templateId + '\'' +
                ",  productCode= '" + productCode + '\'' +
                ",  smsCode= '" + smsCode + '\'' +
                '}';
    }
}