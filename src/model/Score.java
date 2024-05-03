package TeamProject.src.model;

public class Score {

    private int round; // 회차
    private int score; // 점수
    private char grade; // 등급

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

    // toString Override
    @Override
    public String toString() {
        return round + "회차 점수는 " + score + "점이고 등급은 " + grade + "등급 입니다";
    }
}
