package com.example.bankmanager.entity.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommonResponse {
    private String code;
    private String message;

    private Object value;

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
