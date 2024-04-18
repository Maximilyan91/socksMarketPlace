package com.sockMarket.model;

import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sock {

    private Color color;

    private Size size;

    private int cottonPart;

    private long quantity;
}
