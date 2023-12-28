import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Nurse nurse = new Nurse();
        Patient patient = new Patient();

        nurse.initializeList();
        patient.initializeList();


        System.out.println("Please select an item:\nnurse | reception | exit");

        String menuItem = input.next();
        int wrongPasswordNumber = 0;
        String subMenuItem = "";

        while (!menuItem.equals("exit")) {

            switch (menuItem) {

                case "nurse":

                    if (wrongPasswordNumber < 3) {

                        while (!subMenuItem.equals("exit")) {
                            System.out.println("Enter nurse ID:");
                            String nurseId = input.next();

                            if (nurse.validation(nurseId)) {
                                boolean nurseValidation = nurse.getPassword(nurseId);


                                if (!nurseValidation) {
                                    wrongPasswordNumber++;
                                    if (wrongPasswordNumber < 3) {
                                        System.out.println("You can enter the wrong password only "  + (3 - wrongPasswordNumber) + " more times!");
                                    }
                                    else {
                                        System.out.println("Your number of logins has expired.");
                                        System.out.println("You do not have permission to access the application.");
                                        return;
                                    }

                                    if (wrongPasswordNumber < 3) {
                                        System.out.println("Please try again to login.");
                                    }
                                    System.out.println();
                                    break;
                                }

                                System.out.println("Please select an item:\nupdatePatientFile | changePassword | exit");
                                subMenuItem = input.next();

                                while (!subMenuItem.equals("exit")) {

                                    switch (subMenuItem) {
                                        case "updatePatientFile":
                                            boolean updateFile = patient.updateFile();
                                            if (updateFile) {
                                                subMenuItem = "exit";
                                                menuItem = "exit";
                                            } else {
                                                subMenuItem = "exit";
                                            }
                                            break;

                                        case "changePassword":
                                            System.out.println("Enter old password:");
                                            String password = input.next();

                                            boolean passwordValidation = nurse.validation(nurseId, password);
                                            if (passwordValidation) {
                                                nurse.updateFile(nurseId);
                                                subMenuItem = "exit";
                                            }
                                            break;
                                        default:
                                            System.out.println("The chosen option is not correct.");
                                    }

                                    if (nurseValidation) {
                                        System.out.println("Please select an item:\nupdatePatientFile | changePassword | exit");
                                        subMenuItem = input.next();
                                    }
                                }
                            }
                        }
                    }
                    else {
                        subMenuItem = "exit";
                        menuItem = "exit";
                        return;
                    }

                    break;

                case "reception":
                    System.out.println("Please select an item:\nadd(new patient) | remove(a patient) | search(patient) | display(all patients file) | exit");
                    subMenuItem = input.next();

                    while (!subMenuItem.equals("exit")) {
                        switch (subMenuItem) {
                            case "add":
                                patient.add();
                                break;

                            case "remove":
                                patient.remove();
                                break;

                            case "search":
                                patient.search();
                                break;

                            case "display":
                                patient.display();
                                break;

                            default:
                                System.out.println("The chosen option is not correct.");
                        }

                        System.out.println("Please select an item:\nadd(new patient) | remove(a patient) | search(patient) | display(all patients file) | exit");
                        subMenuItem = input.next();
                    }
                    break;

                default:
                    System.out.println("The chosen option is not correct.");
            }

            subMenuItem = "";
            System.out.println("Please select an item:\nnurse | reception | exit");
            menuItem = input.next();
        }
    }
}