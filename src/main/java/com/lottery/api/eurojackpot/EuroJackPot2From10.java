package com.lottery.api.eurojackpot;

import com.lottery.api.BaseLottery;

public class EuroJackPot2From10 extends BaseLottery {

    protected EuroJackPot2From10(String lotteryName) {
        super(lotteryName, 1, 10,
                2, "unluckyNumber2aus10.txt");
    }

}
