package com.sockMarket.service;

import com.sockMarket.model.Operation;

import java.util.List;

public interface OperationsFileService {
    void saveOperation(List<Operation> operations);

    void createFile();

    String readFromFile();
}
