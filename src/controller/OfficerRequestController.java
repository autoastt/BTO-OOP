package controller;

import java.util.List;

import entity.list.OfficerList;
import entity.list.ProjectList;
import entity.list.RequestList;
import entity.project.Project;
import entity.request.Enquiry;
import entity.request.OfficerRegistration;
import entity.request.Request;
import entity.request.RequestStatus;
import entity.request.RequestType;
import entity.user.Officer;
import entity.user.RegistrationStatus;
import entity.user.UserType;
import utils.Display;
import utils.IDController;
import utils.UIController;

public class OfficerRequestController {
    private static String officerID;

    public static void setOfficerID(String ID) {
        officerID = ID;
    }

    public static void registerProject(String projectID) {
        Project project = ProjectList.getInstance().getByID(projectID);
        List<String> officerProject = OfficerList.getInstance().getByID(officerID).getOfficerProject();
        if (!project.getApplicantID().contains(officerID) && project.getVisibility()) {
            boolean can = true;
            for (String id : officerProject) {
                Project p = ProjectList.getInstance().getByID(id);
                if (p.getCloseDate().isBefore(project.getOpenDate()) || project.getCloseDate().isBefore(p.getOpenDate())) continue;
            }
            if (can) {
                Officer officer = OfficerList.getInstance().getByID(officerID);
                officerProject.add(projectID);
                officer.setOfficerProject(officerProject);
                officer.setRegistrationStatusByID(projectID, RegistrationStatus.PENDING);
                OfficerList.getInstance().update(officerID, officer);
                RequestList.getInstance().add(new OfficerRegistration(IDController.newRequestID(), RequestType.REGISTRATION, officerID, projectID, RequestStatus.PENDING));
                System.out.println("Successfully applied registeration.");
                return;
            }
        }
        System.out.println("You are not allowed to apply for this project.");
    }

    public static void viewRegisteredProject() {
        System.out.println(UIController.lineSeparator);
        System.out.println("                        Your Projects");
        System.out.println(UIController.lineSeparator);
        boolean has = false;
        List<String> projects = OfficerList.getInstance().getByID(officerID).getOfficerProject();
        List<Project> list = FilterController.filteredListFromID(projects);
        for (Project project : list) {
            has = true;
            Display.displayProject(project, UserType.OFFICER, null);
        }
        if (!has) System.out.println("You don't have any project.");
    }

    public static void viewRegistrationStatus() {
        List<Request> list = RequestList.getInstance().getAll();
        System.out.println(UIController.lineSeparator);
        System.out.println("                      Your Registration History");
        System.out.println(UIController.lineSeparator);
        boolean has = false;
        for (Request request : list) {
            if (request instanceof OfficerRegistration r) {
                if (r.getUserID().equals(officerID)) {
                    has = true;
                    Display.displayRequest(request, UserType.OFFICER);
                }
            }
        }
        if (!has) System.out.println("You haven't registered to any project.");
    }

    public static void viewEnquiries() {
        List<Request> list = RequestList.getInstance().getAll();
        Officer o = OfficerList.getInstance().getByID(officerID);
        List<String> projectID = o.getOfficerProject();
        boolean has = false;
        for (Request request : list) {
            for (String id : projectID) {
                if (request.getProjectID().equals(id) && request.getRequestType() == RequestType.ENQUIRY) {
                    has = true;
                    Display.displayRequest(request, UserType.OFFICER);
                    break;
                }
            }
        }
        if (!has) System.out.println("There is no enquiry.");
    }

    public static void viewEnquiries(String projectID) {
        Officer officer = OfficerList.getInstance().getByID(officerID);
        List<String> project = officer.getOfficerProject();
        if (!project.contains(projectID)) {
            System.out.println("You are not allowed to view enquiries of other's project.");
            return;
        }
        List<Request> list = RequestList.getInstance().getAll();
        boolean has = false;
        for (Request request : list) {
            if (request.getProjectID().equals(projectID) && request.getRequestType() == RequestType.ENQUIRY) {
                has = true;
                Display.displayRequest(request, UserType.OFFICER);
            }
        }
        if (!has) System.out.println("There is no enquiry for this project.");
    }

    public static void answerEnquiry(String requestID, String text) {
        Enquiry enquiry = (Enquiry) RequestList.getInstance().getByID(requestID);
        enquiry.setAnswer(text);
        enquiry.setRequestStatus(RequestStatus.DONE);
        RequestList.getInstance().update(requestID, enquiry);
    }
}