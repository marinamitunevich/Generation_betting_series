package com.lottery;

import com.lottery.api.Lottery;
import com.lottery.api.LottoLottery;
import com.lottery.api.eurojackpot.EuroJackPotLottery;
import com.lottery.menu.MainMenu;
import com.lottery.menu.Menu;

import java.util.Scanner;

public enum ApplicationContext {

    INSTANCE;
    private Lottery lottoLottery = new LottoLottery();
    private Lottery euroJackPotLottery = new EuroJackPotLottery();
    private Scanner readerFromConsole = new Scanner(System.in);
    private Menu parentMenu = new MainMenu();

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

    public Menu getParentMenu() {
        return parentMenu;
    }
}
