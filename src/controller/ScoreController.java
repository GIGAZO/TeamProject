package TeamProject.src.controller;

import TeamProject.src.model.Score;
import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreController {

    private static List<Student> studentStore; // 삭제하시고 매개변수로 받아오셔야 합니다
    private Subject subject;
    StudentController studentController = new StudentController();
    SubjectController subjectController = new SubjectController();
    Scanner sc = new Scanner(System.in);
    // 수강생의 과목별 시험 회차 및 점수 등록 (효진님 파트)
    public void createScore() {
        // 관리할 수강생 고유 번호
//        String studentId = studentController.getStudentId();
//        Subject sub = null;
//
//        System.out.println("점수를 등록할 과목을 선택하시오");
//        // 해당 수강생이 듣는 과목 출력
//        List<Subject> subList = subjectController.printSubjectByStudent(studentId);
//        // 과목 선택
//        while (true) {
//            String subNum = sc.next();
//            boolean flag = false;
//            for (Subject s : subList) {
//                if (s.getSubjectId().equals(subNum)) {
//                    sub = s;
//                    flag = true;
//                    break;
//                }
//            }
//            if (!flag) {
//                System.out.println("현재 학생이 수강하고 있는 과목이 아닙니다. 다시 과목을 선택해주세요.");
//            } else {
//                break;
//            }
//        }
//        // 해당 과목 회차별 등급 출력
//        //List<Integer> roundList = subjectController.printScore();
//
//        System.out.println("시험 점수를 새로 등록할 회차(숫자만)를 입력해주세요.");
//        // 몇 회차입력 받기
//        int round = 0;
//        while (true) {
//            round = sc.nextInt();
//            // 이미 있는 회차이면 예외처리해서 다시 받기
//            if (roundList.contains(round)) {
//                System.out.println("이미 등록된 회차입니다. 등록되지 않은 회차를 선택해주세요.");
//            } else if (1 > round || round > 10) {
//                System.out.println("1 ~ 10 회차만 등록이 가능합니다. 올바른 범위의 회차(숫자만)를 입력해주세요. ");
//            } else {
//                break;
//            }
//        }
//        // 점수 입력받기
//        System.out.println("등록할 점수를 입력해주세요.");
//        int score = 0;
//        while (true) {
//            score = sc.nextInt();
//            // 점수 범위를 벗어나면 예외처리해서 다시 받기
//            if (score < 0 || score > 100) {
//                System.out.println("점수는 0 ~ 100 사이의 숫자입니다. 올바른 점수를 입력해주세요. ");
//            } else {
//                break;
//            }
//        }
//        // 받은 점수를 통해 등급 계산
//        // 회차, 점수, 등급을 수강생의 해당 과목 정보에 추가
//        sub.makeGrade(score, round);
//
//        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정 (지우님 파트)
    public void updateRoundScoreBySubject() {
        String studentId = studentController.getStudentId(); // 관리할 수강생 고유 번호

        // 관리할 수강생 존재하는지 확인
        boolean studentExist = false;
        for (Student s : studentStore) {
            if (studentId.equals(s.getStudentId())) {
                studentExist = true;
                break;
            }
        }

        if (!studentExist) {
            System.out.println("등록되지 않은 수강생 번호입니다.");
            return;
        }

        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("현재 수강중인 과목: ");
        for (Student student : studentStore) {
            if (studentId.equals(student.getStudentId())) {
                List<Subject> subjects = student.getSubjectList();
                for (Subject sub : subjects) {
                    System.out.println(sub.getSubjectName());
                }
                break;
            }
        }

        System.out.println("\n시험 점수를 수정합니다...");

        System.out.print("\n수정할 과목 이름을 입력하세요: ");
        String subjectName = sc.next();

        // 등록되어 있는 회차 목록 확인
        List<Integer> registeredRounds = new ArrayList<>();
        outerLoop:
        for (Student student : studentStore) {
            if (studentId.equals(student.getStudentId())) {
                List<Subject> subjects = student.getSubjectList();
                for (Subject sub : subjects) {
                    if (subjectName.equals(sub.getSubjectName())) {
//                        List<Score> scores = sub.getScoreList();
//                        for (Score score : scores) {
//                            registeredRounds.add(score.getRound());
//                        }
//                        break outerLoop;
                    }
                }
            }
        }

        System.out.println("현재 등록된 회차 목록: " + registeredRounds);
        System.out.print("수정할 회차를 입력하세요: ");
        int round;
        while (true) {
            round = sc.nextInt();
            if (!registeredRounds.contains(round)) {
                System.out.print("등록되지 않은 회차입니다: 다시 입력하세요: ");
            } else {
                break;
            }
        }

        System.out.print("새로운 점수를 입력하세요: ");
        int newScore;
        while(true){
            newScore = sc.nextInt();
            if (newScore < 0 || newScore > 100) {
                System.out.print("점수는 0 ~ 100 사이의 숫자입니다. 다시 입력해주세요: ");
            } else {
                break;
            }
        }

        // 해당 과목 및 회차를 가진 수강생의 점수 수정
        boolean scoreUpdate = false;
        outerLoop:
        for (Student student : studentStore) {
            if (studentId.equals(student.getStudentId())) {
                List<Subject> subjects = student.getSubjectList();
                for (Subject sub : subjects) {
                    if (subjectName.equals(sub.getSubjectName())) {
//                        List<Score> scores = sub.getScoreList();
//                        for (Score score : scores) {
//                            if (round == score.getRound()) {
//                                // 이전 점수와 등급 저장
//                                int prevScore = score.getScore();
//                                char prevGrade = score.getGrade();
//
//                                score.setScore(newScore); // 점수 업데이트
//                                char newGrade = sub.makeGrade(newScore, round); // 등급 다시 계산
//                                System.out.println("회차 " + round + "의 점수가 수정되었습니다.");
//                                System.out.println("수정된 과목: " + sub.getSubjectName());
//                                System.out.println("이전 점수: " + prevScore);
//                                System.out.println("수정된 점수: " + newScore);
//                                System.out.println("이전 등급: " + prevGrade);
//                                System.out.println("수정된 등급: " + newGrade);
//                                scoreUpdate = true;
//                                break outerLoop; // 외부 반복문 종료
//                            }
                        }
                    }
                }
            }
        }
//        if (!scoreUpdate) {
//            System.out.println("수정할 점수를 찾지 못했습니다. 다시 시도해주세요.");
//        }



    // 수강생의 특정 과목 회차별 등급 조회 (예찬님 파트)
    public void inquireRoundGradeBySubject() {
        String studentId = studentController.getStudentId(); // 관리할 수강생 고유 번호
        Subject sub = null;

        List<Subject> subList = subjectController.printSubjectByStudent(studentId);

        System.out.println("점수를 조회할 과목의 번호를 입력해주세요");

        // 과목 선택
        while (true) {
            String subNum = sc.next();
            boolean flag = false;
            for (Subject s : subList) {
                if (s.getSubjectId().equals(subNum)) {
                    sub = s;
                    flag = true;
//                    for(int i = 0; i < sub.getScoreList().size(); i++){
//                        System.out.println(sub.getSubjectName() + "의 " + (i + 1) + "회차 등급은 " + s.getScoreList().get(i).getGrade() + "입니다.");
//                    }
//                    break;
                }
            }
            if (!flag) {
                System.out.println("현재 학생이 수강하고 있는 과목이 아닙니다. 다시 과목을 선택해주세요.");
            } else {
                break;
            }
        }

        System.out.println("\n등급 조회 성공!");
    }

    // 수강생의 과목별 평균 등급 조회 (효진님 파트)
    public void inquireSubjectAverageByStudent(){
//        String studentId = studentController.getStudentId();
//        List<Subject> subList = null;
//
//        // 해당 학생이 듣는 과목 찾기
//        for (Student s : studentStore) {
//            if (studentId.equals(s.getStudentId())) {
//                subList = s.getSubjectList();
//                break;
//            }
//        }
//
//        for (Subject sub : subList) {
//            sub.averageGrade();
//        }
    }

    // 정효진 수정 -> 해당 과목의 점수가 등록되어 있는 회차 출력 함수
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
//    // 지우 수정: 등급 반환하도록 변경
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
