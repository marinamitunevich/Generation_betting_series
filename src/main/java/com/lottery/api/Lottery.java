package com.lottery.api;

public interface Lottery extends UnluckyNumbers {

    /**
     * displays a series of randomly generated numbers under certain conditions
     * and considers the list of numbers that the method getUnluckyNumbers() returns
     */

    void generateNumbers();

    /**
     * @return name of the game
     */

    String getLotteryName();
}
