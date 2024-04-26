package com.sockMarket.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sockMarket.exception.NegativeQuantityException;
import com.sockMarket.exception.ValidationException;
import com.sockMarket.model.Sock;
import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;
import com.sockMarket.service.FileService;
import com.sockMarket.service.Validation;
import com.sockMarket.service.WarehouseService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private static List<Sock> socks = new ArrayList<>();

    private final Validation validation;

    private final FileService fileService;

    public WarehouseServiceImpl(Validation validation, FileService fileService) {
        this.validation = validation;
        this.fileService = fileService;
    }

    @Override
    public void addSocks(Sock sock) {
        if (!validation.validateSock(sock)) {
            throw new ValidationException(sock.toString());
        }

        if (socks.contains(sock)) {

            Sock sockFromList = socks.get(socks.indexOf(sock));
            sockFromList.setQuantity(sockFromList.getQuantity() + sock.getQuantity());

        } else {
            socks.add(sock);
            fileService.saveToFile(socks);
        }
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

        if (newQuantity == 0) {
            socks.remove(sockFromList);
        }

        sockFromList.setQuantity(newQuantity);
        return sockFromList;
    }

    @Override
    public List<Sock> getByCotton(Color color, Size size, Integer cottonMin, Integer cottonMax) {

        if (!validation.validateCottonPart(cottonMin, cottonMax)) {
            throw new ValidationException("значение cotton не может быть равно " + cottonMin + " или " + cottonMax);
        }

        List<Sock> neededSocks = new ArrayList<>();

        for (Sock sock : socks) {

            if (sock.getColor().equals(color) &&
                    sock.getSize().equals(size) &&
                    sock.getCottonPart() >= cottonMin &&
                    sock.getCottonPart() <= cottonMax) {
                neededSocks.add(sock);
            }
        }
        return neededSocks;
    }

    @Override
    public Sock deleteSock(Sock sock) {
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

        if (newQuantity == 0) {
            socks.remove(sockFromList);
        }

        sockFromList.setQuantity(newQuantity);
        return sockFromList;
    }

    @Override
    public void readFromFile() {
        String json = fileService.readFromFile();

        if (json.isEmpty()) {
            return;
        }
        try {
            socks = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }
}
