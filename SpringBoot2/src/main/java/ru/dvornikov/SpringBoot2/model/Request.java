package ru.dvornikov.SpringBoot2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    private String uid;
    private String operationUid;
    private String systemName;
    private String systemTime;
    private String source;
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;
    private BindingResult bindingResult;

    public boolean isValid() {
        return uid != null && !uid.isEmpty()
                && operationUid != null && !operationUid.isEmpty()
                && systemTime != null && !systemTime.isEmpty()
                && communicationId > 0;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}