package com.sockMarket.service;

import com.sockMarket.model.Sock;

public interface Validation {

    boolean validateSock(Sock sock);

    boolean validateCottonPart(int min, int max);
}
