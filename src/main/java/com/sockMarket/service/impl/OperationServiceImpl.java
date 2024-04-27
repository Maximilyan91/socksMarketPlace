package com.sockMarket.service.impl;

import com.sockMarket.model.Operation;
import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.OperationType;
import com.sockMarket.service.OperationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private static final List<Operation> operations = new ArrayList<>();

    @Override
    public void addOperation(Sock sock, String type) {
        Operation operation = new Operation(OperationType.valueOf(type.toLowerCase()), LocalDateTime.now(), sock);
        operations.add(operation);
    }
}
