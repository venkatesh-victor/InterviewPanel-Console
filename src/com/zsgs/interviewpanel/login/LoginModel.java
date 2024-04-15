package com.zsgs.interviewpanel.login;

public class LoginModel {
    
    private LoginView loginView;

    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void validateUser(String userName, String password) {
        if(validateUserName(userName)) {
            if(validatePassword(password)) {
                loginView.onSuccess();
            } else {
                loginView.showAlert("Invalid password");
                loginView.init();
            }
        } else {
            loginView.showAlert("Invalid Username.");
            loginView.init();
        }
    }

    public boolean validateUserName(String userName) {
        return userName.equals("zsgs");
    }
    
    public boolean validatePassword(String password) {
        return password.equals("interviewer");
    }
}