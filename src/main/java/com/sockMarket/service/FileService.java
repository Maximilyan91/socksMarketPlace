package com.sockMarket.service;

import com.sockMarket.model.Sock;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {
    void saveToFile(List<Sock> socks);

    void createFile();

    String readFromFile();

    File getDataFile();

    boolean uploadDataFile(MultipartFile file);
}
