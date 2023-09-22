package ASM3.form;

import ASM3.config.JwtUtil;

public class LoginForm {
    private String email;
    private String password;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginForm() {
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjkyNjc3NTcwLCJyb2xlIjoiMiIsImV4cCI6MTY5Mjc2Mzk3MH0.lQUzISQx56daK5ONQFeVhqdyAMHd6x15p3vvgROodv8XdRfMiXHXtpWWXpEySNLSKcupQKNjSjAoUAFjxlcRGw";
        System.out.println(JwtUtil.getRoleFromToken(token));
    }
}