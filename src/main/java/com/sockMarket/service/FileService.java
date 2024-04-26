package com.sockMarket.service;

import com.sockMarket.model.Sock;

import java.util.List;

public interface FileService {
    void saveToFile(List<Sock> socks);

    void createFile();

    String readFromFile();
}
