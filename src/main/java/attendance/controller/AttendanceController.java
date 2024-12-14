package attendance.controller;

import attendance.model.Attendance;
import attendance.view.InputView;
import attendance.view.OutputView;

import java.util.List;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;
    private final List<Attendance> attendances;
    public AttendanceController(InputView inputView, OutputView outputView, List<Attendance> attendances) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.attendances = attendances;
    }

    public void start() {

    }
}
