package com.shree.batch.utils;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {
    private static final long serialVersionUID = 2119411267246013200L;
    private String message;
    private int statusCode;

    public Response(String message) {
        this.message = message;
    }
}
