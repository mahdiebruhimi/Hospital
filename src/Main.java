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
        int wrongPassword = 0;
        String subMenuItem = "";

        while (!menuItem.equals("exit")) {

            switch (menuItem) {
                case "nurse":

                    if (wrongPassword < 3) {

                        while (!subMenuItem.equals("back")) {
                            System.out.println("Enter nurse ID:");
                            String nurseId = input.next();

                            if (nurse.validation(nurseId)) {
                                boolean nurseValidation = nurse.getPassword(nurseId);


                                if (!nurseValidation) {
                                    wrongPassword++;
                                    if (wrongPassword < 3) {
                                        System.out.println("You can enter the wrong password only "  + (3 - wrongPassword) + " more times!");
                                    }
                                    else {
                                        System.out.println("Your number of logins has expired.");
                                        System.out.println("You do not have permission to access the application.");
                                        return;
                                    }

                                    if (wrongPassword < 3) {
                                        System.out.println("Please try again to login.");
                                    }
                                    System.out.println();
                                    break;
                                }

                                System.out.println("Please select an item:\nupdatePatientFile | changePassword | back | exit");
                                subMenuItem = input.next();

                                while (!subMenuItem.equals("back")) {

                                    switch (subMenuItem) {
                                        case "updatePatientFile":
                                            boolean updateFile = patient.updateFile();
                                            if (updateFile) {
                                                subMenuItem = "back";
                                                menuItem = "exit";
                                            } else {
                                                return;
                                            }
                                            break;

                                        case "changePassword":
                                            System.out.println("Enter old password:");
                                            String password = input.next();

                                            boolean passwordValidation = nurse.validation(nurseId, password);
                                            if (passwordValidation) {
                                                nurse.updateFile(nurseId);
                                                subMenuItem = "back";
                                            }
                                            break;

                                        case "exit":
                                            return;

                                        default:
                                            System.out.println("The chosen option is not correct.");
                                    }

                                    if (nurseValidation) {
                                        System.out.println("Please select an item:\nupdatePatientFile | changePassword | back | exit");
                                        subMenuItem = input.next();
                                    }
                                }
                            }
                        }
                    }

                    else {
                        subMenuItem = "back";
                        menuItem = "exit";
                        return;
                    }

                    break;

                case "reception":
                    System.out.println("Please select an item:\nadd(new patient) | remove(a patient) | search(patient) | display(all patients file) | back | exit");
                    subMenuItem = input.next();

                    while (!subMenuItem.equals("back")) {
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

                            case "exit":
                                return;

                            default:
                                System.out.println("The chosen option is not correct.");
                        }

                        System.out.println("Please select an item:\nadd(new patient) | remove(a patient) | search(patient) | display(all patients file) | back | exit");
                        subMenuItem = input.next();
                    }
                    break;

                case "exit":
                    return;

                default:
                    System.out.println("The chosen option is not correct.");
            }

            subMenuItem = "";
            System.out.println("Please select an item:\nnurse | reception | exit");
            menuItem = input.next();
        }
    }
}