package attendance.model;

public class Attendance {
    private final String nickname;
    private  final String datetime;

    public Attendance(String nickname, String datetime) {
        this.nickname = nickname;
        this.datetime = datetime;
    }

    public String getNickname() {
        return nickname;
    }

    public String getDatetime() {
        return datetime;
    }
}
