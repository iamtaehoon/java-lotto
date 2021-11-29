package view;

import static lotto.Application.*;
import static util.Constant.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Input {
    private static final Scanner sc = new Scanner(System.in);

    private static String PUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static String DECIDE_MANUAL_LOTTO_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static String PUT_NUMBER_MANNUALY_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static String PUT_WINNING_NUM_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static String PUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static int inputMoney() {
        System.out.print(PUT_MONEY_MESSAGE);
        return sc.nextInt();
    }

    public static int decideManualLottoCnt() {
        System.out.println(DECIDE_MANUAL_LOTTO_COUNT_MESSAGE);
        return sc.nextInt();
    }

    public static void putManualLottoNumMessage() {
        System.out.println(PUT_NUMBER_MANNUALY_MESSAGE);
    }

    public static ArrayList<Integer> putManualLottoNum() {
        sc.nextLine(); // nextInt 다음 공백 제거 위해
        ArrayList<Integer> lottoDigitNumbers = new ArrayList<>();
        String[] lottoDigitNumbersString = sc.nextLine().split(SPLIT_REGEX);
        Arrays.stream(lottoDigitNumbersString).forEach(lottoDigitNumberString ->{
            int lottoNumber = Integer.parseInt(lottoDigitNumberString);
            lottoDigitNumbers.add(lottoNumber);
        });
        return lottoDigitNumbers;
    }

    public static ArrayList<Integer> putWinningNum() {
        ArrayList<Integer> winningNum = new ArrayList<>();
        System.out.println(PUT_WINNING_NUM_MESSAGE);
        Arrays.stream(SC.nextLine().split(SPLIT_REGEX)).forEach(x -> winningNum.add(Integer.parseInt(x)));
        return winningNum;
    }

    public static int putBonusBallNum() {
        System.out.println(PUT_BONUS_BALL_MESSAGE);
        return sc.nextInt();
    }
}
