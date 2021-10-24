package com.lottery.api;

import java.util.List;

public interface UnluckyNumbers {


    /**
     * reads the sting from the file, extracts list of the numbers from string separated by a space
     * methods sends an log.error
     * @throws java.io.IOException
     * @return a list of numbers
     */

    List<Integer> getUnluckyNumbers();

    /**
     * reads a string of numbers from the console,
     * extracts numbers from the string, checks for conditions and adds to a file
     * methods sends an log.error
     * @throws com.lottery.exceptions.IncorrectRangeOfUnluckyNumber
     * @throws NumberFormatException
     */

    void addUnluckyNumbers();

    /**
     * adds an empty line to the file
     * methods sends an log.error
     * @throws java.io.IOException
     */

    void removeUnluckyNumbers();
}
