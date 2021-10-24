package com.lottery.api;

public interface Lottery extends UnluckyNumbers {
    /**
     *displays a series of randomly generated numbers under certain conditions
     * and takes account of the list of numbers that the method getUnluckyNumbers() returns
     *
     */
    void generateNumbers();
    /**
     * @return game name
     */
    String getLotteryName();
}
