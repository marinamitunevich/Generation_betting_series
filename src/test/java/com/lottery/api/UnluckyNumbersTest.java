package com.lottery.api;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UnluckyNumbersTest {

    protected Lottery lottery = new LottoLottery();
    protected Path path = Paths.get("unluckyNumber6aus49.txt");
    Logger logger = Logger.getLogger(BaseLottery.class.getName());

    @Mock
    Appender mockAppender;

    @Test
    public void addUnluckyNumbersTest_success() {

        String input = "5 6 7 30";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        lottery.addUnluckyNumbers();

        String line = "";
        try {
            line = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals("5 6 7 30", line);

    }

    @Test
    public void addUnluckyNumbersTest_NumberFormatException() {

        String input = "kk 6 7 30";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        logger.addAppender(mockAppender);

        Exception exception = assertThrows(NoSuchElementException.class,
                () -> lottery.addUnluckyNumbers());
        assertNotNull(exception);

        ArgumentCaptor<LoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        verify(mockAppender, times(1)).doAppend(eventArgumentCaptor.capture());

        assertEquals("NumberFormatException: ", eventArgumentCaptor.getAllValues().get(0).getMessage());

    }

    @Test
    public void addUnluckyNumbersTest_IncorrectRangeOfUnluckyNumber() {

        String input = "0 6 7 50";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        logger.addAppender(mockAppender);

        Exception exception = assertThrows(NoSuchElementException.class,
                () -> lottery.addUnluckyNumbers());
        assertNotNull(exception);

        ArgumentCaptor<LoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        verify(mockAppender, times(1)).doAppend(eventArgumentCaptor.capture());

        assertEquals("IncorrectRangeOfUnluckyNumber: ", eventArgumentCaptor.getAllValues().get(0).getMessage());
    }

    @Test
    public void generateNumbersTest_success() {

        LottoLottery lottery = new LottoLottery();

        lottery.generateNumbers();

        String line = "";
        try {
            line = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(!lottery.generatedNumbers.contains(Arrays.stream(line.split(" "))
                .map(s -> Integer.valueOf(s)).collect(Collectors.toList())));
        assertEquals(6, lottery.generatedNumbers.size());

    }

    @Test
    public void getUnluckyNumbersTest_success() {

        String input = "20 6 7 30";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        lottery.addUnluckyNumbers();

        assertEquals(Arrays.stream(input.split(" ")).map(s -> Integer.valueOf(s)).collect(Collectors.toList()),
                lottery.getUnluckyNumbers());

    }

    @Test
    public void removeUnLuckyNumbers_success() {

        lottery.removeUnluckyNumbers();

        List<String> line = null;
        try {
            line = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(line.size() == 0);
    }
}
