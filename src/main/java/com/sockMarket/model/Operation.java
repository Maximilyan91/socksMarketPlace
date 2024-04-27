package com.sockMarket.model;

import com.sockMarket.model.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Operation {

    private OperationType type;
    private String dateTime;
    private Sock sock;
}
