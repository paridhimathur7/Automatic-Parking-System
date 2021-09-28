import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        InputParser inputParser = new InputParser();
        switch (args.length) {
            case 0:
                System.out.println("Please enter 'end' command to terminate this program");
                System.out.println("This is an interactive command panel. Enter a command or run '"+"java -classpath .:/run_dir/junit-4.12.jar:target/dependency/* Main input_file.txt' command in shell"+" to get input from input_file.txt");
                
                for (;;) {
                    try {
                        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                        String inputString = bufferRead.readLine();
                        if (inputString.equalsIgnoreCase("end")) {
                            break;
                        } else if ((inputString == null) || (inputString.isEmpty())) {

                        } else {
                            inputParser.parseTextInput(inputString.trim());
                        }
                    } catch(IOException e) {
                        System.out.println("Oops! Error in reading the input from console.");
                        e.printStackTrace();
                    }
                }
                break;
            case 1:
                // File input/output
                inputParser.parseFileInput(args[0]);
                break;
            default:
                System.out.println("Invalid command entered. Please enter the correct command main.java file");
        }
    }
}
