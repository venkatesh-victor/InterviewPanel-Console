package com.zsgs.interviewpanel.login;

import java.util.Scanner;

import com.zsgs.interviewpanel.InterviewPanel;
import com.zsgs.interviewpanel.interviewsetup.InterviewSetupView;

public class LoginView {
    
    private LoginModel  loginModel;

    public LoginView() {
        loginModel = new LoginModel(this);
    }

    public void init() {
        System.out.println("--- " + InterviewPanel.getInstance().getAppName() + " --- \nversion "
                + InterviewPanel.getInstance().getAppVersion());

        System.out.println("\n Please login to proceed.");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the user name: ");
        String userName = sc.next();

        System.out.print("Enter the passowrd: ");
        String password = sc.next();

        loginModel.validateUser(userName, password);
    }

    public void onSuccess() {
        System.out.println("Login Sucess");

        InterviewSetupView interviewSetupView = new InterviewSetupView();
        interviewSetupView.init();
    }

    public void showAlert(String str) {
        System.out.println(str);
    }
}
