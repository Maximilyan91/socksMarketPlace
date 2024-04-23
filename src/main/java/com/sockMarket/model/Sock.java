package com.sockMarket.model;

import com.sockMarket.model.enums.Color;
import com.sockMarket.model.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sock {

    private Color color;

    private Size size;

    private int cottonPart;

    private long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sock sock)) return false;
        return cottonPart == sock.cottonPart && color == sock.color && size == sock.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonPart);
    }
}
