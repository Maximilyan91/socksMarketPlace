package com.sockMarket.service.impl;

import com.sockMarket.exception.NegativeQuantityException;
import com.sockMarket.exception.ValidationException;
import com.sockMarket.model.Sock;
import com.sockMarket.service.Validation;
import com.sockMarket.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public Sock releaseSocks(Sock sock) {
        if (!validation.validateSock(sock)) {
            throw new ValidationException(sock.toString());
        }

        if (!socks.contains(sock)) {
            throw new NoSuchElementException("Таких носков на складе нет");
        }

        Sock sockFromList = socks.get(socks.indexOf(sock));
        long newQuantity = sockFromList.getQuantity() - sock.getQuantity();

        if (newQuantity < 0) {
            throw new NegativeQuantityException(
                    "Вы пытаетесь отгрузить носков больше чем осталось на складе: " + sockFromList.getQuantity());
        }
        sockFromList.setQuantity(newQuantity);
        return sockFromList;
    }

    @Override
    public Sock get(Sock sock) {
        if (!validation.validateSock(sock)) {
            throw new ValidationException(sock.toString());
        }
        if (!socks.contains(sock)) {
            throw new NoSuchElementException("Таких носков на складе нет");
        }

        return socks.get(socks.indexOf(sock));
    }
}
