package attendance.controller;

import attendance.model.Attendance;
import attendance.validator.InputValidator;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;
    private final List<Attendance> attendances;
    private final InputValidator inputValidator;
    private Boolean endKey = false;
    public AttendanceController(InputView inputView, OutputView outputView, List<Attendance> attendances, InputValidator inputValidator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.attendances = attendances;
        this.inputValidator = inputValidator;
    }

    public void start() {
       while(!endKey) {
           checkMenu(inputView.requestMenuChoice());
       }
    }

    private void checkMenu(String menuChoices) {
        if (menuChoices.equals("1")){
            inputValidator.validateDay();
            String nickName = checkAttendanceNickName();
            String time = checkAttendanceNickTime();
            chackAttendance(nickName,time);
        }
        if (menuChoices.equals("2")){

        }
        if (menuChoices.equals("3")){

        }
        if (menuChoices.equals("4")){

        }
        if (menuChoices.equals("Q")){
            endKey = true;
        }
    }

    private void chackAttendance(String nickName, String time)  {
        try{
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        LocalDateTime currentDateTime = DateTimes.now();
        if(currentDateTime.getDayOfWeek().equals(DayOfWeek.MONDAY)){
            String startTime = "13:00";
            Date date1 = sdf.parse(startTime);
            Date date2 = sdf.parse(time);
            long timeMil1 = date1.getTime();
            long timeMil2 = date2.getTime();
            // 비교
            long diff = timeMil2 - timeMil1;
            long diffMin = diff / (1000 * 60);
            if (diffMin<=5){
                outputView.printAttendanceResult(time+" (출석)");
            }
            if(diffMin>5){
                outputView.printAttendanceResult(time+" (지각)");
            }
            if(diffMin>30){
                outputView.printAttendanceResult(time+" (결석)");
            }
        }
        if(!currentDateTime.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            String startTime = "10:00";
            Date date1 = sdf.parse(startTime);
            Date date2 = sdf.parse(time);
            long timeMil1 = date1.getTime();
            long timeMil2 = date2.getTime();
            // 비교
            long diff = timeMil2 - timeMil1;
            long diffMin = diff / (1000 * 60);
            if (diffMin<=5){
                outputView.printAttendanceResult(time+" (출석)");
            }
            if(diffMin>5){
                outputView.printAttendanceResult(time+" (지각)");
            }
            if(diffMin>30){
                outputView.printAttendanceResult(time+" (결석)");
            }
        }
        }catch (ParseException e){
            throw new IllegalArgumentException("[ERROR] 시간 계산 오류");
        }
    }


    private String checkAttendanceNickTime() {
            String time = inputView.checkAttendanceRequestTime();
            validateTime(time);
            return time;
    }

    private void validateTime(String time) {
        inputValidator.validateTime(time);
    }

    private String checkAttendanceNickName() {
            String nickname = inputView.checkAttendanceRequestName();
            validateNickName(nickname);
            return nickname;
    }

    private void validateNickName(String nickname) {
        inputValidator.validateNickName(nickname,attendances);
    }
}
