package org.example;

public interface ClockIn {
    void SetTime(Hand hand, int value) throws ThrowOutException;
    void AddTime(Hand hand, int value) throws  ThrowOutException;
    String getStamp();
    int getPrice();
    String toString();
}