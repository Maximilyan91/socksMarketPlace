package com.sockMarket.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sockMarket.model.Operation;
import com.sockMarket.service.OperationsFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class OperationsFileServiceImpl implements OperationsFileService {
    @Value("${path.to.operations.file}")
    private String dataFilePath;

    @Value("${name.of.operations.file}")
    private String dataFileName;

    @Override
    public void saveOperation(List<Operation> operations) {
        Path path = Path.of(dataFilePath, dataFileName);

        if (!Files.exists(path)) {
            createFile();
        }

        try {
            String json = new ObjectMapper().writeValueAsString(operations);
            Files.writeString(path, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createFile() {
        try {
            Files.createFile(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromFile() {
        Path path = Path.of(dataFilePath, dataFileName);

        try {
            if (!Files.exists(path)) {
                createFile();
            }
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
