package lotto;

import static lotto.Application.*;

import java.util.ArrayList;
import java.util.Random;

public class LottoMachine {
    private ArrayList<LottoTicket> tickets;
    Random random = new Random();

    public LottoMachine(ArrayList<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public void takeLottoAuto(int autoLottoCnt) {
        for (int i = 0; i < autoLottoCnt; i++) {
            makeLottoAutomatic();
        }
    }

    private void makeLottoAutomatic() {
        boolean[] alreadyShown = new boolean[45+1]; //디폴트가 false
        int ballNum;
        ArrayList<Integer> lottoBallList = new ArrayList<>();
        while (lottoBallList.size() != 6) {
            ballNum = 1 + random.nextInt(45);
            if (!alreadyShown[ballNum]) {
                lottoBallList.add(ballNum);
                alreadyShown[ballNum] = true;
            }
        }
        tickets.add(new LottoTicket(lottoBallList));
    }

    public void getResult() { // 다시 로또머신으로 위치를 이동시키자. 그게 맞는거임. 로또기계가 결과를 출력해주는게 책임 분배를 잘 한거라고 생각함.
        int[] result = new int[5+1];
        ArrayList<Integer> winningNumList = new ArrayList<>(); // 결과 기록
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] winningNumsString = SC.nextLine().split(", ");
        for (String winningNumString : winningNumsString) {
            int winningNum = validateStringToInteger(winningNumString);
            winningNumList.add(winningNum);
        }
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusBallNum = Integer.parseInt(SC.nextLine());
        for (LottoTicket ticket : tickets) {
            int matchNumbersCnt = ticket.matchNumbersCnt(winningNumList);
            System.out.println("matchNumbersCnt = " + matchNumbersCnt);
            if (matchNumbersCnt == 5) {
                if (ticket.winSecondPride(bonusBallNum)) {
                    result[2] += 1;
                    continue;
                }
                result[3] += 1;
                continue;
            }
            if (matchNumbersCnt == 6) {
                result[1] += 1;
                continue;
            }
            if (matchNumbersCnt >= 3) {
                result[8 - matchNumbersCnt] += 1;
                continue;
            }
        }
        for (int i : result) {
            System.out.println("i = " + i);
        }

        // tickets를 돌려서 1등, 3등, 4등, 5등을 기록 -> 이후 보너스볼을 입력해주세요 문구 출력.
        // 3등은 따로 기록해두고 거기서 보너스볼이 있는지를 확인. 보너스볼은 앞에 나온 여섯 숫자랑 다른 숫자여야 함.
        // 당첨 통계랑 총 수익률 계산.
    }

    private int validateStringToInteger(String input) {
        if (!input.matches("-?\\d+")) {
            throw new IllegalArgumentException("숫자를 입력해주세요");
        }
        return Integer.parseInt(input);
    }
}
