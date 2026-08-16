package vn.edu.eaut.lab6.store;

import vn.edu.eaut.lab6.model.Student;

import java.util.ArrayList;
import java.util.List;

public final class StudentStore {

    private static final List<Student> students =
            new ArrayList<>();

    static {

        students.add(
                new Student(
                        "SV001",
                        "Nguyen Van An",
                        "DCCNTT12",
                        "an@example.com"
                )
        );

        students.add(
                new Student(
                        "SV002",
                        "Tran Thi Binh",
                        "DCCNTT12",
                        "binh@example.com"
                )
        );

        students.add(
                new Student(
                        "SV003",
                        "Le Van Cuong",
                        "DCCNTT13",
                        "cuong@example.com"
                )
        );

        students.add(
                new Student(
                        "SV004",
                        "Pham Thi Dung",
                        "DCCNTT13",
                        "dung@example.com"
                )
        );

        students.add(
                new Student(
                        "SV005",
                        "Hoang Van Em",
                        "DCCNTT14",
                        "em@example.com"
                )
        );
    }


    private StudentStore() {
    }


    /*
     * Lấy tất cả sinh viên
     */
    public static synchronized List<Student> findAll() {

        return new ArrayList<>(students);
    }


    /*
     * Tìm kiếm
     */
    public static synchronized List<Student> search(
            String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return findAll();
        }

        String key =
                keyword.trim().toLowerCase();

        List<Student> result =
                new ArrayList<>();

        for (Student student : students) {

            if (contains(student.getId(), key)
                    || contains(student.getName(), key)
                    || contains(student.getClassName(), key)
                    || contains(student.getEmail(), key)) {

                result.add(student);
            }
        }

        return result;
    }


    private static boolean contains(
            String value,
            String key) {

        return value != null
                && value.toLowerCase().contains(key);
    }


    /*
     * Kiểm tra tồn tại
     */
    public static synchronized boolean exists(
            String id) {

        return findById(id) != null;
    }


    /*
     * Thêm
     */
    public static synchronized boolean add(
            Student student) {

        if (student == null) {
            return false;
        }

        if (student.getId() == null
                || student.getId().trim().isEmpty()) {

            return false;
        }

        if (exists(student.getId())) {
            return false;
        }

        students.add(student);

        return true;
    }


    /*
     * Tìm theo ID
     */
    public static synchronized Student findById(
            String id) {

        if (id == null
                || id.trim().isEmpty()) {

            return null;
        }

        for (Student student : students) {

            if (student.getId() != null
                    && student.getId()
                    .equalsIgnoreCase(id.trim())) {

                return student;
            }
        }

        return null;
    }


    /*
     * Cập nhật
     */
    public static synchronized boolean update(
            String id,
            String name,
            String className,
            String email) {

        Student student =
                findById(id);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setClassName(className);
        student.setEmail(email);

        return true;
    }


    /*
     * Xóa
     */
    public static synchronized boolean delete(
            String id) {

        Student student =
                findById(id);

        if (student == null) {
            return false;
        }

        students.remove(student);

        return true;
    }


    /*
     * Đếm
     */
    public static synchronized int count() {

        return students.size();
    }
}