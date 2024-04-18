package com.sockMarket.service.impl;

import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;
import com.sockMarket.service.WarehouseService;

import java.util.Map;
import java.util.TreeMap;

public class WarehouseServiceImpl implements WarehouseService {

    private final static Map<Color, Map<Size, Sock>> socks = new TreeMap<>();

    public void addSocks(Sock sock) {

    }

}
