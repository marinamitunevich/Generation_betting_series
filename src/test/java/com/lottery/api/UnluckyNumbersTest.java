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

        mockScanner("5 6 7 30");

        lottery.addUnluckyNumbers();

        assertEquals("5 6 7 30", readFromFile());
    }

    @Test
    public void addUnluckyNumbersTest_NumberFormatException() {

        mockScanner("kk 6 7 30");

        logger.addAppender(mockAppender);

        Exception exception = assertThrows(NoSuchElementException.class, () -> lottery.addUnluckyNumbers());
        assertNotNull(exception);

        ArgumentCaptor<LoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        verify(mockAppender, times(1)).doAppend(eventArgumentCaptor.capture());

        assertEquals("NumberFormatException: ", eventArgumentCaptor.getAllValues().get(0).getMessage());
    }

    @Test
    public void addUnluckyNumbersTest_NumberFormatException_emptyLine() {

        mockScanner("");

        logger.addAppender(mockAppender);

        Exception exception = assertThrows(NoSuchElementException.class, () -> lottery.addUnluckyNumbers());
        assertNotNull(exception);

        ArgumentCaptor<LoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        verify(mockAppender, times(0)).doAppend(eventArgumentCaptor.capture());
    }

    @Test
    public void addUnluckyNumbersTest_NumberFormatException_space() {

        String input = mockScanner(" ");

        logger.addAppender(mockAppender);

        lottery.addUnluckyNumbers();

        ArgumentCaptor<LoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        verify(mockAppender, times(0)).doAppend(eventArgumentCaptor.capture());

        String line = readFromFile();

        assertEquals(input, line);
    }

    @Test
    public void addUnluckyNumbersTest_IncorrectRangeOfUnluckyNumber() {

        mockScanner("0 6 7 50");

        logger.addAppender(mockAppender);

        Exception exception = assertThrows(NoSuchElementException.class, () -> lottery.addUnluckyNumbers());
        assertNotNull(exception);

        ArgumentCaptor<LoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        verify(mockAppender, times(1)).doAppend(eventArgumentCaptor.capture());

        assertEquals("IncorrectRangeOfUnluckyNumber: ", eventArgumentCaptor.getAllValues().get(0).getMessage());
    }

    @Test
    public void addUnluckyNumbersTest_IncorrectRangeOfUnluckyNumber_boundaryValues1and49() {

        String input = mockScanner("1 6 7 49");

        logger.addAppender(mockAppender);

        lottery.addUnluckyNumbers();

        ArgumentCaptor<LoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        verify(mockAppender, times(0)).doAppend(eventArgumentCaptor.capture());

        assertEquals(input,readFromFile());
    }

    @Test
    public void getUnluckyNumbersTest_success() {

        String input = mockScanner("20 6 7 30");

        lottery.addUnluckyNumbers();

        List<Integer> inputList = Arrays.stream(input.split(" ")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());

        assertEquals(inputList, lottery.getUnluckyNumbers());
        assertEquals(inputList.size(),lottery.getUnluckyNumbers().size());
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

    private String readFromFile() {

        String line = "";
        try {
            line = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private String mockScanner(String s) {

        InputStream in = new ByteArrayInputStream(s.getBytes());
        System.setIn(in);
        return s;
    }
}
