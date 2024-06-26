package TeamProject.src.controller;

import TeamProject.src.Init;
import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import static TeamProject.src.Init.*;

public class StudentController {

    Scanner sc = new Scanner(System.in);
    private static Init init = new Init();

    SubjectController subjectController = new SubjectController();

    // 수강생 등록 (상윤님 파트)
    public List<Student> createStudent(List<Student> studentStore, List<Subject> subjectStore) {
        System.out.println("==================================");
        System.out.println("[수강생 등록]");
        System.out.print("• 이름 : ");
        String studentName = sc.next();
        System.out.println("\n");

        String studentId = init.sequence(INDEX_TYPE_STUDENT);
        Student newStudent = new Student(studentId, studentName);

        // 기능 구현 (필수 과목, 선택 과목)
        System.out.println("• 필수과목 / 3개 이상의 과목을 번호로 선택하세요.");
        subjectController.selectSubjects(newStudent, SUBJECT_TYPE_MANDATORY, 3, subjectStore);

        System.out.println("\n");
        System.out.println("• 선택과목 / 2개 이상의 과목을 번호로 선택하세요");
        subjectController.selectSubjects(newStudent, SUBJECT_TYPE_CHOICE, 2, subjectStore);

        System.out.println("\n");
        System.out.println("• 상태 선택 / 해당하는 상태를 번호로 선택하세요.");
        selectStatus(newStudent);

        System.out.println("\n");
        studentStore.add(newStudent);
        System.out.println("수강생 등록 성공! 이 전 화면으로 돌아갑니다...");
        //System.out.println("==================================");

        return studentStore;
    }

    public void selectStatus(Student student) {
        while (true) {
            System.out.println("1. Green 2. Red 3. Yellow");
            System.out.print("상태를 선택하세요: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    student.setStatus("Green");
                    break;
                case 2:
                    student.setStatus("Red");
                    break;
                case 3:
                    student.setStatus("Yellow");
                    break;
                default:
                    System.out.println("잘못 입력되었습니다.. 다시 선택해주세요.");
                    continue;
            }
            System.out.println("선택된 상태: " + student.getStatus());
            break;
        }
    }

    public void updateStudentInfo(List<Student> studentStore, List<Subject> subjectStore) {
        if (studentStore.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
            return;
        }

        System.out.println("수강생 목록을 조회합니다...");
        printStudent(studentStore); // 기존 수강생 목록 출력
        System.out.println("수정할 수강생의 번호를 입력하시오...");

        String id = getStudentId(studentStore); // 수강생 고유번호 입력 받기
        Student student = studentStore.stream()
                .filter(s -> s.getStudentId().equals(id))
                .findFirst()
                .orElse(null);

        if (student == null) {
            System.out.println("해당 ID의 수강생을 찾을 수 없습니다.");
            return;
        }

        System.out.println("새로운 정보를 입력합니다.");
        // 이름 업데이트
        System.out.print("• 이름: ");
        String studentName = sc.next();
        student.setStudentName(studentName);

        // 과목 목록 초기화 및 새로 선택
        student.getSubjectList().clear();
        System.out.println("• 필수과목 / 3개 이상의 과목을 번호로 선택하세요.");
        subjectController.selectSubjects(student, SUBJECT_TYPE_MANDATORY, 3, subjectStore);
        System.out.println("• 선택과목 / 2개 이상의 과목을 번호로 선택하세요.");
        subjectController.selectSubjects(student, SUBJECT_TYPE_CHOICE, 2, subjectStore);

        // 상태 업데이트
        selectStatus(student);

        System.out.println("수강생 정보가 성공적으로 수정되었습니다.");
    }

    // 수강생 목록 조회 (승훈님 파트)
    public void inquireStudent(List<Student> studentStore) {
        System.out.println("\n수강생 목록을 조회합니다...");
        System.out.println("-------------------------------------");
        printStudent(studentStore);
        System.out.println("\n수강생 목록 조회 성공!");
    }

    public void printStudent(List<Student> studentStore) {
        if (studentStore.isEmpty()) {
            System.out.println("해당 조건에 맞는 수강생이 없습니다.");
        }
        for(Student student : studentStore) {
            String subjectList = "";
            for(int i = 0; i < student.getSubjectList().size(); i++) {
                subjectList += student.getSubjectList().get(i).getSubjectName();
                if(i != student.getSubjectList().size() - 1) {
                    subjectList += ", ";
                }
            }
            System.out.println("수강생 고유번호: " + student.getStudentId());
            System.out.println("수강생 이름: " + student.getStudentName());
            System.out.println("선택한 과목: " + subjectList);
            System.out.println("수강생 상태: " + student.getStatus());
            System.out.println("-------------------------------------");
        }
    }

    public String getStudentId(List<Student> studentStore) {
        //수강생 조회 출력
        inquireStudent(studentStore);
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 상태별 수강생 목록 조회 (효진님 파트)
    public void inquireStudentsByStudentStatus(List<Student> studentStore) {
        System.out.println("조회하고 싶은 수강생의 상태를 [숫자로] 입력해주세요.");
        System.out.println("1. Green 2. Red 3. Yellow");
        while (true) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    printStudentByStatus(studentStore, "Green");
                    break;
                case 2:
                    printStudentByStatus(studentStore, "Red");
                    break;
                case 3:
                    printStudentByStatus(studentStore, "Yellow");
                    break;
                default:
                    System.out.println("올바르지 않는 입력이 들어왔습니다. [숫자] 1,2,3 중 하나를 입력해주세요.");
            }
            break;
        }
    }

    public void printStudentByStatus(List<Student> studentStore, String status) {
        List<Student> statusStudent = new ArrayList<>();
        for (Student student : studentStore) {
            if (student.getStatus().equals(status)) {
                statusStudent.add(student);
            }
        }
        System.out.println(status + "상태인 수강생들을 조회합니다.");
        System.out.println("-------------------------------------");
        printStudent(statusStudent);
    }

    // 수강생 삭제 (예찬님 파트)
    public void removeStudent(List<Student> studentStore) {
        String studentId = getStudentId(studentStore);

        System.out.println("해당 학생을 삭제하시겠습니까? (y/n) : ");
        String answer = sc.next();
        switch (answer) {
            case "y": {
                for (int i = 0; i < studentStore.size(); i++) {
                    if (studentStore.get(i).getStudentId().equals(studentId)) {
                        for (int j = 0; j < studentStore.get(i).getScoreList().size(); j++)
                            studentStore.get(i).getScoreList().remove(j);
                        studentStore.remove(i);
                    }
                }
                System.out.println("수강생 삭제 완료!");
                break;
            }
            case "n": {
                System.out.println("수강생 삭제를 취소합니다.");
                break;
            }
            default: {
                System.out.println("올바른 문자를 입력해주세요.");
            }
        }
    }
}
