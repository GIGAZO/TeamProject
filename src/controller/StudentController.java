package TeamProject.src.controller;

import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentController {

    private String SUBJECT_TYPE_MANDATORY = "MANDATORY"; // 필수 과목 설정용 상수
    private String SUBJECT_TYPE_CHOICE = "CHOICE"; // 선택 과목 설정용 상수

    private static int studentIndex; // 학생용 인덱스
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex; // 과목용 인덱스
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex; // 점수용 인덱스 (안 써도 될듯)
    private static final String INDEX_TYPE_SCORE = "SC";


    Scanner sc = new Scanner(System.in);
    // 수강생 등록 (상윤님 파트)
    public List<Student> createStudent(List<Student> studentStore, List<Subject> subjectStore) {
        //void 말고 list<student>
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        //Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드

        String studentId = sequence(INDEX_TYPE_STUDENT);
        Student newStudent = new Student(studentId, studentName);

        // 기능 구현 (필수 과목, 선택 과목)
        System.out.println("필수 과목을 선택하세요. 최소 3개:");
        selectSubjects(newStudent, SUBJECT_TYPE_MANDATORY, 3, subjectStore);

        System.out.println("선택 과목을 선택하세요. 최소 2개:");
        selectSubjects(newStudent, SUBJECT_TYPE_CHOICE, 2, subjectStore);

        studentStore.add(newStudent);
        System.out.println("수강생 등록 성공!\n");

        return studentStore;
    }

    public void selectSubjects(Student student, String subjectType, int minimumSubjects, List<Subject> subjectStore) {
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
                if (answer.equalsIgnoreCase("n")) {
                    break;
                }
            }
        }
    }

    // 수강생 목록 조회 (승훈님 파트)
    public void inquireStudent(List<Student> studentStore) {
        System.out.println("\n수강생 목록을 조회합니다...");
        System.out.println("-------------------------------------");
        for(Student student : studentStore) {
            String subjectlist = "";
            for(int i = 0; i < student.getSubjectList().size(); i++) {
                subjectlist += student.getSubjectList().get(i).getSubjectName();
                if(i != student.getSubjectList().size() - 1) {
                    subjectlist += ", ";
                }
            }
            System.out.println("학생 고유번호: " + student.getStudentId());
            System.out.println("학생 이름: " + student.getStudentName());
            System.out.println("선택한 과목: " + subjectlist);
            System.out.println("-------------------------------------");
        }
        // 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");
    }

    public String getStudentId(List<Student> studentStore) {
        //수강생 조회 출력
        inquireStudent(studentStore);
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

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

}
