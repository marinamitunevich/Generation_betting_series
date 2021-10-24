package com.lottery;

import com.lottery.api.Lottery;
import com.lottery.api.LottoLottery;
import com.lottery.api.eurojackpot.EuroJackPot2From10;
import com.lottery.api.eurojackpot.EuroJackPot5From50;
import com.lottery.api.eurojackpot.EuroJackPotLottery;
import com.lottery.menu.MainMenu;
import com.lottery.menu.Menu;

import java.util.Scanner;

public enum ApplicationContext {

    INSTANCE;
    private Lottery lottoLottery = new LottoLottery();
    private Lottery euroJackPotLottery = new EuroJackPotLottery();
    private Lottery euroJackPot2From10 = new EuroJackPot2From10();
    private Lottery euroJackPot5From50= new EuroJackPot5From50();
    private Scanner readerFromConsole = new Scanner(System.in);
    private Menu rootMenu = new MainMenu();

    ApplicationContext() {
    }

    public Lottery getLottoLottery() {
        return lottoLottery;
    }

    public Lottery getEuroJackPotLottery() {
        return euroJackPotLottery;
    }

    public Scanner getReaderFromConsole() {
        return readerFromConsole;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Lottery getEuroJackPot2From10() {
        return euroJackPot2From10;
    }

    public Lottery getEuroJackPot5From50() {
        return euroJackPot5From50;
    }
}
