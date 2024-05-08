package TeamProject.src.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId; // 고유번호
    private String studentName; // 이름
    private List<Subject> subjectList; // 수강 과목 저장용 List 객체
    private List<Score> scoreList;
    private String status; // 수강생 상태

    public Student(String seq, String studentName) {
        this.studentId = seq; // 입력받은 값 저장
        this.studentName = studentName;
        this.subjectList = new ArrayList<>(); // List 객체 초기화
        this.scoreList = new ArrayList<>(); // List 객체 초기화
        this.status = ""; // 수강생 상태 초기화
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
        this.subjectList.add(listen);
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(Score score) {
        this.scoreList.add(score);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
