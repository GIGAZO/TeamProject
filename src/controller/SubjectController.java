package TeamProject.src.controller;

import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static TeamProject.src.Init.SUBJECT_TYPE_CHOICE;
import static TeamProject.src.Init.SUBJECT_TYPE_MANDATORY;

public class SubjectController {

    Scanner sc = new Scanner(System.in);

    public Student printSubjectByStudent(String studentId, List<Student> studentStore) {
        Student student = null;
        List<Subject> subjectList = new ArrayList<>();
        for (Student s : studentStore) {
            if (studentId.equals(s.getStudentId())) {
                student = s;
                subjectList = s.getSubjectList();
                subjectList.forEach(n -> System.out.println(n.getSubjectId()+ " : "+ n.getSubjectName() + " "));
                break;
            }
        }
        return student;
    }

    public void selectSubjects(Student student, String subjectType, int minimumSubjects, List<Subject> subjectStore) {
        // 과목의 타입에 따라 선택지가 만들어지도록
        List<Subject> choiceSubjects = subjectStore.stream()
                .filter(subject -> subject.getSubjectType().equals(subjectType)) // 파라미터에 담긴 과목 타입으로 필터링(같은 것을 찾음)
                .collect(Collectors.toList()); // 필터링의 결과를 다시 리스트로 담는 것 -> availableSubjects에 담김
        int count = 0;
        //System.out.println("과목을 선택하세요(번호 입력):");

        while (count < minimumSubjects || (count < choiceSubjects.size() && count >= minimumSubjects)) {
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
                    System.out.println(selectedSubject.getSubjectName() + "이(가) 추가되었습니다.");
                } else {
                    System.out.println("※ 이미 선택된 과목입니다.");
                }
            } else {
                System.out.println("※ 잘못 입력되었습니다. 다시 선택해주세요.");
            }

            if (count >= minimumSubjects && count < choiceSubjects.size()) {
                System.out.print("과목을 더 추가하시겠습니까? (y/n): ");
                String answer = sc.next();
                //System.out.print("\n=======================================\n");
                if (answer.equalsIgnoreCase("n")) {
                    break;
                }
            } else if (count == choiceSubjects.size()) {
                //System.out.println("모든 선택 가능한 과목이 추가되었습니다.");
                break;
            }
        }

        // 선택된 과목 리스트 출력
        List<String> mandatorySubjects = student.getSubjectList().stream()
                .filter(subject -> subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY))
                .map(Subject::getSubjectName)
                .collect(Collectors.toList());

        List<String> choiSubjects = student.getSubjectList().stream()
                .filter(subject -> subject.getSubjectType().equals(SUBJECT_TYPE_CHOICE))
                .map(Subject::getSubjectName)
                .collect(Collectors.toList());

        System.out.println("선택된 필수 과목: " + String.join(", ", mandatorySubjects));
        System.out.println("선택된 선택 과목: " + String.join(", ", choiSubjects));
    }
}
