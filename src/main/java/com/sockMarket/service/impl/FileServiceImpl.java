package com.sockMarket.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sockMarket.model.Sock;
import com.sockMarket.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Value("${path.to.data.file}")
    private String dataFilePath;

    @Value("${name.of.data.file}")
    private String dataFileName;

    @Override
    public void saveToFile(List<Sock> socks) {
        Path path = Path.of(dataFilePath, dataFileName);

        try {
            String json = new ObjectMapper().writeValueAsString(socks);

            if (!Files.exists(path)) {
                createFile();
            }
            Files.writeString(path, json);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createFile() {
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromFile() {
        Path path = Path.of(dataFilePath, dataFileName);
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);
    }

    @Override
    public boolean uploadDataFile(MultipartFile file) {

        try (FileOutputStream fos = new FileOutputStream(getDataFile())) {
            IOUtils.copy(file.getInputStream(), fos);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
