package attendance.view;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class InputView {
    LocalDateTime currentDateTime = DateTimes.now();
    DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();

    public String requestMenuChoice() {
        System.out.printf("오늘은 %d월 %d일 %s입니다. 기능을 선택해 주세요.",currentDateTime.getMonthValue(), currentDateTime.getDayOfMonth(),dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN));
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
        return Console.readLine();
    }
}
