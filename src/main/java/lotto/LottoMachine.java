package lotto;

import static lotto.Application.*;
import static util.Constant.*;

import java.util.ArrayList;
import java.util.Random;

public class LottoMachine {
    ArrayList<Integer> winningNum = new ArrayList<>(); // 결과 기록
    private int bonusBallNum;
    int[] result = new int[TOTAL_LOTTO_BALL_CNT];
    int sumOfPrize = 0;

    private ArrayList<LottoTicket> tickets;
    Random random = new Random();

    public LottoMachine(ArrayList<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public void makeLottosAuto(int autoLottoCnt) {
        ArrayList<Integer> eachLotto;
        for (int i = 0; i < autoLottoCnt; i++) {
            eachLotto = makeLottoEachDigitAutomatic();
            tickets.add(new LottoTicket(eachLotto));
        }
    }

    private ArrayList<Integer> makeLottoEachDigitAutomatic() {
        boolean[] alreadyShown = new boolean[LOTTO_BALL_MAX_NUM + 1];
        ArrayList<Integer> lottoBallList = new ArrayList<>();
        int ballNum;
        while (lottoBallList.size() != TOTAL_LOTTO_BALL_CNT) {
            ballNum = 1 + random.nextInt(LOTTO_BALL_MAX_NUM);
            if (alreadyShown[ballNum]) {
                continue;
            }
            lottoBallList.add(ballNum);
            alreadyShown[ballNum] = true;
        }
        return lottoBallList;
    }

    public int getResult() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        makeWinningNum(winningNum);
        System.out.println("보너스 볼을 입력해 주세요.");
        bonusBallNum = Integer.parseInt(SC.nextLine());
        for (LottoTicket ticket : tickets) {
            recordResult(ticket);
        }

        calculateSumOfPrize();
        return sumOfPrize;
    }

    private void calculateSumOfPrize() {
        for (int i = 0; i < 5; i++) {
            System.out.println(RESULT_SENTENCE_FORWARD[i] + result[i] + RESULT_SENTENCE_BACK);
            sumOfPrize += (result[i] * PRICE[i]);
        }
    }

    private void recordResult(LottoTicket ticket) {
        int matchNumbersCntWithWinningNum = ticket.compareWinningNum(winningNum);
        if (matchNumbersCntWithWinningNum == 6) {
            result[FIRST_PRIZE] += 1;
            return;
        }
        if (matchNumbersCntWithWinningNum == 5) {
            if (ticket.isSecondPride(bonusBallNum)) {
                result[SECOND_PRIZE] += 1;
                return;
            }
            result[THIRD_PRIZE] += 1;
            return;
        }
        if (matchNumbersCntWithWinningNum == 4) {
            result[FOURTH_PRIZE] += 1;
        }
        if (matchNumbersCntWithWinningNum == 3) {
            result[FIFTH_PRIZE] += 1;
        }
    }

    private void makeWinningNum(ArrayList<Integer> winningNumList) {
        String[] winningNumsString = SC.nextLine().split(", ");
        for (String eachDigitWinningNumString : winningNumsString) {
            int eachDigitWinningNum = validateStringToInteger(eachDigitWinningNumString);
            winningNumList.add(eachDigitWinningNum);
        }
    }

    private int validateStringToInteger(String input) {
        if (!input.matches("-?\\d+")) {
            throw new IllegalArgumentException(INPUT_INVALID_MESSAGE);
        }
        return Integer.parseInt(input);
    }
}
