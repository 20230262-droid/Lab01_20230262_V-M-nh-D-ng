package vn.edu.eaut.lab2;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== LAB 2 =====");

        System.out.print("Nhap ma SV: ");
        String id = scanner.nextLine();

        System.out.print("Nhap ho ten: ");
        String name = scanner.nextLine();

        double cc = inputScore(scanner, "chuyen can");
        double gk = inputScore(scanner, "giua ky");
        double ck = inputScore(scanner, "cuoi ky");

        Student sv = new Student(id, name, cc, gk, ck);

        double total = GradeCalculator.calculateFinalScore(sv);
        String grade = GradeCalculator.classify(total);

        System.out.println("\n----- KET QUA -----");
        System.out.println("Ma SV: " + sv.getStudentId());
        System.out.println("Ten: " + sv.getFullName());
        System.out.printf("Diem: %.2f\n", total);
        System.out.println("Xep loai: " + grade);

        scanner.close();
    }

    private static double inputScore(Scanner sc, String label) {
        while (true) {
            try {
                System.out.print("Nhap diem " + label + ": ");
                double s = Double.parseDouble(sc.nextLine());
                GradeCalculator.validateScore(s, label);
                return s;
            } catch (Exception e) {
                System.out.println("Nhap sai! Nhap lai.");
            }
        }
    }
}