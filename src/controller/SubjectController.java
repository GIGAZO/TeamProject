package TeamProject.src.controller;

import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectController {

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
}
