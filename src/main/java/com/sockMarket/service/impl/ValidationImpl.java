package com.sockMarket.service.impl;

import com.sockMarket.model.Sock;
import com.sockMarket.service.Validation;
import org.springframework.stereotype.Service;

@Service
public class ValidationImpl implements Validation {
    @Override
    public boolean validateSock(Sock sock) {
        return sock != null &&
                sock.getCottonPart() > 0 &&
                sock.getCottonPart() < 101 &&
                sock.getQuantity() > 0;
    }

    @Override
    public boolean validateCottonPart(int min, int max) {
        return min >= 0 && min <= 100 && max >= 0 && max <= 100 ;
    }
}
