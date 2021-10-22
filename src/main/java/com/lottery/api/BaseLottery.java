package com.lottery.api;

import com.lottery.exceptions.IncorrectRangeOfUnluckyNumber;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseLottery implements Lottery {

    private static Logger log = Logger.getLogger(BaseLottery.class.getName());
    protected Path path;

    protected final String lotteryName;
    protected final int minLotteryNumber;
    protected final int maxLotteryNumber;
    protected final int lotteryCountNumbers;
    protected final String unluckyNumbersFile;
    protected List<Integer> generatedNumbers;

    protected BaseLottery(String lotteryName, int minLotteryNumber, int maxLotteryNumber, int lotteryCountNumbers, String unluckyNumbersFile) {
        this.lotteryName = lotteryName;
        this.minLotteryNumber = minLotteryNumber;
        this.maxLotteryNumber = maxLotteryNumber;
        this.lotteryCountNumbers = lotteryCountNumbers;
        this.unluckyNumbersFile = unluckyNumbersFile;
        this.path = Paths.get(unluckyNumbersFile);
        this.generatedNumbers = new ArrayList<>();
    }

    @Override
    public List<Integer> getUnluckyNumbers() {

        List<Integer> unluckyNumbers;
        String line = "";

        try {
            line = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IOException: ", e);
        }

        unluckyNumbers = Arrays.stream(line.split(" ")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

        return unluckyNumbers;
    }

    @Override
    public String getLotteryName() {
        return lotteryName;
    }

    @Override
    public void generateNumbers() {

        Random rand = new Random();
        while (generatedNumbers.size() < lotteryCountNumbers) {

            int randomNum = rand.nextInt((maxLotteryNumber - minLotteryNumber) + 1) + minLotteryNumber;

            if (!generatedNumbers.contains(randomNum) && !getUnluckyNumbers().contains(randomNum)) {
                generatedNumbers.add(randomNum);
            }
        }

        System.out.println("====================Series of numbers for " + lotteryCountNumbers + "aus" + maxLotteryNumber + " :");
        generatedNumbers = generatedNumbers.stream().sorted().collect(Collectors.toList());
        System.out.println(generatedNumbers);
    }

    @Override
    public void addUnluckyNumbers() {

        System.out.println("enter unlucky numbers for " + getLotteryName() + " and please split with blank:");
        Scanner readerFromConsole = new Scanner(System.in);

        String unluckyNumbers = "";
        boolean condition = true;

        while (condition) {
            unluckyNumbers = readerFromConsole.nextLine();
            try {
                if (!(Arrays.stream(unluckyNumbers.split(" ")).map(s -> Integer.valueOf(s))
                        .allMatch(integer -> integer <= maxLotteryNumber && integer >= minLotteryNumber)
                        && unluckyNumbers.split(" ").length <= 6)) {

                    throw new IncorrectRangeOfUnluckyNumber("unlucky number should be in the range [" + minLotteryNumber + "," +
                            maxLotteryNumber + "] and it is allowed maximum 6 unlucky numbers");
                } else {
                    condition = false;
                }

            } catch (IncorrectRangeOfUnluckyNumber e) {

                System.out.println(e.getMessages());
                log.error("IncorrectRangeOfUnluckyNumber: ", e);

            } catch (NumberFormatException e) {

                System.out.println("invalid format of numbers");
                log.error("NumberFormatException: ", e);
            }
        }

        try {
            Files.write(path, unluckyNumbers.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IOException: ", e);
        }
    }

    @Override
    public void removeUnluckyNumbers() {

        String str = "";

        try {
            Files.write(path, str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IOException: ", e);
        }

        System.out.println("Attention unlucky numbers for " + getLotteryName() + " are deleted");
    }
}
