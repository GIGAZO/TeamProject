package TeamProject.src.controller;

import TeamProject.src.model.Score;
import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreController {

    StudentController studentController = new StudentController();
    SubjectController subjectController = new SubjectController();
    Scanner sc = new Scanner(System.in);

    // 수강생의 과목별 시험 회차 및 점수 등록 (효진님 파트)
    public void createScore(List<Student> studentStore) {
        // 관리할 수강생 고유 번호
        String studentId = studentController.getStudentId(studentStore);
        String subjectId = "";

        System.out.println("점수를 등록할 과목 고유번호를 입력해주세요.");
        // 해당 수강생이 듣는 과목 출력
        Student student = subjectController.printSubjectByStudent(studentId, studentStore);
        // 과목 선택
        while (true) {
            String subNum = sc.next();
            boolean flag = false;
            for (Subject s : student.getSubjectList()) {
                if (s.getSubjectId().equals(subNum)) {
                    subjectId = s.getSubjectId();
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("현재 수강생이 수강하고 있는 과목이 아닙니다. 수강생이 수강하고 있는 과목으로 입력해주세요.");
            } else {
                break;
            }
        }
        // 해당 과목 회차별 등급 출력
        List<Integer> roundList = printIsScore(student, subjectId);

        System.out.println("시험 점수를 새로 등록할 회차(숫자만)를 입력해주세요.");

        // 몇 회차입력 받기
        int round = 0;
        while (true) {
            round = sc.nextInt();
            // 이미 있는 회차이면 예외처리해서 다시 받기
            if (roundList.contains(round)) {
                System.out.println("이미 등록된 회차입니다. 등록되지 않은 회차(숫자만)를 선택해주세요.");
            } else if (1 > round || round > 10) {
                System.out.println("1 ~ 10 회차만 등록이 가능합니다. 올바른 범위의 회차(숫자만)를 입력해주세요. ");
            } else {
                break;
            }
        }

        // 점수 입력받기
        System.out.println("등록할 점수를 입력해주세요.");
        int score = 0;
        while (true) {
            score = sc.nextInt();
            // 점수 범위를 벗어나면 예외처리해서 다시 받기
            if (score < 0 || score > 100) {
                System.out.println("점수는 0 ~ 100 사이의 숫자입니다. 올바른 점수를 입력해주세요. ");
            } else {
                break;
            }
        }
        // 받은 점수를 통해 등급 계산
        // 회차, 점수, 등급을 수강생의 해당 과목 정보에 추가
        makeGrade(subjectId, student, score, round);

        System.out.println("\n점수 등록 성공!");
    }

    /* 수강생의 과목별 회차 점수 수정 (지우님 파트) */
    public void updateRoundScoreBySubject(List<Student> studentStore) {
        // 관리할 수강생 고유 번호
        String studentId = studentController.getStudentId(studentStore);
        String subjectId = "";
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

        /* 해당 수강생이 듣는 과목 출력 */
        System.out.println("현재 수강중인 과목: ");
        Student student = subjectController.printSubjectByStudent(studentId, studentStore);

        System.out.println("\n시험 점수를 수정합니다...");

        /* 과목 수정 */
        System.out.print("\n수정할 과목 고유번호를 입력하세요: ");
        String subjectNum = sc.next();

        Subject selectedSubject = null;
        for (Subject subject : student.getSubjectList()) {
            if (subjectNum.equals(subject.getSubjectId())) {
                selectedSubject = subject;
                break;
            }
        }
        if (selectedSubject == null) {
            System.out.println("입력한 과목 번호에 해당하는 과목이 없습니다.");
            return;
        }

        /* 회차 수정 */
        List<Integer> roundList = printIsScore(student, selectedSubject.getSubjectId());

        System.out.print("수정할 회차(숫자만)를 입력해주세요: ");
        int round;
        while (true) {
            round = sc.nextInt();
            if (!roundList.contains(round)) {
                System.out.print("등록되지 않은 회차입니다. 다시 입력해주세요: ");
            } else {
                break;
            }
        }

        // 이전 정보 저장
        int prevScore = 0;
        char prevGrade = 'N';
        Score removeScore = null;
        for (Score s : student.getScoreList()) {
            if (s.getSubjectId().equals(subjectNum) && s.getRound() == round) {
                removeScore = s;
                prevScore = s.getScore();
                prevGrade = s.getGrade();
                break;
            }
        }
        student.getScoreList().remove(removeScore);

        System.out.print("새로운 점수를 입력하세요: ");
        int newScore;
        while (true) {
            newScore = sc.nextInt();
            if (newScore < 0 || newScore > 100) {
                System.out.print("점수는 0 ~ 100 사이의 숫자입니다. 다시 입력해주세요: ");
            } else {
                break;
            }
        }

        // 해당 과목 및 회차를 가진 수강생의 점수 수정
        char updatedGrade = makeGrade(subjectNum, student, newScore, round);

        System.out.println("이전 점수: " + prevScore);
        System.out.println("수정된 점수: " + newScore);
        System.out.println("이전 등급: " + prevGrade);
        System.out.println("수정된 등급: " + updatedGrade);
    }

    // 수강생의 특정 과목 회차별 등급 조회 (예찬님 파트)
    public void inquireRoundGradeBySubject(List<Student> studentStore) {
        String studentId = studentController.getStudentId(studentStore); // 관리할 수강생 고유 번호
        Subject sub = null;

        Student student = subjectController.printSubjectByStudent(studentId, studentStore);

        System.out.println("점수를 조회할 과목의 번호를 입력해주세요");

        // 과목 선택
        while (true) {
            String subNum = sc.next();
            boolean flag = false;
            for (Subject s : student.getSubjectList()) {
                if (s.getSubjectId().equals(subNum)) {
                    sub = s;
                    flag = true;
                    int count = 1;
                    for(int i = 0; i < student.getScoreList().size(); i++){
                        if (student.getScoreList().get(i).getSubjectId().equals(subNum)) {
                            System.out.println(sub.getSubjectName() + "의 " + count + "회차 등급은 " + student.getScoreList().get(i).getGrade() + "입니다.");
                            count++;
                        }
                    }
                    break;
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
    public void inquireSubjectAverageByStudent(List<Student> studentStore) {
        String studentId = studentController.getStudentId(studentStore);

        // 해당 학생이 듣는 과목 찾기
        for (Student s : studentStore) {
            if (studentId.equals(s.getStudentId())) {
                for (Subject sub : s.getSubjectList()) {
                    averageGrade(sub, s);
                }
                break;
            }
        }
    }

    // 정효진 수정 -> 해당 과목의 점수가 등록되어 있는 회차 출력 함수
    public List<Integer> printIsScore(Student student, String subjectId) {
        List<Integer> roundList = new ArrayList<>();
        for (Score s : student.getScoreList()) {
            if (s.getSubjectId().equals(subjectId)) {
                roundList.add(s.getRound());
            }
        }
        if (roundList.size() == 0) {
            System.out.println("점수가 등록되어 있는 회차가 없습니다.");
        } else {
            System.out.println("현재 점수가 등록되어 있는 회차입니다.");
            roundList.forEach(n -> System.out.print(n + "회차 "));
            System.out.println();
        }
        return roundList;
    }

    /* 특정 상태 수강생들의 필수 과목 평균 등급 조회 (지우님 파트) */
    public void inquireSubjectAverageByStudentStatus(List<Student> studentStore) {
        // 상태 목록을 저장할 리스트 생성
        List<String> statuses = new ArrayList<>();

        // 모든 수강생의 상태를 가져와 중복을 제거해 상태 목록에 저장
        for (Student student : studentStore) {
            String status = student.getStatus();
            if (!statuses.contains(status)) {
                statuses.add(status);
            }
        }

        // 각 상태별로 필수 과목의 평균 등급 조회
        for (String status : statuses) {
            System.out.println(status + " 상태인 수강생들의 필수 과목 평균 등급:");

            for (Student student : studentStore) {
                // 해당 상태의 수강생만 필터링하기
                if (student.getStatus().equals(status)) {
                    // 필수 과목만 필터링해 가져오기
                    List<Subject> mandatorySubjects = new ArrayList<>();
                    for (Subject subject : student.getSubjectList()) {
                        if (subject.getSubjectType().equals("MANDATORY")) {
                            mandatorySubjects.add(subject);
                        }
                    }

                    if (mandatorySubjects.isEmpty()) {
                        System.out.println(student.getStudentName() + "님은 필수 과목을 수강하고 있지 않습니다.");
                        continue;
                    }

                    System.out.println(student.getStudentName() + " : ");
                    for (Subject subject : mandatorySubjects) {
                        averageGrade(subject, student); // 평균 등급을 계산하고 출력
                    }
                }
            }
            System.out.println("-----------------------");
        }
    }

    // 지우 수정: 등급 반환하도록 변경
    public char makeGrade(String subjectId, Student student, int score, int round) {
        char grade;
        String subjectType = "";
        for (Subject s : student.getSubjectList()) {
            if (subjectId.equals(s.getSubjectId())) {
                subjectType = s.getSubjectType();
            }
        }
        if (subjectType.equals("MANDATORY")) { // 필수 과목일 경우
            if (95 <= score && score <= 100) {
                grade = 'A';
            } else if (90 <= score && score <= 94) {
                grade = 'B';
            } else if (80 <= score && score <= 89) {
                grade = 'C';
            } else if (70 <= score && score <= 79) {
                grade = 'D';
            } else if (60 <= score && score <= 69) {
                grade = 'F';
            } else {
                grade = 'N';
            }
        } else { // 선택 과목일 경우
            if (90 <= score && score <= 100) {
                grade = 'A';
            } else if (80 <= score && score <= 89) {
                grade = 'B';
            } else if (70 <= score && score <= 79) {
                grade = 'C';
            } else if (60 <= score && score <= 69) {
                grade = 'D';
            } else if (50 <= score && score <= 59) {
                grade = 'F';
            } else {
                grade = 'N';
            }
        }
        Score newScore = new Score(round, score, grade, student.getStudentId(), subjectId);
        student.setScoreList(newScore);
        return grade;
    }

    // 과목별 평균 등급 산정
    public void averageGrade(Subject subject, Student student) {
        System.out.print(subject.getSubjectName() + " 의 평균 등급 : ");
        double sum = 0;
        int size = 0;
        for (Score score : student.getScoreList()) {
            if (score.getSubjectId().equals(subject.getSubjectId()) && score.getStudentId().equals(student.getStudentId())) {
                sum += score.getScore();
                size += 1;
            }
        }
        double average = sum / size;
        if (subject.getSubjectType() == "MANDATORY") {// 필수 과목일 경우
            if (95 <= average && average <= 100) {
                System.out.println("A");
            } else if (90 <= average && average <= 94) {
                System.out.println("B");
            } else if (80 <= average && average <= 89) {
                System.out.println("C");
            } else if (70 <= average && average <= 79) {
                System.out.println("D");
            } else if (60 <= average && average <= 69) {
                System.out.println("F");
            } else {
                System.out.println("N");
            }
        } else { // 선택 과목일 경우
            if (90 <= average && average <= 100) {
                System.out.println("A");
            } else if (80 <= average && average <= 89) {
                System.out.println("B");
            } else if (70 <= average && average <= 79) {
                System.out.println("C");
            } else if (60 <= average && average <= 69) {
                System.out.println("D");
            } else if (50 <= average && average <= 59) {
                System.out.println("F");
            } else {
                System.out.println("N");
            }
        }
    }
}