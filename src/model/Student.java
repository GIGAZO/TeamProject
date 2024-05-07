package TeamProject.src.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId; // 고유번호
    private String studentName; // 이름
    private List<Subject> subjectList; // 수강 과목 저장용 List 객체


    public Student(String seq, String studentName) {
        this.studentId = seq; // 입력받은 값 저장
        this.studentName = studentName;
        this.subjectList = new ArrayList<>(); // List 객체 초기화
    }

    // getter setter
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() { return studentId; }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Subject listen) {
        subjectList.add(listen);
    }

}
