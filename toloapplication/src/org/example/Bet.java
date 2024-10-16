package org.example;

import java.util.ArrayList;

public class Bet {
    private ArrayList<Integer> numbers;
    private int betMoney;

    public Bet(ArrayList<Integer> userNumbers, int betMoney) {
        this.numbers = userNumbers;
        this.betMoney = betMoney;
    }

    public int computeGain(Tolo tolo) {
        return tolo.getGain(numbers, -1, betMoney); // -1 indicates no lucky number in Bet
    }
}
