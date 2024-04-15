package com.zsgs.interviewpanel.interviewsetup;

import com.zsgs.interviewpanel.model.Interview;
import com.zsgs.interviewpanel.datalayer.InterviewDatabase;

public class InterviewSetupModel {
    private InterviewSetupView interviewSetupView;
    private InterviewDatabase interviewDatabase;
    private Interview interview;

    public InterviewSetupModel(InterviewSetupView interviewSetupView) {
        this.interviewSetupView = interviewSetupView;
        interviewDatabase = InterviewDatabase.getInstance();
        interview = InterviewDatabase.getInstance().getInterview();
    }

    public void setInterview(
        String interviewName,
        String date,
        String venue
    ) {
        Interview interview = new Interview();

        interview.setInterviewName(interviewName);
        interview.setDate(date);
        interview.setVenue(venue);

        interviewDatabase.insertInterview(interview);
        interviewSetupView.onSetupComplete();
    }

    public void startSetup() {
        if(interview == null) {
            interviewSetupView.initiateSetup();
        } else {
            interviewSetupView.onSetupComplete();
        }
    }
}
