package com.lottery.api.eurojackpot;

import com.lottery.api.BaseLottery;

public class EuroJackPot5From50 extends BaseLottery {

    protected EuroJackPot5From50(String lotteryName) {
        super(lotteryName, 1, 50, 5,
                "unluckyNumber5aus50.log");
    }
}