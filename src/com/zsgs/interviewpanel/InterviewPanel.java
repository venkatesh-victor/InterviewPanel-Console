package com.zsgs.interviewpanel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zsgs.interviewpanel.login.LoginView;
import com.zsgs.interviewpanel.model.Candidate;
import com.zsgs.interviewpanel.model.Interview;
import com.zsgs.interviewpanel.datalayer.InterviewDatabase;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class InterviewPanel {
    
    private static InterviewPanel interviewpanel;
    private String appName = "Interview Management System";
    private String appVersion = "0.1.0";

    private InterviewPanel() {}

    public static InterviewPanel getInstance() {
        if(interviewpanel == null) {
            interviewpanel = new InterviewPanel();
        }
        return interviewpanel;
    }

    private void create() {
        LoginView loginView = new LoginView();
        loginView.init();
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public static void main(String[] args) {
        InterviewDatabase interviewDatabase = InterviewDatabase.getInstance();
        List<Candidate> candidateList = loadUsers();
        Interview interview = loadInterview();

        //for(Candidate candidate : candidateList) {
        //System.out.println(candidate.getName());
        //}

        interviewDatabase.setCandidateList(candidateList);
        interviewDatabase.insertInterview(interview);

        InterviewPanel.getInstance().create();
    }

    private static List<Candidate> loadUsers() {
        Gson gson = new Gson();
        List<Candidate> loadedList = new ArrayList<>();

        try(FileReader reader = new FileReader("C:\\console\\interviewpanel\\src\\com\\zsgs\\interviewpanel\\datalayer\\candidates.json")) {
            StringBuilder sb = new StringBuilder();
            int c;
            while((c = reader.read()) != -1) {
                sb.append((char) c);
            }

            String jsonString = sb.toString();
            if(jsonString.isEmpty()) {
                return loadedList;
            }

            Type type = new TypeToken<List<Candidate>>(){}.getType();
            loadedList = gson.fromJson(jsonString, type);

        } catch(IOException e) {
            e.printStackTrace();
        }

        return loadedList;

    }

    private static Interview loadInterview() {
        try(FileReader reader = new FileReader("C:\\console\\interviewpanel\\src\\com\\zsgs\\interviewpanel\\datalayer\\interview.json")) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Interview.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}