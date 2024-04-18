package com.sockMarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sock {

    private String color;

    private int size;

    private int cottonPart;

    private long quantity;
}
