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
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;
    private List<Attendance> attendances;
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
            modifiAttendance();

        }
        if (menuChoices.equals("3")){

        }
        if (menuChoices.equals("4")){

        }
        if (menuChoices.equals("Q")){
            endKey = true;
        }
    }

    private void modifiAttendance() {
        String nickName = inputView.changeAttendanceName();
        String modifyDay = inputView.changeAttendanceDay();
        String modifyTime = inputView.changeAttendanceTime();
        Boolean checkNickname, checkDay = false;
        String newDayAndTime = "",oldDayAndTime = "";

        if(modifyDay.length() == 1){
            modifyDay = "0"+modifyDay;
        }
        for(Attendance attendance: attendances){
            if (nickName.equals(attendance.getNickname())&&modifyDay.equals(attendance.getDatetime().substring(8,10))){
                newDayAndTime = attendance.getDatetime().substring(0,8)+modifyDay+" "+modifyTime;
                oldDayAndTime = attendance.getDatetime();
                attendance.setDatetime(newDayAndTime);
            }
        }
        modifyAttenceReslut(newDayAndTime,oldDayAndTime);
    }

    private void modifyAttenceReslut(String newDayAndTime, String oldDayAndTime) {
        try{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String str = newDayAndTime.substring(0,10)+" 13:47:13.248";
        String str2 = oldDayAndTime.substring(0,10)+" 13:47:13.248";
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String newTime = newDayAndTime.substring(11);
        String oldTime = oldDayAndTime.substring(11);
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        DayOfWeek dayOfWeek2 = dateTime2.getDayOfWeek();
        String newTimeResult = null;
        String oldTimeResult = null;
            if(dateTime.getDayOfWeek().equals(DayOfWeek.MONDAY)){
                String startTime = "13:00";
                Date date1 = sdf.parse(startTime);
                Date date2 = sdf.parse(newTime);
                long timeMil1 = date1.getTime();
                long timeMil2 = date2.getTime();
                // 비교
                long diff = timeMil2 - timeMil1;
                long diffMin = diff / (1000 * 60);
                if (diffMin<=5){
                    newTimeResult= String.valueOf(dateTime.getMonthValue())+"월 "+dateTime.getDayOfMonth()+"일 "+dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (출석) "+"수정 완료!";
;                }
                if(diffMin>5){
                    newTimeResult= String.valueOf(dateTime.getMonthValue())+"월 "+dateTime.getDayOfMonth()+"일 "+dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (지각) "+"수정 완료!";
                }
                if(diffMin>30){
                    newTimeResult= String.valueOf(dateTime.getMonthValue())+"월 "+dateTime.getDayOfMonth()+"일 "+dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (결석) "+"수정 완료!";
                }
            }
            if(!dateTime.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                String startTime = "10:00";
                Date date1 = sdf.parse(startTime);
                Date date2 = sdf.parse(newTime);
                long timeMil1 = date1.getTime();
                long timeMil2 = date2.getTime();
                // 비교
                long diff = timeMil2 - timeMil1;
                long diffMin = diff / (1000 * 60);
                if (diffMin<=5){
                    newTimeResult= String.valueOf(dateTime.getMonthValue())+"월 "+dateTime.getDayOfMonth()+"일 "+dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (출석) "+"수정 완료!";
                }
                if(diffMin>5){
                    newTimeResult= String.valueOf(dateTime.getMonthValue())+"월 "+dateTime.getDayOfMonth()+"일 "+dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (지각) "+"수정 완료!";
                }
                if(diffMin>30){
                    newTimeResult= String.valueOf(dateTime.getMonthValue())+"월 "+dateTime.getDayOfMonth()+"일 "+dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (결석) "+"수정 완료!";
                }
            }
            if(dateTime2.getDayOfWeek().equals(DayOfWeek.MONDAY)){
                String startTime = "13:00";
                Date date1 = sdf.parse(startTime);
                Date date2 = sdf.parse(oldTime);
                long timeMil1 = date1.getTime();
                long timeMil2 = date2.getTime();
                // 비교
                long diff = timeMil2 - timeMil1;
                long diffMin = diff / (1000 * 60);
                if (diffMin<=5){
                    oldTimeResult= String.valueOf(dateTime2.getMonthValue())+"월 "+dateTime2.getDayOfMonth()+"일 "+dayOfWeek2.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (출석) "+"-> ";
                }
                if(diffMin>5){
                    oldTimeResult= String.valueOf(dateTime2.getMonthValue())+"월 "+dateTime2.getDayOfMonth()+"일 "+dayOfWeek2.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (지각) "+"-> ";
                }
                if(diffMin>30){
                    oldTimeResult= String.valueOf(dateTime2.getMonthValue())+"월 "+dateTime2.getDayOfMonth()+"일 "+dayOfWeek2.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (결석) "+"-> ";
                }
            }
            if(!dateTime2.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                String startTime = "10:00";
                Date date1 = sdf.parse(startTime);
                Date date2 = sdf.parse(oldTime);
                long timeMil1 = date1.getTime();
                long timeMil2 = date2.getTime();
                // 비교
                long diff = timeMil2 - timeMil1;
                long diffMin = diff / (1000 * 60);
                if (diffMin<=5){
                    oldTimeResult= String.valueOf(dateTime2.getMonthValue())+"월 "+dateTime2.getDayOfMonth()+"일 "+dayOfWeek2.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (출석) "+"-> ";
                }
                if(diffMin>5){
                    oldTimeResult= String.valueOf(dateTime2.getMonthValue())+"월 "+dateTime2.getDayOfMonth()+"일 "+dayOfWeek2.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (지각) "+"-> ";
                }
                if(diffMin>30){
                    oldTimeResult= String.valueOf(dateTime2.getMonthValue())+"월 "+dateTime2.getDayOfMonth()+"일 "+dayOfWeek2.getDisplayName(TextStyle.FULL, Locale.KOREAN)+" "+newTime+" (결석) "+"-> ";
                }
            }
            outputView.printModifyResult(oldTimeResult+newTimeResult);
        }catch (ParseException e){
            throw new IllegalArgumentException("[ERROR] 시간 계산 오류");
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
