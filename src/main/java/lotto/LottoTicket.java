package lotto;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoTicket {
    private int[] lottoNums = new int[6];

    public LottoTicket(ArrayList<Integer> lottoNum) {
        for (int i = 0; i < 6; i++) {
            if (!((0 < lottoNum.get(i)) & (lottoNum.get(i) <= 45))) {
                throw new IllegalArgumentException("로또 번호는 1~45까지만 가능합니다.");
            }
            this.lottoNums[i] = lottoNum.get(i);
        }
    }

    @Override
    public String toString() {
        String numbersOnTicket = "[";
        for (int lottoNum : lottoNums) {
            numbersOnTicket += lottoNum;
            if (lottoNum != lottoNums[5]) {
                numbersOnTicket += ", ";
                continue;
            }
            numbersOnTicket += "]";
        }
        return numbersOnTicket;
    }

    public int matchNumbersCnt(ArrayList<Integer> winningNumList) {
        int answer = 0;
        for (int i = 0; i < 6; i++) {
            if (winningNumList.get(i) == lottoNums[i]) {
                answer += 1;
            }
        }
        return answer;
    }

    public boolean winSecondPride(int bonusBallNum) {
        for (int lottoNum : lottoNums) {
            if (bonusBallNum == lottoNum) {
                return true;
            }
        }
        return false;
    }
}
