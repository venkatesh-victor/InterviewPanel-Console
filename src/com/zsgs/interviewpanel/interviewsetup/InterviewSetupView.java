package com.zsgs.interviewpanel.interviewsetup;

import com.zsgs.interviewpanel.candidate.CandidateView;
import java.util.Scanner;

public class InterviewSetupView {

    private InterviewSetupModel interviewSetupModel;

    public InterviewSetupView() {
        interviewSetupModel = new InterviewSetupModel(this);
    }

    public void init() {
        interviewSetupModel.startSetup();
    }

    public void onSetupComplete() {
        System.out.println("\nInterview setup completed.");
        new CandidateView().init();
    }

    public void showAlert(String str) {
        System.out.println(str);
    }

    public void initiateSetup() {
        System.out.println("Enter interview details here: ");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name of the post for which the interview is held: ");
        String interviewName = sc.nextLine();

        System.out.print("Enter the interview date in 'dd/mm/yy' format: ");
        String date = sc.nextLine();

        System.out.print("Enter the venue: ");
        String venue = sc.nextLine();

        interviewSetupModel.setInterview(interviewName, date, venue);

    }

}
