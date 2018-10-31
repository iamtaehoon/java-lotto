package lotto.domain;

import com.sun.tools.javac.util.List;
import org.junit.Test;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
    @Test
    public void 생성자() {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", 8);
        assertThat(winningNumbers).isEqualTo(new WinningNumbers("1,2,3,4,5,6", 8));
        assertThat(winningNumbers).isNotEqualTo(new WinningNumbers("11,12,13,14,15,16", 8));
    }
}