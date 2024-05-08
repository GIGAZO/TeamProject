package TeamProject.src.model;

public class Score {

    private int round; // 회차
    private int score; // 점수
    private char grade; // 등급

    private String studentId;

    private String subjectId;

    public Score(int round, int score, char grade, String studentId, String subjectId) {
        this.round = round;
        this.score = score;
        this.grade = grade;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    // getter setter
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
