package boundary;

import controller.AccountController;
import controller.ApplicantController;
import controller.IOController;
import controller.UIController;
import entity.list.ApplicantList;

public class ApplicantPage {

    public static void allOptions() {
        UIController.clearPage();
        System.out.println(UIController.lineSeparator);
        System.out.println("Applicant Page");
        System.out.println(UIController.lineSeparator);
        System.out.println("Welcome, " + ApplicantList.getInstance().getByID(AccountController.getUserID()).getName() + ". Please enter your choice."
                + "\n\t1. View Applicable Project"
                + "\n\t2. View Applied Projects"
                + "\n\t3. Apply for Project"
                + "\n\t4. Withdraw Application"
                + "\n\t5. Make Query"
                + "\n\t6. View Query"
                + "\n\t7. Edit Query"
                + "\n\t8. Delete Query"
                + "\n\t9. Exit");
        System.out.print("Your choice (1-9): ");
        int option = IOController.nextInt();
        switch (option) {
            case 1 -> viewApplicableProject();
            case 2 -> viewAppliedProject();
            case 3 -> applyProject();
            case 4 -> withdrawApplication();
            case 5 -> query();
            case 6 -> viewQuery();
            case 7 -> editQuery();
            case 8 -> deleteQuery();
            case 9 -> UIController.exit();
            default -> {
                System.out.println("Invalid choice. Press ENTER to try again.");
                IOController.nextLine();
                allOptions();
            }
        }
    }

    

    public static void viewApplicableProject() {
        ApplicantController.viewApplicableProject();
        UIController.loopApplicant();
    }

    public static void viewAppliedProject() {
        ApplicantController.viewAppliedProject();
        UIController.loopApplicant();
    }

    public static void applyProject() {
        System.out.print("Enter the project ID to apply: ");
        String projectID = IOController.nextLine();
        ApplicantController.applyProject(projectID);
        UIController.loopApplicant();
    }

    public static void withdrawApplication() {
        ApplicantController.withdrawApplication();
        UIController.loopApplicant();
    }

    public static void query() {
        System.out.print("Enter your query: ");
        String question = IOController.nextLine();
        ApplicantController.query(question);
        UIController.loopApplicant();
    }

    public static void viewQuery() {
        ApplicantController.viewQuery();
        UIController.loopApplicant();
    }

    public static void editQuery() {
        System.out.print("Enter the request ID to edit: ");
        String requestID = IOController.nextLine();
        System.out.print("Enter the new query: ");
        String newQuery = IOController.nextLine();
        ApplicantController.editQuery(requestID, newQuery);
        UIController.loopApplicant();
    }

    public static void deleteQuery() {
        System.out.print("Enter the request ID to delete: ");
        String requestID = IOController.nextLine();
        ApplicantController.deleteQuery(requestID);
        UIController.loopApplicant();
    }
}
