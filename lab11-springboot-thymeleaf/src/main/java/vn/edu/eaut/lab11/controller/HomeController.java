package vn.edu.eaut.lab11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Hệ thống quản lý sinh viên");
        model.addAttribute("message", "Chào mừng đến với Spring Boot và Thymeleaf");
        model.addAttribute("description",
                "Ứng dụng minh họa Spring MVC cơ bản, truyền dữ liệu bằng Model và hiển thị giao diện động bằng Thymeleaf.");
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("course", "Công nghệ Java");
        model.addAttribute("chapter", "Chương 4 - Spring Framework");
        model.addAttribute("description",
                "Lab 11 giúp sinh viên làm quen với Spring Boot, Spring MVC, Maven và Thymeleaf.");
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("faculty", "Khoa Công nghệ Thông tin");
        model.addAttribute("subject", "Học phần Công nghệ Java");
        model.addAttribute("email", "info@eaut.edu.vn");
        model.addAttribute("phone", "024 3689 0201");
        return "contact";
    }
}
