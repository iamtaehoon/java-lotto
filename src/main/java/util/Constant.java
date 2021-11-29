package util;

public class Constant {
    public static int LOTTO_PRICE = 1000;
    public static int TOTAL_LOTTO_BALL_CNT = 6;
    public static int LOTTO_BALL_MIN_NUM = 1;
    public static int LOTTO_BALL_MAX_NUM = 45;

    public static int FIRST_PRIZE = 4;
    public static int SECOND_PRIZE = 3;
    public static int THIRD_PRIZE = 2;
    public static int FOURTH_PRIZE = 1;
    public static int FIFTH_PRIZE = 0;

    public static int[] PRICE = {5000, 50000, 1500000, 30000000, 2000000000};
    public static String[] RESULT_SENTENCE_FORWARD = {"3개 일치 (5000원)- ", "4개 일치 (50000원)- ", "5개 일치 (1500000원)- ",
        "5개 일치, 보너스 볼 일치(30000000원) - ", "6개 일치 (2000000000원)- "};
    public static String RESULT_SENTENCE_BACK = "개";

    public static String INT_REGEX = "-?\\d+";
    public static String SPLIT_REGEX =", ";

    public static String CANT_NEGATIVE_MESSAGE = "구매할 로또 개수는 음수가 될 수 없습니다.";
    public static String CANT_BUY_MORE_THAN_TOTAL_MESSAGE = "구매할 로또 개수보다 더 많은 수동 로또를 살 수 없습니다.";
    public static String LOTTO_RANGE_ERROR_MESSAGE = "로또 번호는 1~45까지만 가능합니다.";
    public static String INPUT_INVALID_MESSAGE = "올바른 금액을 입력해주세요.";
    public static String LACK_OF_MONEY_MESSAGE = "로또를 구매할 금액이 부족합니다.";

}
