package academic.model;

/**
 * @author 12S24004 Silvia Eklesiana Sitorus
 */

public class Course {
    private String code;
    private String name;
    private int credits;
    private String passingGrade;
    private SessionDetail sessionDetail;

    public Course(String code, String name, int credits, String passingGrade) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.passingGrade = passingGrade;
        this.sessionDetail = new SessionDetail(28, "Theory");
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

    public class SessionDetail {
        private int totalMeetings;
        private String sessionType;

        public SessionDetail(int totalMeetings, String sessionType) {
            this.totalMeetings = totalMeetings;
            this.sessionType = sessionType;
        }

        public int getTotalMeetings() {
            return totalMeetings;
        }

        public String getSessionType() {
            return sessionType;
        }

        public String getDescription() {
            return code + " has " + totalMeetings + " " + sessionType + " sessions";
        }
    }

    public SessionDetail getSessionDetail() {
        return sessionDetail;
    }
}