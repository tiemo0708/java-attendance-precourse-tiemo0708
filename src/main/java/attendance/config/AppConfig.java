package attendance.config;

import attendance.controller.AttendanceController;
import attendance.model.Attendance;
import attendance.utils.AttendanceLoader;
import attendance.validator.InputValidator;
import attendance.view.InputView;
import attendance.view.OutputView;

import java.util.List;

public class AppConfig {
    public AttendanceController attendanceController() {
        return new AttendanceController(inputView(), outputView(), attendanceList(),inputValidator());
    }
    public List<Attendance> attendanceList() {
        return AttendanceLoader.loadProductsFromFile("src/main/resources/attendances.csv");
    }

    public OutputView outputView() {
        return new OutputView();
    }
    public InputView inputView() {
        return new InputView();
    }
    public InputValidator inputValidator(){
        return new InputValidator();
    }

}
