package attendance.view;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class OutputView {
    LocalDateTime currentDateTime = DateTimes.now();
    DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();

    public void printError(String message) {
            System.out.println(message);
    }

    public void printAttendanceResult(String timeAttendance) {
        System.out.printf("%d월 %d일 %s %s\n",currentDateTime.getMonthValue(), currentDateTime.getDayOfMonth(),dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN),timeAttendance);
    }

    public void printModifyResult(String s) {
        System.out.println(s);
    }
}
