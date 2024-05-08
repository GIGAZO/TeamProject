package TeamProject.src.controller;

import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectController {

    private static List<Student> studentStore;
    public List<Subject> printSubjectByStudent(String studentId) {
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
}
