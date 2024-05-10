package TeamProject.src;

import TeamProject.src.model.Student;
import TeamProject.src.model.Subject;
import java.util.List;
import static TeamProject.src.View.displayMainView;

public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore; // 전체 학생 데이터 저장 List
    private static List<Subject> subjectStore; // 과목 목록 생성용 List

    private static Init init = new Init();

    // 실행시킬 메인 메서드
    public static void main(String[] args) {
        // 데이터 저장소들 생성 메서드
        studentStore = init.setInitStudent();
        subjectStore = init.setInitSubject();
        try {
            displayMainView(studentStore, subjectStore); // 실제 로직이 담긴 메서드
        } catch (Exception e) { // 예외처리
            System.out.println(e);
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }
}
