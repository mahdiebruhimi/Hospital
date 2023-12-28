import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Nurse extends Hospital {
    Scanner input = new Scanner(System.in);
    ArrayList<ArrayList<String>> list = new ArrayList<>();

    public void initializeList () {
        int i=0;

        try {
            File file = new File("Nurses.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.next();
                list.add(new ArrayList<String>());
                list.get(i).add(data);
                data = myReader.next();
                list.get(i).add(1, data);
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
    public boolean getPassword (String id) {
        System.out.println("Enter nurse password:");
        String password = input.next();

        boolean validation = validation(id, password);

        if (validation) {
            System.out.println("Login successfully.");
            return true;
        }
        else {
            System.out.println("Login not successfully!");
        }
        return false;
    }

    public boolean validation (String id, String password) {
        int index = getIndex(id);
        if (list.get(index).get(1).equals(password)) {
            return true;
        }
        return false;
    }

    public int getIndex (String id) {
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).get(0).equals(id)) {
                return i;
            }
        }
        return -2;
    }

    @Override
    public void updateFile (String id) {
        int index = getIndex(id);
        System.out.println("Enter new password:");
        list.get(index).set(1, input.next());
        System.out.println("Password changed successfully.");
        writeFile();
    }

    @Override
    public void writeFile () {
        {
            try {
                FileWriter myWriter = new FileWriter("Nurses.txt");
                for (int i=0; i< list.size(); i++) {
                    for (int j=0; j<2; j++) {
                        if (j<1) {
                            myWriter.write(list.get(i).get(j) + " ");
                        }
                        else
                        {
                            myWriter.write(list.get(i).get(j));
                        }
                    }
                    if (i != list.size() - 1){
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