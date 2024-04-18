package com.sockMarket.model.enums;

import lombok.Getter;

@Getter
public enum Color {

    WHITE("белый"),
    BLACK("черный"),
    RED("красный"),
    YELLOW("желтый"),
    GREEN("зеленый"),
    BLUE("голубой"),
    GREY("серый");

    private final String name;

    Color(String name) {
        this.name = name;
    }
}
