package lotto;

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

}
