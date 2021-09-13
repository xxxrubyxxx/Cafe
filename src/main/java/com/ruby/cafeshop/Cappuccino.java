package com.ruby.cafeshop;

public class Cappuccino extends CafeShop{

    public void makeCappuccino(String name, Object cappuccino) {
        super.setCafe(name, cappuccino);
    }

    public Cafe gotCappuccino(String name) {
        Cafe cafe = super.getCafe(name);
        if (cafe == null) {
            throw new IllegalArgumentException("Api not found");
        }
        return cafe;
    }
}
