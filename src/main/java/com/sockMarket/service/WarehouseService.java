package com.sockMarket.service;

import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface WarehouseService {
    List<Sock> addSocks(Sock sock);

    List<Sock> getAllSocks();
}
