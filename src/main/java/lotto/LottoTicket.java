package lotto;

public class LottoTicket {
    private int[] lottoNum = new int[6];

    public LottoTicket(int[] lottoNum) {
        for (int i = 0; i < 6; i++) {
            if (!((0 < lottoNum[i]) & (lottoNum[i] <= 45))) {
                throw new IllegalArgumentException("로또 번호는 1~45까지만 가능합니다.");
            }
            this.lottoNum[i] = lottoNum[i];
        }
    }
}
