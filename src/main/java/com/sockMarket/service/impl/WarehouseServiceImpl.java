package com.sockMarket.service.impl;

import com.sockMarket.exception.ValidationException;
import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;
import com.sockMarket.service.Validation;
import com.sockMarket.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final static List<Sock> socks = new ArrayList<>();

    private final Validation validation;

    public WarehouseServiceImpl(Validation validation) {
        this.validation = validation;
    }


    @Override
    public List<Sock> addSocks(Sock sock) {
        if (!validation.validateSock(sock)) {
            throw new ValidationException(sock.toString());
        }

        if (socks.isEmpty()) {
            socks.add(sock);
            return socks;
        }

        if (socks.contains(sock)) {

            Sock sockFromList = socks.get(socks.indexOf(sock));
            sockFromList.setQuantity(sockFromList.getQuantity() + sock.getQuantity());
        } else {
            socks.add(sock);
        }
        return socks;
    }

    @Override
    public List<Sock> getAllSocks() {
        return socks;
    }


}
