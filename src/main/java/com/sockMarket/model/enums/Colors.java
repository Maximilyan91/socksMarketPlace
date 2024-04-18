package com.sockMarket.model.enums;

public enum Colors {

    WHITE("белый"),
    BLACK("черный"),
    RED("красный"),
    YELLOW("желтый"),
    GREEN("зеленый"),
    BLUE("голубой"),
    GREY("серый");

    private final String name;

    Colors(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
