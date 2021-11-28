package lotto;

import static util.Constant.*;

import java.util.ArrayList;

public class LottoTicket {
    private int[] lottoBalls = new int[TOTAL_LOTTO_BALL_CNT];
    private ArrayList<Integer> inputLottoBall;

    public LottoTicket(ArrayList<Integer> inputLottoBall) {
        this.inputLottoBall = inputLottoBall;
        for (int i = 0; i < TOTAL_LOTTO_BALL_CNT; i++) {
            if (isOutOfRange(i)) {
                throw new IllegalArgumentException(LOTTO_RANGE_ERROR_MESSAGE);
            }
            this.lottoBalls[i] = inputLottoBall.get(i);
        }
    }

    private boolean isOutOfRange(int i) {
        return !((LOTTO_BALL_MIN_NUM <= inputLottoBall.get(i)) & (inputLottoBall.get(i) <= LOTTO_BALL_MAX_NUM));
    }

    public int compareWinningNum(ArrayList<Integer> lottoWinningNumber) {
        int sameLocationCnt = 0;
        for (int i = 0; i < TOTAL_LOTTO_BALL_CNT; i++) {
            if (lottoWinningNumber.get(i) == lottoBalls[i]) {
                sameLocationCnt += 1;
            }
        }
        return sameLocationCnt;
    }

    public boolean isSecondPride(int bonusBallNum) {
        for (int lottoNum : lottoBalls) {
            if (bonusBallNum == lottoNum) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String stringLottoNumOnIt = "[";
        for (int lottoBall : lottoBalls) {
            stringLottoNumOnIt += lottoBall;
            if (lottoBall != lottoBalls[TOTAL_LOTTO_BALL_CNT-1]) {
                stringLottoNumOnIt += ", ";
                continue;
            }
            stringLottoNumOnIt += "]";
        }
        return stringLottoNumOnIt;
    }
}
