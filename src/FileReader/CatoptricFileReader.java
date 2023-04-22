package FileReader;
import controller.ControllerNative;
import java.io.BufferedReader;
import java.io.FileReader;
public class CatoptricFileReader {
    private final ControllerNative controller;

    public CatoptricFileReader() {
        controller = new ControllerNative();
    }

    public void sendFileToController(String filename) {
        try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            //Assume the file is in the correct format
            //Anton and I discussed this, and we decided the format as follows:
            //row col motor direction steps
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                int row = Integer.parseInt(tokens[0]);
                int col = Integer.parseInt(tokens[1]);
                int motor = Integer.parseInt(tokens[2]);
                int direction = Integer.parseInt(tokens[3]);
                int steps = Integer.parseInt(tokens[4]);
                controller.moveMirror(row, col, motor, direction, steps);
            }
        }catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void invokeNativeConsole() {
        controller.invokeNativeConsole();
    }

    public static void main(String[] args) {
        CatoptricFileReader fileReader = new CatoptricFileReader();
        //Check if the args are valid. That is, two arguments are passed in.
        //And the second argument is -f
        if (!args[0].equals("-f") && !args[0].equals("-c")) {
            System.out.println("Usage: -f -> read from file, -c -> open the controller console");
            return;
        }

        if (args[0].equals("-f")){
            fileReader.sendFileToController(args[1]);
        }
        fileReader.invokeNativeConsole();
    }
}
