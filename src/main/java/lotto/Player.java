package lotto;

import static lotto.Application.*;

public class Player {
    public static int LOTTO_PRICE = 1000;
    private int inputMoney = 0;
    private int purchaseAmount = 0;
    private int lottoCntPurchase = 0;

    public void playLotto() {
        putMoney();
        lottoCntPurchase = inputMoney / LOTTO_PRICE;
        purchaseAmount = lottoCntPurchase * LOTTO_PRICE;
    }

    private void putMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String inputMoney = SC.nextLine(); // inputMoney는 로또 한장 이상의 금액이어야함.
        validateMoneyForm(inputMoney);
    }

    private void validateMoneyForm(String inputMoney) {
        if (!inputMoney.matches("-?\\d+")) {
            throw new IllegalArgumentException("올바른 금액을 입력해주세요.");
        }
        this.inputMoney = Integer.parseInt(inputMoney);
        if (this.inputMoney < 0) {
            throw new IllegalArgumentException("올바른 금액을 입력해주세요.");
        }
        if (this.inputMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또를 구매할 금액이 부족합니다.");
        }
    }
}
