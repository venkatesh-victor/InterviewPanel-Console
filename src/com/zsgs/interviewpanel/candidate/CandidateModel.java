package com.zsgs.interviewpanel.candidate;

import com.google.gson.Gson;
import com.zsgs.interviewpanel.datalayer.InterviewDatabase;
import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Interview;
import com.zsgs.interviewpanel.validate.Validate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CandidateModel {
    private CandidateView candidateView;
    private InterviewDatabase interviewDatabase;

    public CandidateModel(CandidateView candidateView) {
        this.candidateView = candidateView;
        interviewDatabase = InterviewDatabase.getInstance();
        Interview interview = InterviewDatabase.getInstance().getInterview();
    }

    public void selectCandidates(int topCandidatesCount) {
        List<Candidate> sortedCandidates = interviewDatabase.sortCandidates();

        for(int i = 0; i < topCandidatesCount; i++) {
            Candidate selectedCandidate = sortedCandidates.get(i);
            System.out.println(selectedCandidate.getName() + " has been selected.");
            System.out.println("Offer letter sent.");
        }
    }


    public void setupCandidate(int id, String name,
        String emailId, String phoneNo, float cgpa) 
    {
        if(interviewDatabase.getCandidate(id) == null) {
            Candidate candidate = new Candidate();
            candidate.setId(id);
            candidate.setName(name);
            candidate.setEmailId(emailId);
            candidate.setPhoneNo(phoneNo);
            candidate.setCgpa(cgpa);

            if(!Validate.isValidEmail(emailId)) {
                candidateView.showAlert("Email is invalid. Try again.");
                candidateView.addCandidate();
            }

            if(!Validate.isValidPhoneNo(phoneNo)) {
                candidateView.showAlert("Phone number is invalid. Try again.");
                candidateView.addCandidate();
            }

            interviewDatabase.addCandidate(candidate);
            candidateView.showAlert("Candidate Added.");
        } else {
            candidateView.showAlert("Candidate already exists");
        }
    }

    public void viewAllCandidates() {
        System.out.println("\n-------------------------------------------------------\n");
        List<Candidate> candidateList = interviewDatabase.getAllCandidates();
        for(int i = 0; i < candidateList.size(); i++) {
            candidateView.showAlert("Candidate Name: " + candidateList.get(i).getName() + ": ");
            candidateView.showAlert("\tID: " + candidateList.get(i).getId());
            candidateView.showAlert("\tEmail: " + candidateList.get(i).getEmailId());
            candidateView.showAlert("\tCGPA: " + candidateList.get(i).getCgpa());
            candidateView.showAlert("\tInterview Marks: " + candidateList.get(i).getScore());
        }
        System.out.println("\n-------------------------------------------------------\n");
    }

    public void updateCandidate(int id, int mark, String status) {
        Candidate candidateToUpdate = interviewDatabase.getCandidate(id);

        if(candidateToUpdate != null) {
            candidateToUpdate.setScore(mark);
            candidateToUpdate.setStatus(status);

            candidateView.showAlert("Candidate details updated successfully.");
        } else {
            candidateView.showAlert("Candidate with the given id does not exist.");
        }
    }

    public void viewSingleCandidate(int id) {
        Candidate singleCandidate = interviewDatabase.getCandidate(id);

        if(singleCandidate != null) {
            candidateView.showAlert("Here is the details of the candidate:- ");
            candidateView.showAlert(singleCandidate.getName());
            candidateView.showAlert("\t" + "Email: "+ singleCandidate.getEmailId());
            candidateView.showAlert("\t" + "Ph.No: "+ singleCandidate.getPhoneNo());
            candidateView.showAlert("\t" + "CGPA: " + singleCandidate.getCgpa());
            candidateView.showAlert("\t" + "Score: " + singleCandidate.getScore());
            candidateView.showAlert("\t" + "Status: " + singleCandidate.getStatus());
        } else {
            candidateView.showAlert("Candidate with the given id does not exist");
        }
    }

    public void saveCandidates(List<Candidate> candidateList, Interview interview) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(candidateList);
        String interviewJsonString = gson.toJson(interview);

        try (FileWriter writer = new FileWriter("C:\\console\\interviewpanel\\src\\com\\zsgs\\interviewpanel\\datalayer\\candidates.json")) {
            writer.write(jsonString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writeObj = new FileWriter("C:\\console\\interviewpanel\\src\\com\\zsgs\\interviewpanel\\datalayer\\interview.json")) {
            writeObj.write(interviewJsonString);
            writeObj.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
