package TeamProject.src.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String subjectId; // 과목 고유번호
    private String subjectName; // 과목 이름
    private String subjectType; // 과목 타입(필수, 선택)
    private List<Score> scoreList; // 전체 회차 점수 저장
    private Score score; // 각 회차 점수 저장용

    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.scoreList = new ArrayList<>(); // 전체 점수 저장 List 초기화
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }


    public void setScore(int round, int result, char grade){
        score = new Score(); // score 값을 세팅할 때 기존 값이 바뀌면 안되기 때문에 메소드 실행될 때마다 계속 초기화되게 설정
        score.setRound(round); // score에 setter에 값 전달
        score.setScore(result);
        score.setGrade(grade);
        scoreList.add(score); // 완성된 score를 List에 저장
    }

    // 정효진 수정 -> 해당 과목의 점수가 등록되어 있는 회차 출력 함수
    public List<Integer> printScore() {
        System.out.println("현재 점수가 등록되어 있는 회차입니다.");
        List<Integer> roundList = new ArrayList<>();
        for (Score s : this.getScoreList()) {
            roundList.add(s.getRound());
            System.out.print(s.getRound() + "회차 ");
        }
        System.out.println();
        return roundList;
    }

    public void makeGrade(int score, int round) {
        if (this.getSubjectType() == "MANDATORY") { // 필수 과목일 경우
            if (95 <= score && score <= 100) {
                this.setScore(round, score, 'A');
            } else if (90 <= score && score <= 94) {
                this.setScore(round, score, 'B');
            } else if (80 <= score && score <= 89) {
                this.setScore(round, score, 'C');
            } else if (70 <= score && score <= 79) {
                this.setScore(round, score, 'D');
            } else if (60 <= score && score <= 69) {
                this.setScore(round, score, 'F');
            } else {
                this.setScore(round, score, 'N');
            }
        } else { // 선택 과목일 경우
            if (90 <= score && score <= 100) {
                this.setScore(round, score, 'A');
            } else if (80 <= score && score <= 89) {
                this.setScore(round, score, 'B');
            } else if (70 <= score && score <= 79) {
                this.setScore(round, score, 'C');
            } else if (60 <= score && score <= 69) {
                this.setScore(round, score, 'D');
            } else if (50 <= score && score <= 59) {
                this.setScore(round, score, 'F');
            } else {
                this.setScore(round, score, 'N');
            }
        }
    }
}
