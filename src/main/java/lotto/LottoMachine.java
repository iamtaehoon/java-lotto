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

    public int getResult() { // 다시 로또머신으로 위치를 이동시키자. 그게 맞는거임. 로또기계가 결과를 출력해주는게 책임 분배를 잘 한거라고 생각함.
        int[] result = new int[5 + 1];
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
            if (matchNumbersCnt == 5) {
                if (ticket.winSecondPride(bonusBallNum)) {
                    result[4] += 1;
                    continue;
                }
                result[3] += 1;
                continue;
            }
            if (matchNumbersCnt == 6) {
                result[5] += 1;
                continue;
            }
            if (matchNumbersCnt >= 3) {
                result[5-matchNumbersCnt] += 1;
                continue;
            }
        }
        //2000000000, 30000000, 1500000, 50000,
        int[] price = {5000, 50000, 1500000, 30000000, 2000000000};
        String[] sentence = {"3개 일치 (5000원)- ", "4개 일치 (50000원)- ", "5개 일치 (1500000원)- ",
            " 5개 일치, 보너스 볼 일치(30000000원) - ", "6개 일치 (2000000000원)- "};
        int priceSum = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println(sentence[i] + result[i + 1] + "개");
            priceSum += (result[i + 1] * price[i]);
        }
        return priceSum;
    }

    private int validateStringToInteger(String input) {
        if (!input.matches("-?\\d+")) {
            throw new IllegalArgumentException("숫자를 입력해주세요");
        }
        return Integer.parseInt(input);
    }
}
