package com.polotechnologies.homejoblink.dataModels;

public class UserRegistration {
    private String userID;
    private String jobCategory;
    private String jobTittle;
    private String jobQualification;
    private String briefDefinition;


    public UserRegistration() {
    }

    public UserRegistration(String userID, String jobCategory, String jobTittle, String jobQualification, String briefDefinition) {
        this.userID = userID;
        this.jobCategory = jobCategory;
        this.jobTittle = jobTittle;
        this.jobQualification = jobQualification;
        this.briefDefinition = briefDefinition;
    }

    public String getUserID() {
        return userID;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public String getJobTittle() {
        return jobTittle;
    }

    public String getJobQualification() {
        return jobQualification;
    }

    public String getBriefDefinition() {
        return briefDefinition;
    }
}
