package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Tolo {
    private ArrayList<Integer> drawnNumbers;

    public Tolo() {
        drawnNumbers = new ArrayList<>();
        drawNumbers();
    }

    private void drawNumbers() {
        drawnNumbers.clear();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(20) + 1;
            while (drawnNumbers.contains(number)) {
                number = random.nextInt(20) + 1;
            }
            drawnNumbers.add(number);
        }
        int luckyNumber = random.nextInt(10) + 1;
        drawnNumbers.add(luckyNumber);
    }

    public int getGain(ArrayList<Integer> userNumbers, int userLuckyNumber, int betMoney) {
        int matchCount = 0;
        int payout = 0;

        // Check how many of the user's numbers match the drawn numbers
        for (int userNumber : userNumbers) {
            if (drawnNumbers.contains(userNumber)) {
                matchCount++;
            }
        }

        if (matchCount == 4) {
            payout = 50 * betMoney;
            if (userLuckyNumber == drawnNumbers.get(4)) {
                return 5 * payout;
            } else {
                return payout;
            }
        } else if (matchCount == 3) {
            payout = 5 * betMoney;
            if (userLuckyNumber == drawnNumbers.get(4)) {
                return 5 * payout;
            } else {
                return payout;
            }
        } else {
            return 0; // no macth 	
    }
    }
}
