package org.example;

public class Clock extends Throwable implements ClockIn {
    public int hour = 0, minute = 0, price = 0;
    public String stamp;
    Clock(String stamp, int price) {
        this.stamp = stamp;
        this.price = price;
    }
    Clock(String stamp, int price, int hour, int minute) {
        super();
        this.hour = hour;
        this.minute = minute;
        this.stamp = stamp;
        this.price = price;
    }
    @Override
    public void SetTime(Hand hand, int value) throws ThrowOutException {
        if (value < 0)
            throw new ThrowOutException(value, " Value must be positive only\n");
        if (hand == Hand.HOUR) {
            if (value > 23)
                throw new ThrowOutException(value, " Incorrect hour\n");
            else
                this.hour = value;
        }
        if (hand == Hand.MINUTE) {
            if (value > 59)
                throw new ThrowOutException(value, " Incorrect minute\n");
            else
                this.minute = value;
        }
    }
    @Override
    public void AddTime(Hand hand, int value) throws  ThrowOutException {
        if (value < 0)
            throw new ThrowOutException(value, " Value must be positive only\n");
        if (hand == Hand.HOUR)
            this.hour += value;
        if (this.hour > 23)
            this.hour %= 24;
        if (hand == Hand.MINUTE)
            this.minute += value;
        if(this.minute > 59) {
            this.hour += this.minute / 60;
            this.minute %= 60;
        }
    }
    @Override
    public String getStamp() {
        return stamp;
    }
    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return  "Stamp - " + stamp + "   Price - " + price + "  Time - " + hour + ":" + minute + "\n";
    }
}