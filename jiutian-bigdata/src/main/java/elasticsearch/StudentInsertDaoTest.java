package elasticsearch;

/**
 * Copyright @ 2018 lihao.com
 * All right reserved.
 *
 * @author Li Hao
 * @since 2018/11/14  16:52
 */
public class StudentInsertDaoTest {
    private static StudentInsertDao abc = new StudentInsertDao();

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(12);
        student.setAddr("SHlihao");
        student.setName("Lack55");
        abc.insert(student);
    }
}

