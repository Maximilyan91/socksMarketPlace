package com.sockMarket.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sockMarket.model.Operation;
import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.OperationType;
import com.sockMarket.service.OperationService;
import com.sockMarket.service.OperationsFileService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private static List<Operation> operations = new ArrayList<>();

    private final OperationsFileService fileService;

    public OperationServiceImpl(OperationsFileService fileService) {
        this.fileService = fileService;
    }


    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public void addOperation(Sock sock, String type) {
       String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss"));

        Operation operation = new Operation(OperationType.valueOf(type), time, sock);
        operations.add(operation);
        fileService.saveOperation(operations);
    }

    public void readFromFile() {
        String json = fileService.readFromFile();

        if (json.isEmpty()) {
            return;
        }

        try {
            operations = new ObjectMapper().readValue(json, new TypeReference<List<Operation>>() {
                    }
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
