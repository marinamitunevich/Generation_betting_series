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

    private final static Logger log = Logger.getLogger(BaseLottery.class.getName());
    protected final Path path;
    protected final byte[] emptyLineBytes;

    protected final String lotteryName;
    protected final int minLotteryNumber;
    protected final int maxLotteryNumber;
    protected final int lotteryCountNumbers;
    protected final String unluckyNumbersFile;
    protected List<Integer> generatedNumbers;

    protected BaseLottery(String lotteryName, int minLotteryNumber, int maxLotteryNumber, int lotteryCountNumbers, String unluckyNumbersFile) {

        this.emptyLineBytes = "".getBytes(StandardCharsets.UTF_8);
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

        unluckyNumbers = getListFromLine(line);
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

        String unluckyNumbers = null;

        while (unluckyNumbers == null) {
            try {
                unluckyNumbers = checkConditions();

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

        try {
            Files.write(path, emptyLineBytes);
            System.out.println("Attention unlucky numbers for " + getLotteryName() + " are deleted\n");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IOException: ", e);
        }
    }

    private List<Integer> getListFromLine(String line) {

        return Arrays.stream(line.split(" ")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());
    }

    private String checkConditions() throws IncorrectRangeOfUnluckyNumber {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        if (!(getListFromLine(line).stream().allMatch(integer -> integer <= maxLotteryNumber && integer >= minLotteryNumber)
                && line.split(" ").length <= 6)) {

            throw new IncorrectRangeOfUnluckyNumber("unlucky number should be in the range [" + minLotteryNumber + "," +
                    maxLotteryNumber + "] and it is allowed maximum 6 unlucky numbers");
        } else {
            return line;
        }
    }
}
