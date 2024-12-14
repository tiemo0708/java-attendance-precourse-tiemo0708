package attendance.controller;

import attendance.model.Attendance;
import attendance.validator.InputValidator;
import attendance.view.InputView;
import attendance.view.OutputView;

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
            checkAttendanceNickName();
            checkAttendanceNickTime();
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

    private void checkAttendanceNickTime() {
            String time = inputView.checkAttendanceRequestTime();
            validateTime(time);
    }

    private void validateTime(String time) {
        inputValidator.validateTime(time);
    }

    private void checkAttendanceNickName() {
            String nickname = inputView.checkAttendanceRequestName();
            validateNickName(nickname);
    }

    private void validateNickName(String nickname) {
        inputValidator.validateNickName(nickname,attendances);
    }
}
