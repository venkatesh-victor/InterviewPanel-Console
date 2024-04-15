package com.zsgs.interviewpanel.datalayer;

import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Interview;
import com.zsgs.interviewpanel.model.Interviewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InterviewDatabase {
    private static InterviewDatabase interviewDatabase;
    private Interview interview;
    private List<Candidate> candidateList = new ArrayList<>();

    public static InterviewDatabase getInstance() {
        if(interviewDatabase == null) {
            interviewDatabase = new InterviewDatabase();
        } 
        return interviewDatabase;
    }

    public Interview getInterview() {
        return interview;
    }
    
    public void insertInterview(Interview interview) {
        this.interview = interview;
    }

    public void setCandidateList(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }

    public Candidate getCandidate(int id) {
        
        for(Candidate candidate : candidateList) {
            if(candidate.getId() == id) {
                return candidate;
            }
        }

        return null;
    }

    public List<Candidate> sortCandidates() {
        Collections.sort(candidateList, new Comparator<Candidate>() {
            @Override
            public int compare(Candidate o1, Candidate o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });

        return candidateList;
    }
    public void addCandidate(Candidate candidate) {
        candidateList.add(candidate);
    }


    public List<Candidate> getAllCandidates() {
        return candidateList;
    }
}
