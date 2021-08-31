package com.popspot.challenge.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponsePayload {
    String message;
    Object payload;
}
