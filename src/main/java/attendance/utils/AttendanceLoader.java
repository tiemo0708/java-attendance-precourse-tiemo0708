package attendance.utils;

import attendance.model.Attendance;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceLoader {

    public static List<Attendance> loadProductsFromFile(String filePath) {
        List<String> lines = FileReaderUtility.readLines(filePath);
        return lines.stream()
                .skip(1)
                .map(AttendanceLoader::parseAttendance)
                .collect(Collectors.toList());
    }

    private static Attendance parseAttendance(String line) {
        String[] parts = line.split(",");
        String nickname = parts[0];
        String datetime = parts[1];
        return new Attendance(nickname, datetime);
    }
}
