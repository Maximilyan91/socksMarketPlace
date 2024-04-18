package com.sockMarket.service;

import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;

import java.util.Map;
import java.util.TreeMap;

public interface WarehouseService {
    TreeMap<Color, Map<Size, Sock>> addSocks(Sock sock);

    TreeMap<Color, Map<Size, Sock>> getAllSocks();
}
