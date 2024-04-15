package com.zsgs.interviewpanel.candidate;

import com.zsgs.interviewpanel.datalayer.InterviewDatabase;
import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Interview;

import java.util.Scanner;
import java.util.List;

public class CandidateView {
    private Scanner sc = new Scanner(System.in);
    private CandidateModel candidateModel;
    private InterviewDatabase interviewDatabase;

    public CandidateView() {
        candidateModel = new CandidateModel(this);
        this.interviewDatabase = new InterviewDatabase();
    }

    public void init() {

        while (true) {
            System.out.println("-------------------------------");
            System.out.println("Select your choice.");
            System.out.println("1. Add Candidate");
            System.out.println("2. View All Candidates.");
            System.out.println("3. Update candidate score");
            System.out.println("4. Select Candidate.");
            System.out.println("5. View single candidate.");
            System.out.println("6. Exit.");
            System.out.println("-------------------------------");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addCandidate();
                    break;
                case 2:
                    viewCandidates();
                    break;
                case 3:
                    updateCandidate();
                    break;
                case 4:
                    selectCandidates();
                    break;
                case 5:
                    viewSingleCandidate();
                    break;
                case 6:
                    saveData();
                    return;
                default:
                    System.out.println("Invalid Input.");
                    break;
            }
        }
    }

    private void saveData() {
        List<Candidate> candidateList = InterviewDatabase.getInstance().getAllCandidates();
        Interview interview = InterviewDatabase.getInstance().getInterview();
        candidateModel.saveCandidates(candidateList, interview);
    }

    public void addCandidate() {
        try {
            System.out.print("Enter candidate id: ");
            int id = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter candidate name: ");
            String name = sc.nextLine();

            System.out.print("Enter candidate email: ");
            String emailId = sc.nextLine();

            System.out.print("Enter phoneno: ");
            String phoneNo = sc.nextLine();

            System.out.print("Enter candidate cgpa: ");
            float cgpa = sc.nextFloat();

            candidateModel.setupCandidate(id, name, emailId,
                    phoneNo, cgpa);
        } catch (Exception e) {
            System.out.println("Some inputs are invalid. Try again");
            addCandidate();
        }
    }

    private void viewCandidates() {
        candidateModel.viewAllCandidates();
    }

    private void updateCandidate() {
        System.out.print("Enter the candidate id: ");
        int id = sc.nextInt();

        System.out.print("Enter the mark of the candidate: ");
        int mark = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter the status of the candidate: ");
        String status = sc.nextLine();

        candidateModel.updateCandidate(id, mark, status);

    }

    private void selectCandidates() {
        System.out.print("Enter how many top scorers you want to select: ");
        int topCandidatesCount = sc.nextInt();

        System.out.println("Top " + topCandidatesCount + " candidates will be sent offer letters.");
        System.out.println("Do you want to proceed? (type y for yes and n for no): ");
        char choice = sc.next().charAt(0);

        if (choice == 'y' || choice == 'Y') {
            candidateModel.selectCandidates(topCandidatesCount);
        } else if (choice == 'n' || choice == 'N') {
            init();
        } else {
            System.out.println("Invalid input.");
        }

    }

    public void viewSingleCandidate() {
        System.out.println("Enter candidate id: ");
        int id = sc.nextInt();

        candidateModel.viewSingleCandidate(id);
    }

    public void showAlert(String str) {
        System.out.println(str);
    }
}
