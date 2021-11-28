package lotto;

import static lotto.Application.*;

import java.util.ArrayList;

public class Player {
    public static int LOTTO_PRICE = 1000;
    private int inputMoney = 0;
    private int purchaseAmount = 0;
    private int lottoCntPurchase = 0;
    private int manualLottoCnt;
    private ArrayList<LottoTicket> tickets = new ArrayList<>();
    private LottoMachine lottoMachine = new LottoMachine(tickets);

    public void playLotto() {
        putMoney();
        lottoCntPurchase = inputMoney / LOTTO_PRICE;
        purchaseAmount = lottoCntPurchase * LOTTO_PRICE;
        decideToBuyManualLottoCnt();
        takeTheLottoManually();
        lottoMachine.takeLottoAuto(lottoCntPurchase - manualLottoCnt);
        showAllTickets();
        lottoMachine.getResult();
    }

    private void showAllTickets() {
        System.out.println("수동으로 " + manualLottoCnt + "장, 자동으로 " + (lottoCntPurchase - manualLottoCnt) + "개를 구매했습니다.");
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    private void takeTheLottoManually() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualLottoCnt; i++) {
            String inputLottoNumbers = SC.nextLine();
            String[] lottoDigitNumbersString = inputLottoNumbers.split(", ");
            ArrayList<Integer> lottoDigitNumbers = new ArrayList<>();
            for (String lottoDigitNumberString : lottoDigitNumbersString) {
                int lottoNumber = validateStringToInteger(lottoDigitNumberString);
                lottoDigitNumbers.add(lottoNumber);
            }
            tickets.add(new LottoTicket(lottoDigitNumbers));
        }

    }

    private void decideToBuyManualLottoCnt() {
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
        if (lottoCntPurchase < manualLottoCnt) {
            throw new IllegalArgumentException("구매할 로또 개수보다 더 많은 수동 로또를 살 수 없습니다.");
        }
    }

    private void putMoney() {
        System.out.println("구입금액을 입력해 주세요.");
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
