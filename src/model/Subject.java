package TeamProject.src.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String subjectId; // 과목 고유번호
    private String subjectName; // 과목 이름
    private String subjectType; // 과목 타입(필수, 선택)

    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
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

//    // 정효진 수정 -> 해당 과목의 점수가 등록되어 있는 회차 출력 함수
//    public List<Integer> printScore() {
//        System.out.println("현재 점수가 등록되어 있는 회차입니다.");
//        List<Integer> roundList = new ArrayList<>();
//        for (Score s : this.getScoreList()) {
//            roundList.add(s.getRound());
//            System.out.print(s.getRound() + "회차 ");
//        }
//        System.out.println();
//        return roundList;
//    }
//
//// 지우 수정: 등급 반환하도록 변경
//    public char makeGrade(int score, int round) {
//        char grade;
//        if (this.getSubjectType().equals("MANDATORY")) { // 필수 과목일 경우
//            if (95 <= score && score <= 100) {
//                grade = 'A';
//            } else if (90 <= score && score <= 94) {
//                grade = 'B';
//            } else if (80 <= score && score <= 89) {
//                grade = 'C';
//            } else if (70 <= score && score <= 79) {
//                grade = 'D';
//            } else if (60 <= score && score <= 69) {
//                grade = 'F';
//            } else {
//                grade = 'N';
//            }
//        } else { // 선택 과목일 경우
//            if (90 <= score && score <= 100) {
//                grade = 'A';
//            } else if (80 <= score && score <= 89) {
//                grade = 'B';
//            } else if (70 <= score && score <= 79) {
//                grade = 'C';
//            } else if (60 <= score && score <= 69) {
//                grade = 'D';
//            } else if (50 <= score && score <= 59) {
//                grade = 'F';
//            } else {
//                grade = 'N';
//            }
//        }
//        this.setScore(round, score, grade); // 등급을 설정한 뒤에 점수를 저장
//        return grade;
//    }
//
//
//    // 과목별 평균 등급 산정
//    public void averageGrade() {
//        System.out.print(this.getSubjectName() + " 의 평균 등급 : ");
//        List<Score> scoreList = this.getScoreList();
//        String subjectType = this.getSubjectType();
//        double sum = 0;
//        int size = scoreList.size();
//        for (Score score : scoreList) {
//            sum += score.getScore();
//        }
//        double average = sum / size;
//        if (subjectType == "MANDATORY") {// 필수 과목일 경우
//            if (95 <= average && average <= 100) {
//                System.out.println("A");
//            } else if (90 <= average && average <= 94) {
//                System.out.println("B");
//            } else if (80 <= average && average <= 89) {
//                System.out.println("C");
//            } else if (70 <= average && average <= 79) {
//                System.out.println("D");
//            } else if (60 <= average && average <= 69) {
//                System.out.println("F");
//            } else {
//                System.out.println("N");
//            }
//        } else { // 선택 과목일 경우
//            if (90 <= average && average <= 100) {
//                System.out.println("A");
//            } else if (80 <= average && average <= 89) {
//                System.out.println("B");
//            } else if (70 <= average && average <= 79) {
//                System.out.println("C");
//            } else if (60 <= average && average <= 69) {
//                System.out.println("D");
//            } else if (50 <= average && average <= 59) {
//                System.out.println("F");
//            } else {
//                System.out.println("N");
//            }
//        }
//    }
}


