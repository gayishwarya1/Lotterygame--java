package org.example;

import java.util.ArrayList;

public class SuperBet {
    private ArrayList<Integer> numbers;
    private int luckyNumber;
    private int betMoney;

    public SuperBet(ArrayList<Integer> userNumbers, int userLuckyNumber, int betMoney) {
        super();
        this.numbers = userNumbers;
        this.luckyNumber = userLuckyNumber;
        this.betMoney = betMoney;
    }

    public int computeGain(Tolo tolo) {
        return tolo.getGain(numbers, luckyNumber, betMoney);
    }
}
