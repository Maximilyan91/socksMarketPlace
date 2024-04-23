package com.sockMarket.service;

import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;

import java.util.List;

public interface WarehouseService {
    void addSocks(Sock sock);

    List<Sock> getAllSocks();

    Sock releaseSocks(Sock sock);

    List<Sock> getByCotton(Color color, Size size, Integer cottonMin, Integer cottonMax);

    Sock deleteSock(Sock sock);
}
