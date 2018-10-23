package lotto.view;

import lotto.domain.LottoGame;
import lotto.domain.LottoMap;
import org.junit.Test;

public class ResultViewTest {
    @Test
    public void 결과출력() {
        LottoGame lottoGame = new LottoGame(30000);
        LottoMap map = lottoGame.compare("1,2,3,4,5,6");
        ResultView.statisticsLottoWinnings(map, 30000);
    }
}