package com.sockMarket.service.impl;

import com.sockMarket.model.Sock;
import com.sockMarket.service.Validation;

public class ValidationImpl implements Validation {

    public boolean validateSock(Sock sock) {
        return sock != null &&
                sock.getCottonPart() >= 1 &&
                sock.getCottonPart() <= 100 &&
                sock.getQuantity() >= 1;
    }
}
