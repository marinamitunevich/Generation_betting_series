package com.lottery.exceptions;

public class IncorrectRangeOfUnluckyNumber extends Exception{
    
    private String messages;
    public IncorrectRangeOfUnluckyNumber(String s) {
        this.messages = s;
    }
    public String getMessages(){
        return messages;
    }
}
