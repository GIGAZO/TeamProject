package TeamProject.src;

import TeamProject.src.controller.ScoreController;
import TeamProject.src.controller.StudentController;
import TeamProject.src.controller.SubjectController;
import TeamProject.src.model.Score;
import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore; // 전체 학생 데이터 저장 List
    private static List<Subject> subjectStore; // 과목 목록 생성용 List

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY"; // 필수 과목 설정용 상수
    private static String SUBJECT_TYPE_CHOICE = "CHOICE"; // 선택 과목 설정용 상수

    // index 관리 필드
    private static int studentIndex; // 학생용 인덱스
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex; // 과목용 인덱스
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex; // 점수용 인덱스 (안 써도 될듯)
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    private static StudentController studentController = new StudentController();
    private static SubjectController subjectController = new SubjectController();
    private static ScoreController scoreController = new ScoreController();


    // 실행시킬 메인 메서드
    public static void main(String[] args) {
        setInitData(); // 데이터 저장소들 생성 메서드
        try {
            Student student1 = new Student("1", "김예찬");
            studentStore.add(student1);
            studentStore.get(0).setSubjectList(new Subject("SU1", "Java", SUBJECT_TYPE_MANDATORY));
            studentStore.get(0).setScoreList(new Score(1, 90, 'A', "1", "SU1"));

            displayMainView(); // 실제 로직이 담긴 메서드
        } catch (Exception e) { // 예외처리
            System.out.println(e);
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>(); // 전체 학생 정보를 담을 List를 초기화
        subjectStore = List.of( // 과목별로 객체를 생성해서 전체 과목 List에 담아주기
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 상태별 수강생 목록 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> studentController.createStudent(studentStore,subjectStore); // 수강생 등록
                case 2 -> studentController.inquireStudent(); // 수강생 목록 조회
                case 3 -> studentController.inquireSubjectsByStudentStatus();
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급 조회");
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> scoreController.createScore(studentStore); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> scoreController.updateRoundScoreBySubject(studentStore); // 수강생의 과목별 회차 점수 수정
                case 3 -> scoreController.inquireRoundGradeBySubject(studentStore); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> scoreController.inquireSubjectAverageByStudent(); // 수강생의 과목별 평균 등급 조회
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

}