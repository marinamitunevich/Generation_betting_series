package com.lottery.api.eurojackpot;

import com.lottery.api.Lottery;

import java.util.List;

public class EuroJackPotLottery implements Lottery {

    protected EuroJackPot5From50 euroJackPot5From50;
    protected EuroJackPot2From10 euroJackPot2From10;
    protected String nameLottery;

    public EuroJackPotLottery() {

        this.nameLottery = "EuroJackPot";
        euroJackPot5From50 = new EuroJackPot5From50();
        euroJackPot2From10 = new EuroJackPot2From10();
    }


    @Override
    public void generateNumbers() {

        euroJackPot5From50.generateNumbers();

        euroJackPot2From10.generateNumbers();
    }

    @Override
    public String getLotteryName() {
        return this.nameLottery;
    }

    @Override
    public List<Integer> getUnluckyNumbers() {

        throw new UnsupportedOperationException("Not allowed here");
    }

    @Override
    public void addUnluckyNumbers() {

        throw new UnsupportedOperationException("Not allowed here");
    }

    @Override
    public void removeUnluckyNumbers() {

        throw new UnsupportedOperationException("Not allowed here");
    }
}
