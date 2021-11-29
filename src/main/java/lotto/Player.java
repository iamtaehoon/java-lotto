package lotto;

import static lotto.Application.*;
import static util.Constant.*;

import java.util.ArrayList;

import view.Input;

public class Player {
    private ArrayList<LottoTicket> tickets = new ArrayList<>();
    private LottoMachine lottoMachine = new LottoMachine(tickets);

    private int inputMoney = 0;
    private int purchaseAmount = 0;
    private int priceAmount = 0;
    private int lottoTotalCnt = 0;
    private int manualLottoCnt;
    private double result;

    public void playLotto() {
        putMoney();
        manualLottoCnt = Input.decideManualLottoCnt();
        takeLottosManually();
        lottoMachine.makeLottosAuto(lottoTotalCnt - manualLottoCnt);
        showAllTickets();
        priceAmount = lottoMachine.getResult();
        result = (double)priceAmount / purchaseAmount;
        System.out.println("총 수익률은 "+String.format("%.2f",result)+"%입니다.");
    }

    private void showAllTickets() {
        System.out.println("수동으로 " + manualLottoCnt + "장, 자동으로 " + (lottoTotalCnt - manualLottoCnt) + "개를 구매했습니다.");
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    private void takeLottosManually() {
        Input.putManualLottoNumMessage();
        for (int i = 0; i < manualLottoCnt; i++) {
            takeEachLottoManually();
        }
    }

    private void takeEachLottoManually() {
        ArrayList<Integer> lottoDigitNumbers = Input.putManualLottoNum();
        tickets.add(new LottoTicket(lottoDigitNumbers));
    }

    private void putMoney() {
        int inputMoney = Input.inputMoney();
        lottoTotalCnt = inputMoney / LOTTO_PRICE;
        purchaseAmount = lottoTotalCnt * LOTTO_PRICE;
    }
}
