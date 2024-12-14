package attendance.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String requestMenuChoice() {
        System.out.println("");
        return Console.readLine();
    }
}
