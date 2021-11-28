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
        lottoTotalCnt = inputMoney / LOTTO_PRICE;
        purchaseAmount = lottoTotalCnt * LOTTO_PRICE;
        decideManualLottoCnt();
        takeLottosManually();
        lottoMachine.takeLottoAuto(lottoTotalCnt - manualLottoCnt);
        showAllTickets();
        priceAmount = lottoMachine.getResult();
        // TODO: 나중에 결과값으로 바꿔줄거. 일단 제대로 결과가 출력되는지 확인을 위한 코드
        // System.out.println("purchaseAmount = " + purchaseAmount);
        // System.out.println("priceAmount = " + priceAmount);
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
        String[] lottoDigitNumbersString = inputLottoNumbers.split(", ");
        ArrayList<Integer> lottoDigitNumbers = new ArrayList<>();
        for (String lottoDigitNumberString : lottoDigitNumbersString) {
            int lottoNumber = validateStringToInteger(lottoDigitNumberString);
            lottoDigitNumbers.add(lottoNumber);
        }
        tickets.add(new LottoTicket(lottoDigitNumbers));
    }

    private void decideManualLottoCnt() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
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
            throw new IllegalArgumentException("구매할 로또 개수는 음수가 될 수 없습니다.");
        }
    }

    private void validateManualLottoCntIsSmallerThanTotal(int manualLottoCnt) {
        if (lottoTotalCnt < manualLottoCnt) {
            throw new IllegalArgumentException("구매할 로또 개수보다 더 많은 수동 로또를 살 수 없습니다.");
        }
    }

    private void putMoney() {
        System.out.println(PUT_MONEY_MESSAGE);
        String inputMoneyString = SC.nextLine(); // inputMoney는 로또 한장 이상의 금액이어야함.
        validateMoneyForm(inputMoneyString);
    }

    private void validateMoneyForm(String inputMoneyString) {
        this.inputMoney = validateStringToInteger(inputMoneyString);
        if (inputMoney < 0) {
            throw new IllegalArgumentException("올바른 금액을 입력해주세요.");
        }
        if (inputMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또를 구매할 금액이 부족합니다.");
        }
    }

    private int validateStringToInteger(String input) {
        if (!input.matches("-?\\d+")) {
            throw new IllegalArgumentException("숫자를 입력해주세요");
        }
        return Integer.parseInt(input);
    }
}
