import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class InputParser {
    InputFromUser inputFromUser;
    static ParkingProcedures parkingProcedures;
    public InputParser() {
        inputFromUser = new InputFromUser();
        parkingProcedures = new ParkingProcedures();
    }
    public void parseTextInput(String inputString) {
        String[] inputs = inputString.split(" ");
        switch (inputs.length) {
            case 1:
                try {
                    Method method = inputFromUser.inputFromUserMap.get(inputString);
                    if (method != null) {
                        method.invoke(parkingProcedures);
                    } else {
                        System.out.println("Invalid command entered. Please enter the correct command");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    Method method = inputFromUser.inputFromUserMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(parkingProcedures, inputs[1]);
                    } else {
                        System.out.println("Invalid command entered. Please enter the correct command");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    Method method = inputFromUser.inputFromUserMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(parkingProcedures, inputs[1], inputs[2]);
                    } else {
                        System.out.println("Invalid command entered. Please enter the correct command");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    Method method = inputFromUser.inputFromUserMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(parkingProcedures, inputs[1], inputs[2],inputs[3]);
                    } else {
                        System.out.println("Invalid command entered. Please enter the correct command");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid command entered. Please enter the correct command");
        }
    }
    public void parseFileInput(String filePath) {
     
        // Assuming input to be a valid fileh.
        File inputFile = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    parseTextInput(line.trim());
                }
            } catch (IOException ex) {
                System.out.println("Error in reading the input file.");
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the path specified.");
            e.printStackTrace();
        }
    }
}
