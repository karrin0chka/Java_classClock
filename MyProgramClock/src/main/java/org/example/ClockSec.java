package org.example;

public class ClockSec extends Clock {
    protected int second = 0;
    public ClockSec(String stamp, int price) {
        super(stamp, price);
    }
    public ClockSec(String stamp, int price, int hour, int minute, int second) {
        super(stamp, price, hour, minute);
        this.second = second;
    }
    @Override
    public void SetTime(Hand hand, int value) throws ThrowOutException {
        if (hand == Hand.SECOND) {
            if (value > 59)
                throw new ThrowOutException(value, " Incorrect second\n");
            else
                this.second = value;
        }
        super.SetTime(hand, value);
    }
    @Override
    public void AddTime(Hand hand, int value) throws ThrowOutException {
        if (hand == Hand.SECOND)
            this.second += value;
        if (this.second > 59) {
            this.hour += this.second / 3600;
            this.minute += (this.second / 60) % 60;
            this.second %= 60;
        }
        super.AddTime(hand, value);
    }
    @Override
    public String toString() {
        return "Stamp - " + stamp + "   Price - " + price + "  Time - " + hour + ":" + minute + ":" + second + "\n";

    }
}