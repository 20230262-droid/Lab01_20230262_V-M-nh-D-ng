package vn.edu.eaut.lab6.model;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String className;
    private String email;


    /*
     * Constructor rỗng
     * Cần thiết cho việc tạo object trong JSP/Servlet
     */
    public Student() {
    }


    /*
     * Constructor đầy đủ
     */
    public Student(
            String id,
            String name,
            String className,
            String email) {

        this.id = id;
        this.name = name;
        this.className = className;
        this.email = email;
    }


    /*
     * =========================
     * GET ID
     * =========================
     */
    public String getId() {
        return id;
    }


    /*
     * =========================
     * SET ID
     * =========================
     */
    public void setId(String id) {
        this.id = id;
    }


    /*
     * =========================
     * GET NAME
     * =========================
     */
    public String getName() {
        return name;
    }


    /*
     * =========================
     * SET NAME
     * =========================
     */
    public void setName(String name) {
        this.name = name;
    }


    /*
     * =========================
     * GET CLASS NAME
     * =========================
     */
    public String getClassName() {
        return className;
    }


    /*
     * =========================
     * SET CLASS NAME
     * =========================
     */
    public void setClassName(String className) {
        this.className = className;
    }


    /*
     * =========================
     * GET EMAIL
     * =========================
     */
    public String getEmail() {
        return email;
    }


    /*
     * =========================
     * SET EMAIL
     * =========================
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /*
     * =========================
     * toString()
     * =========================
     */
    @Override
    public String toString() {

        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}