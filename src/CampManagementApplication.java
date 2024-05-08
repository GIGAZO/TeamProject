package TeamProject.src;

import TeamProject.src.model.Score;
import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    // 실행시킬 메인 메서드
    public static void main(String[] args) {
        setInitData(); // 데이터 저장소들 생성 메서드
        try {
            // 여기부터
                    Student student1 = new Student("1", "김예찬"); // 학생1 객체 생성
            //student1.setSubjectList(subjectStore.get(0)); // 학생1의 수강 과목 입력
            student1.setSubjectList(subjectStore.get(1));
            student1.setSubjectList(subjectStore.get(2));
            student1.getSubjectList().get(0).setScore(1, 80, 'C'); // 0번째 과목에 점수 입력
            student1.getSubjectList().get(0).setScore(2, 92, 'B'); // 근데 학생의 과목별로 입력이 안되고 같이 입력되는 문제있음
            student1.getSubjectList().get(1).setScore(1, 63, 'F'); // 현재 0번 과목(Java)에 1번 학생 점수랑 2번 학생 점수가 같이 저장됨
            student1.getSubjectList().get(1).setScore(2, 74, 'D'); // 학생별로 SubjectList는 있으나 그 안에 객체가 공유되고 있는 상황인 것 같음

            // 이렇게 과목을 넣을 때 Subject를 새로 생성하면 문제 해결되나 모든 학생이 수강 신청을 할 때마다
            // 이렇게 생성을 하기에는 구조가 별로임
            student1.setSubjectList(new Subject("SU9", "Java", SUBJECT_TYPE_MANDATORY));


            Student student2 = new Student("2", "노상윤");
            student2.setSubjectList(new Subject("SU10", "Java", SUBJECT_TYPE_MANDATORY));
            //student1.setSubjectList(subjectStore.get(0));
            student2.setSubjectList(subjectStore.get(4));
            student2.setSubjectList(subjectStore.get(5));
            student2.getSubjectList().get(0).setScore(1, 81, 'C');
            student2.getSubjectList().get(0).setScore(2, 76, 'D');
            student2.getSubjectList().get(1).setScore(1, 99, 'A');
            student2.getSubjectList().get(2).setScore(2, 90, 'B');

            studentStore.add(student1); // 완성된 학생 정보를 전체 학생 List에 저장
            studentStore.add(student2);

            System.out.println(studentStore.get(1).getSubjectList().get(0).getScoreList().get(0)); // 2번 학생의 1회차 점수가 아닌 1번 학생의 1회차 점수가 나옴

            // 입력 잘 되었는지 테스트용 출력
            for (int i =0; i < studentStore.size(); i++) {
                System.out.println("이름 : " + studentStore.get(i).getStudentName());
                System.out.println("아이디 : " + studentStore.get(i).getStudentId());
                for (int j =0; j < studentStore.get(i).getSubjectList().size(); j++) {
                    System.out.println("수강중인 과목 : " + studentStore.get(i).getSubjectList().get(j).getSubjectName());
                    for (int k =0; k < studentStore.get(i).getSubjectList().get(j).getScoreList().size(); k++)
                    System.out.println(studentStore.get(i).getSubjectList().get(j).getSubjectName() + "의 " + studentStore.get(i).getSubjectList().get(j).getScoreList().get(k));
                }
            }

            // 과목 인덱스 번호 확인용 출력 (출력해보시려면 주석 풀어주세요)
//            for (int i =0; i < subjectStore.size(); i++) {
//                System.out.println("아이디 : " + subjectStore.get(i).getSubjectId());
//                System.out.println("이름 : " + subjectStore.get(i).getSubjectName());
//                System.out.println("타입 : " + subjectStore.get(i).getSubjectType());
//            }
            // 여기까지 테스트용 데이터

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
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }



    // 수강생 등록 (상윤님 파트)
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        //Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드

        String studentId = sequence(INDEX_TYPE_STUDENT);
        Student newStudent = new Student(studentId, studentName);

        // 기능 구현 (필수 과목, 선택 과목)
        System.out.println("필수 과목을 선택하세요. 최소 3개:");
        selectSubjects(newStudent, SUBJECT_TYPE_MANDATORY, 3);

        System.out.println("선택 과목을 선택하세요. 최소 2개:");
        selectSubjects(newStudent, SUBJECT_TYPE_CHOICE, 2);

        studentStore.add(newStudent);
        System.out.println("수강생 등록 성공!\n");
    }

    private static void selectSubjects(Student student, String subjectType, int minimumSubjects) {
        // 과목의 타입에 따라 선택지가 만들어지도록
        List<Subject> choiceSubjects = subjectStore.stream()
                .filter(subject -> subject.getSubjectType().equals(subjectType)) // 파라미터에 담긴 과목 타입으로 필터링(같은 것을 찾음)
                .collect(Collectors.toList()); // 필터링의 결과를 다시 리스트로 담는 것 -> availableSubjects에 담김
        int count = 0;
        System.out.println("과목을 선택하세요(번호 입력):");

        while (true) {
            // 선택지 생성, 과목을 리스트 형태로
            for (int i = 0; i < choiceSubjects.size(); i++) {
                Subject sub = choiceSubjects.get(i);
                System.out.println((i + 1) + ". " + sub.getSubjectName());
            }


            int choice = sc.nextInt() - 1; // 입력 받은 숫자에서 -1로 번호 조정
            if (choice >= 0 && choice < choiceSubjects.size()) {
                Subject selectedSubject = choiceSubjects.get(choice);
                if (!student.getSubjectList().contains(selectedSubject)) {
                    student.setSubjectList(selectedSubject);
                    count++;
                    System.out.println(selectedSubject.getSubjectName() + " 과목이 추가되었습니다.");
                } else {
                    System.out.println("이미 선택된 과목입니다.");
                }
            } else {
                System.out.println("잘못 입력되었습니다. 다시 선택해주세요.");
            }

            if (count >= minimumSubjects) {
                System.out.print("과목 더 추가하시겠습니까? (y/n): ");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("n") && choice == choiceSubjects.size()) {
                    break;
                }
            }
        }
    }




    // 수강생 목록 조회 (승훈님 파트)
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        System.out.println("-------------------------------------");
        for(Student student : studentStore) {
            System.out.println("학생 고유번호: " + student.getStudentId());
            System.out.println("학생 이름: " + student.getStudentName());
            System.out.println("-------------------------------------");
        }
        // 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");
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
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireSubjectAverageByStudent(); // 수강생의 과목별 평균 등급 조회
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 조회용 학생 ID받아오는 메서드 (수정 X)
    private static String getStudentId() {
        //수강생 조회 출력
        inquireStudent();
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }
    private static List<Subject> printSubjectByStudent(String studentId) {
        List<Subject> subList = new ArrayList<>();
        for (Student s : studentStore) {
            if (studentId.equals(s.getStudentId())) {
                subList = s.getSubjectList();
                subList.forEach(n -> System.out.println(n.getSubjectId()+ " : "+ n.getSubjectName() + " "));
                break;
            }
        }
        return subList;
    }

    // 수강생의 과목별 시험 회차 및 점수 등록 (효진님 파트)
    private static void createScore() {
        // 관리할 수강생 고유 번호
        String studentId = getStudentId();
        Subject sub = null;

        System.out.println("점수를 등록할 과목을 선택하시오");
        // 해당 수강생이 듣는 과목 출력
        List<Subject> subList = printSubjectByStudent(studentId);
        // 과목 선택
        while (true) {
            String subNum = sc.next();
            boolean flag = false;
            for (Subject s : subList) {
                if (s.getSubjectId().equals(subNum)) {
                    sub = s;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("현재 학생이 수강하고 있는 과목이 아닙니다. 다시 과목을 선택해주세요.");
            } else {
                break;
            }
        }
        // 해당 과목 회차별 등급 출력
        List<Integer> roundList = sub.printScore();

        System.out.println("시험 점수를 새로 등록할 회차(숫자만)를 입력해주세요.");
        // 몇 회차입력 받기
        int round = 0;
        while (true) {
            round = sc.nextInt();
            // 이미 있는 회차이면 예외처리해서 다시 받기
            if (roundList.contains(round)) {
                System.out.println("이미 등록된 회차입니다. 등록되지 않은 회차를 선택해주세요.");
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
        sub.makeGrade(score, round);

        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정 (지우님 파트)
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호

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
                        List<Score> scores = sub.getScoreList();
                        for (Score score : scores) {
                            registeredRounds.add(score.getRound());
                        }
                        break outerLoop;
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
                        List<Score> scores = sub.getScoreList();
                        for (Score score : scores) {
                            if (round == score.getRound()) {
                                // 이전 점수와 등급 저장
                                int prevScore = score.getScore();
                                char prevGrade = score.getGrade();

                                score.setScore(newScore); // 점수 업데이트
                                char newGrade = sub.makeGrade(newScore, round); // 등급 다시 계산
                                System.out.println("회차 " + round + "의 점수가 수정되었습니다.");
                                System.out.println("수정된 과목: " + sub.getSubjectName());
                                System.out.println("이전 점수: " + prevScore);
                                System.out.println("수정된 점수: " + newScore);
                                System.out.println("이전 등급: " + prevGrade);
                                System.out.println("수정된 등급: " + newGrade);
                                scoreUpdate = true;
                                break outerLoop; // 외부 반복문 종료
                            }
                        }
                    }
                }
            }
        }
        if (!scoreUpdate) {
            System.out.println("수정할 점수를 찾지 못했습니다. 다시 시도해주세요.");
        }
    }

        // 수강생의 특정 과목 회차별 등급 조회 (예찬님 파트)
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        Subject sub = null;

        List<Subject> subList = printSubjectByStudent(studentId);

        System.out.println("점수를 조회할 과목의 번호를 입력해주세요");

        // 과목 선택
        while (true) {
            String subNum = sc.next();
            boolean flag = false;
            for (Subject s : subList) {
                if (s.getSubjectId().equals(subNum)) {
                    sub = s;
                    flag = true;
                    for(int i = 0; i < sub.getScoreList().size(); i++){
                        System.out.println(sub.getSubjectName() + "의 " + (i + 1) + "회차 등급은 " + s.getScoreList().get(i).getGrade() + "입니다.");
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
    private static void inquireSubjectAverageByStudent(){
        String studentId = getStudentId();
        List<Subject> subList = null;

        // 해당 학생이 듣는 과목 찾기
        for (Student s : studentStore) {
            if (studentId.equals(s.getStudentId())) {
                subList = s.getSubjectList();
                break;
            }
        }

        for (Subject sub : subList) {
            sub.averageGrade();
        }
    }
}
