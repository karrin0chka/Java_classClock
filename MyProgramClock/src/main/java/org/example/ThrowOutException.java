package org.example;

public class ThrowOutException extends Exception {
    private int i = 0;
    public ThrowOutException(int i, String message) {
        super(message);
        this.i = i;
    }
    @Override
    public  String toString() {
        return "ThrowOutException{" + "i = " + i + "}:" + this.getMessage();
    }
}