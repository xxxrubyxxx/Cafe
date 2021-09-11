package com.ruby.cafeshop;

public class Cappuccino extends CafeShop{

    public void makeCappuccino(String name, Object cappuccino) {
        super.setCafe(name, cappuccino);
    }

    public Cafe gotCappuccino(String name) {
        return super.getCafe(name);
    }
}
