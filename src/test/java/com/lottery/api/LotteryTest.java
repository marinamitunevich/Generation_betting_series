package com.lottery.api;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LotteryTest {

    protected Path path = Paths.get("unluckyNumber6aus49.txt");
    protected Lottery lottery = new LottoLottery();

    @Test
    public void generateNumbersTest_success() {

        LottoLottery lottery = new LottoLottery();

        lottery.generateNumbers();

        String line = readFromFile();
        List<Integer> listUnluckyNumbers = Arrays.stream(line.split(" "))
                .map(s -> Integer.valueOf(s)).collect(Collectors.toList());

        assertTrue(!lottery.generatedNumbers.contains(listUnluckyNumbers));
        assertEquals(6, lottery.generatedNumbers.size());
        assertTrue(lottery.generatedNumbers.stream().allMatch(integer -> integer >= lottery.minLotteryNumber
        && integer <= lottery.maxLotteryNumber));
    }

    private String readFromFile() {
        String line = "";
        try {
            line = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Test
    public void getLotteryNameTest_success(){

        String nameLottery = "Lotto";
        assertEquals(nameLottery, lottery.getLotteryName());
    }
}
