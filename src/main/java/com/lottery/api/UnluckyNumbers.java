package com.lottery.api;

import java.util.List;

public interface UnluckyNumbers {

    /**
     * reads the sting from the file, extracts list of the numbers from string separated by a space
     * methods sends an log.error
     *
     * @return a list of numbers
     * @throws java.io.IOException
     * @throws IndexOutOfBoundsException
     */

    List<Integer> getUnluckyNumbers();

    /**
     * reads a string of numbers from the console,
     * extracts numbers from the string, checks for conditions and adds to a file
     * methods sends an log.error
     *
     * @throws com.lottery.exceptions.IncorrectRangeOfUnluckyNumber the string line does not satisfy the condition of the if operator
     * @throws NumberFormatException                                if the string contains non-numbers
     */

    void addUnluckyNumbers();

    /**
     * adds an empty line to the file
     * methods sends an log.error
     *
     * @throws java.io.IOException
     */

    void removeUnluckyNumbers();
}
