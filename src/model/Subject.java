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
        scoreList = new ArrayList<>(); // 전체 점수 저장 List 초기화
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
}
