package boundary;

import controller.ApplicantController;
import controller.OfficerRequestController;
import controller.OfficerProjectController;
import controller.UIController;

public class OfficerPage {

    // Page management methods
    public static void allOptions() {
        System.out.println("Options:"
                + "\n1. View Applicants Projects List"
                + "\n2. View Applied Projects"
                + "\n3. Apply for Project"
                + "\n4. Withdraw Application"
                + "\n5. Make Query"
                + "\n6. View Query"
                + "\n7. Edit Query"
                + "\n8. Delete Query"
                + "\n9. Register for Project as Officer"
                + "\n10. View Registered Project"
                + "\n11. View All Enquiries"
                + "\n12. View Enquiry by ID"
                + "\n13. Answer Enquiries"
                + "\n14. View Registrable Project List"
                + "\n15. View Applicant Application Status"
                + "\n16. Book Flat for Applicant"
                + "\n17. Generate Receipt"
                + "\n18. Generate Receipt by Applicant"
                + "\n19. Generate Receipt by Project");
        System.out.print("Option selection: ");
        int option = UIController.nextInt();
        switch (option) {
            case 1:
                viewApplicantProjectList();
                break;
            case 2:
                viewAppliedProject();
                break;
            case 3:
                applyProject();
                break;
            case 4:
                withdrawApplication();
                break;
            case 5:
                query();
                break;
            case 6:
                viewQuery();
                break;
            case 7:
                editQuery();
                break;
            case 8:
                deleteQuery();
                break;
            case 9:
                registerProject();
                break;
            case 10:
                viewRegisteredProject();
                break;
            case 11:
                viewEnquiries();
                break;
            case 12:
                viewEnquiry();
                break;
            case 13:
                answerEnquiries();
                break;
            case 14:
                viewOfficerProjectList();
                break;
            case 15:
                viewApplicantApplicationStatus();
                break;
            case 16:
                bookFlat();
                break;
            case 17:
                generateReceipt();
                break;
            case 18:
                generateReceiptByApplicant();
                break;
            case 19:
                generateReceiptByProject();
                break;
        }
    }

    // ApplicantController Methods

    public static void viewApplicantProjectList() {
        ApplicantController.viewApplicableProject();
    }

    public static void viewAppliedProject() {
        ApplicantController.viewAppliedProject();
    }

    public static void applyProject() {
        System.out.println("Enter the project ID to apply: ");
        String projectID = UIController.nextLine();
        ApplicantController.applyProject(projectID);
    }

    public static void withdrawApplication() {
        ApplicantController.withdrawApplication();
    }

    public static void query() {
        System.out.println("Enter your query: ");
        String question = UIController.nextLine();
        ApplicantController.query(question);
    }

    public static void viewQuery() {
        ApplicantController.viewQuery();
    }

    public static void editQuery() {
        System.out.println("Enter the request ID to edit: ");
        String requestID = UIController.nextLine();
        System.out.println("Enter the new query: ");
        String newQuery = UIController.nextLine();
        ApplicantController.editQuery(requestID, newQuery);
    }

    public static void deleteQuery() {
        System.out.println("Enter the request ID to delete: ");
        String requestID = UIController.nextLine();
        ApplicantController.deleteQuery(requestID);
    }

    // OfficerRequestController Methods

    public static void registerProject() {
        System.out.println("Enter the project ID to register: ");
        String projectID = UIController.nextLine();
        OfficerRequestController.registerProject(projectID);
    }

    public static void viewRegisteredProject() {
        OfficerRequestController.viewRegisteredProject();
    }

    public static void viewEnquiries() {
        OfficerRequestController.viewEnquiries();
    }

    public static void viewEnquiry() {
        System.out.println("Enter the request ID to view: ");
        String requestID = UIController.nextLine();
        OfficerRequestController.viewEnquiries(requestID);
    }

    public static void answerEnquiries() {
        System.out.println("Enter the request ID to answer: ");
        String requestID = UIController.nextLine();
        OfficerRequestController.answerEnquiries(requestID);
    }

    // OfficerProjectController Methods

    public static void viewOfficerProjectList() {
        OfficerProjectController.viewProjectList();
    }

    public static void viewApplicantApplicationStatus() {
        OfficerProjectController.viewApplicantApplicationStatus();
    }

    public static void bookFlat() {
        System.out.println("Enter the applicant ID to book a flat: ");
        String applicantID = UIController.nextLine();
        OfficerProjectController.bookFlat(applicantID);
    }

    public static void generateReceipt() {
        OfficerProjectController.generateReceipt();
    }

    public static void generateReceiptByApplicant() {
        System.out.println("Enter the applicant ID to generate receipt: ");
        String applicantID = UIController.nextLine();
        OfficerProjectController.generateReceipt(applicantID);
    }

    public static void generateReceiptByProject() {
        System.out.println("Enter the project ID to generate receipt: ");
        String projectID = UIController.nextLine();
        OfficerProjectController.generateReceipt(projectID);
    }
}
