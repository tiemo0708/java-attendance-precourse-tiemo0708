package attendance.validator;

import attendance.model.Attendance;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InputValidator {

    public void validateNickName(String nickname, List<Attendance> attendances) {
        List<String> trueName = new ArrayList<>();
        for(Attendance attendance: attendances){
            trueName.add(attendance.getNickname());
        }
        if(!trueName.contains(nickname)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }

    public void validateTime(String time) {
        if (!time.matches("^1[0~9]|2[0~4]|0[0~9]:60|[0~5][0~9]")){
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    public void validateDay() {
        LocalDateTime currentDateTime = DateTimes.now();
        DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();
        if(currentDateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)||currentDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            throw new IllegalArgumentException("[ERROR] "+currentDateTime.getMonthValue()+"월 "+currentDateTime.getDayOfMonth()+"일 "+dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)+"은 등교일이 아닙니다.");
        }
    }
}
