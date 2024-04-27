package com.sockMarket.model;

import com.sockMarket.model.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    private OperationType type;
    private String dateTime;
    private Sock sock;
}
