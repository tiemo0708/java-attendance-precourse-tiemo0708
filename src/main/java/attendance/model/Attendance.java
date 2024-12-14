package attendance.model;

public class Attendance {
    private String nickname;
    private String datetime;

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

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
