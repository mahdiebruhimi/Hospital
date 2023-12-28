import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Patient extends Hospital {
    Scanner input = new Scanner(System.in);

    ArrayList<ArrayList<String>> list = new ArrayList<>();

    public void initializeList () {
        int i=0;

        try {
            File file = new File("Patients.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.next();
                list.add(new ArrayList<String>());
                list.get(i).add(data);
                data = myReader.next();
                list.get(i).add(1, data);
                data = myReader.next();
                list.get(i).add(2, data);
                data = myReader.next();
                list.get(i).add(3, data);
                data = myReader.next();
                list.get(i).add(4, data);
                data = myReader.next();
                list.get(i).add(5, data);
                data = myReader.next();
                list.get(i).add(6, data);
                i++;
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public boolean getID () {
        System.out.println("Enter patient ID:");
        String id = input.next();

        boolean validation = validation(id);

        if (validation) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean validation (String id) {
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).get(0).equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean updateFile () {
        System.out.println("Enter patient ID:");
        String pateintID = input.next();
        boolean validation = validation(pateintID);
        int patientIndex = -1;

        if (validation) {
            System.out.println("The patient is on the list.");
            System.out.println();

            for (int i=0; i<list.size(); i++) {
                if (list.get(i).get(0).equals(pateintID)) {
                    patientIndex = i;
                    System.out.println("List of drugs:");
                    for (int j=4; j<list.get(i).size(); j++) {
                        System.out.println(list.get(i).get(j));
                    }
                    System.out.println("");
                }
            }



            System.out.println("Please select an item:\nadd(drug) | remove(drug) | edit(drug) | exit");
            String updateDrugs = input.next();

            while (!updateDrugs.equals("exit")) {

                switch (updateDrugs) {
                    case "add":
                        System.out.println("Enter the name of the drug to add to the list:");
                        list.get(patientIndex).add(input.next());
                        System.out.println("The new drug has been successfully added to the list.");
                        writeFile();
                        break;

                    case "remove":
                        System.out.println("Enter the name of the drug to remove from the list:");
                        list.get(patientIndex).remove(input.next());
                        System.out.println("The drug has been successfully removed from the list.");
                        writeFile();
                        break;

                    case "edit":
                        System.out.println("Enter the drug name to edit:");
                        int drugIndex = list.get(patientIndex).indexOf(input.next());
                        System.out.println("Enter the new name to apply the edit:");
                        list.get(patientIndex).set(drugIndex, input.next());
                        System.out.println("Drug editing was done successfully.");
                        writeFile();
                        break;

                    default:
                        System.out.println("The chosen option is not correct.");
                }

                System.out.println("Please select an item:\nadd(drug) | remove(drug) | edit(drug) | exit");
                updateDrugs = input.next();
            }
            return true;
        }

        else {
            System.out.println("There is no patient in the list.");
            return false;
        }
    }

    public void add () {
        int index = list.size();
        list.add(new ArrayList<String>());

        System.out.println("Enter the patient's ID, the patient's name, the patient's doctor's name,\nthe date of hospitalization and the names of the three drugs in the following order:");
        System.out.println("1220 AliAlizadeh dr.Rezazadeh 2023/12/28 Aspirin Amoxicillin Dexamethasone");

        list.get(index).add(input.next());

        list.get(index).add(input.next());

        list.get(index).add(input.next());

        list.get(index).add(input.next());

        list.get(index).add(input.next());

        list.get(index).add(input.next());

        list.get(index).add(input.next());

        System.out.println("The new patient has been successfully added to the list.");
        writeFile();
    }
    public void remove () {
        System.out.println("Enter patient ID for remove from list:");
        String id = input.next();

        boolean validation = validation(id);

        if (validation) {
            for (int i=0; i<list.size(); i++) {
                if (list.get(i).get(0).equals(id)) {
                    list.remove(i);
                    System.out.println("The patient was successfully removed from the list.");
                    writeFile();
                }
            }
        }
        else {
            System.out.println("There is no patient in the list.");
        }
    }
    public void search () {
        System.out.println("Enter patient ID for display file:");
        String id = input.next();

        boolean validation = validation(id);

        if (validation) {
            for (int i=0; i<list.size(); i++) {
                if (list.get(i).get(0).equals(id)) {
                    System.out.println(list.get(i));
                }
            }
        }
        else {
            System.out.println("There is no patient in the list.");
        }
    }
    public void display () {
        ArrayList <String> sortedList = sortList();
        sortedList.sort(Comparator.naturalOrder());
        for (int i=0; i<sortedList.size(); i++) {
            for (int j=0; j<list.size(); j++) {
                if (sortedList.get(i).equals(list.get(j).get(0))) {
                    System.out.println(list.get(j));
                }
            }
        }
    }

    public ArrayList<String> sortList () {
        ArrayList <String> sortedList = new ArrayList<>();
        for (int i=0; i<list.size(); i++) {
            sortedList.add(list.get(i).get(0));
        }
        return sortedList;
    }

    @Override
    public void writeFile () {
        {
            try {
                FileWriter myWriter = new FileWriter("Patients.txt");
                for (int i=0; i< list.size(); i++) {
                    for (int j=0; j<list.get(i).size(); j++) {
                        if (j < list.get(i).size() - 1) {
                            myWriter.write(list.get(i).get(j) + " ");
                        }
                        else {
                            myWriter.write(list.get(i).get(j));
                        }
                    }
                    if (i != list.size() - 1) {
                        myWriter.write("\n");
                    }
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}