package com.sockMarket.service.impl;

import com.sockMarket.exception.ValidationException;
import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;
import com.sockMarket.service.Validation;
import com.sockMarket.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final static TreeMap<Color, Map<Size, Sock>> socks = new TreeMap<>();

    private final Validation validation;

    public WarehouseServiceImpl(Validation validation) {
        this.validation = validation;
    }


    @Override
    public TreeMap<Color, Map<Size, Sock>> addSocks(Sock sock) {

        if (!validation.validateSock(sock)) {
            throw new ValidationException(sock.toString());
        }
        Color color = sock.getColor();
        Map<Size, Sock> oneColorSocks = socks.getOrDefault(color, new LinkedHashMap<>());
        Size size = sock.getSize();

        if (oneColorSocks.containsKey(size)) {
            long newQuantity = oneColorSocks.get(size).getQuantity() + sock.getQuantity();
            oneColorSocks.get(size).setQuantity(newQuantity);
        } else {
            oneColorSocks.put(size, sock);
        }
        socks.put(color, oneColorSocks);
        return socks;
    }

    @Override
    public TreeMap<Color, Map<Size, Sock>> getAllSocks() {
        return socks;
    }


}
