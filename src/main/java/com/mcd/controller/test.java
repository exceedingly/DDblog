package com.mcd.controller;

public class test {
    public static void main(String[] args) {
        String a = "123";
        String b = "123";
        System.out.println(a==b);
        String c = new String("123");
        System.out.println(c==b);
    }
}
