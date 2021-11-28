package lotto;

import static lotto.Application.*;
import static util.Constant.*;

import java.util.ArrayList;

public class Player {
    private ArrayList<LottoTicket> tickets = new ArrayList<>();
    private LottoMachine lottoMachine = new LottoMachine(tickets);
    private int inputMoney = 0;
    private int purchaseAmount = 0;
    private int priceAmount = 0;
    private int lottoTotalCnt = 0;
    private int manualLottoCnt;

    public void playLotto() {
        putMoney();
        decideManualLottoCnt();
        takeLottosManually();
        lottoMachine.makeLottosAuto(lottoTotalCnt - manualLottoCnt);
        showAllTickets();
        priceAmount = lottoMachine.getResult();
        // TODO: 나중에 결과값으로 바꿔줄거. 일단 제대로 결과가 출력되는지 확인을 위한 코드
        double result = (double)priceAmount / purchaseAmount;

        System.out.println("총 수익률은 "+String.format("%.2f",result)+"%입니다.");
    }

    private void showAllTickets() {
        System.out.println("수동으로 " + manualLottoCnt + "장, 자동으로 " + (lottoTotalCnt - manualLottoCnt) + "개를 구매했습니다.");
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    private void takeLottosManually() {
        System.out.println(PUT_NUMBER_MANNUALY_MESSAGE);
        for (int i = 0; i < manualLottoCnt; i++) {
            takeEachLottoManually();
        }
    }

    private void takeEachLottoManually() {
        String inputLottoNumbers = SC.nextLine();
        String[] lottoDigitNumbersString = inputLottoNumbers.split(SPLIT_REGEX);
        ArrayList<Integer> lottoDigitNumbers = new ArrayList<>();
        for (String lottoDigitNumberString : lottoDigitNumbersString) {
            int lottoNumber = validateStringToInteger(lottoDigitNumberString);
            lottoDigitNumbers.add(lottoNumber);
        }
        tickets.add(new LottoTicket(lottoDigitNumbers));
    }

    private void decideManualLottoCnt() {
        System.out.println(DECIDE_MANUAL_LOTTO_COUNT_MESSAGE);
        String manualLottoCntString = SC.nextLine();
        validateManualLottoCntForm(manualLottoCntString);
    }

    private void validateManualLottoCntForm(String manualLottoCntString) {
        this.manualLottoCnt = validateStringToInteger(manualLottoCntString);
        validateManualLottoCntIsNotMinus(manualLottoCnt);
        validateManualLottoCntIsSmallerThanTotal(manualLottoCnt);
    }

    private void validateManualLottoCntIsNotMinus(int manualLottoCnt) {
        if (manualLottoCnt < 0) {
            throw new IllegalArgumentException(CANT_NEGATIVE_MESSAGE);
        }
    }

    private void validateManualLottoCntIsSmallerThanTotal(int manualLottoCnt) {
        if (lottoTotalCnt < manualLottoCnt) {
            throw new IllegalArgumentException(CANT_BUY_MORE_THAN_TOTAL_MESSAGE);
        }
    }

    private void putMoney() {
        System.out.println(PUT_MONEY_MESSAGE);
        String inputMoneyString = SC.nextLine();
        validateMoneyForm(inputMoneyString);
        lottoTotalCnt = inputMoney / LOTTO_PRICE;
        purchaseAmount = lottoTotalCnt * LOTTO_PRICE;
    }

    private void validateMoneyForm(String inputMoneyString) {
        this.inputMoney = validateStringToInteger(inputMoneyString);
        if (inputMoney < 0) {
            throw new IllegalArgumentException(INPUT_INVALID_MESSAGE);
        }
        if (inputMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException(LACK_OF_MONEY_MESSAGE);
        }
    }

    private int validateStringToInteger(String input) {
        if (!input.matches(INT_REGEX)) {
            throw new IllegalArgumentException(INPUT_INVALID_MESSAGE);
        }
        return Integer.parseInt(input);
    }
}
