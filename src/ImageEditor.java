import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class ImageEditor {
    private static boolean exitRequested = false;
    private static Command userCommand = null;
    private static String[] userCommandParameters = null;
    int numberOfParameters = 0;
    private static String input;
    private static String[] inputArray;
    private static Session currentSession;
    private static Image currentImage;

    public ImageEditor() {
        if (!Files.exists(Path.of("Sessions/"))){
            new File("Sessions/").mkdir();
        }

        Scanner scanner = new Scanner(System.in);

        //TODO: When adding the image and the session is null a new session needs to be created.
        do {
            try {
                System.out.print("Enter a command (or type 'exit' to exit): ");

                input = scanner.nextLine();
                inputArray = input.split("\\s+", 2);

                userCommand = Command.valueOf(inputArray[0].toUpperCase());
                userCommand.handle();

            } catch (InvalidCommandFormat e) {
                System.out.println(e.getMessage());
                e.getStackTrace();

            } catch (IllegalArgumentException e) {
                System.out.println("Unknown command!");
                e.getStackTrace();
            }
            userCommand = null;
            inputArray = null;
        } while (!exitRequested);
    }

    public static String[] getUserCommandParameters() {
        return userCommandParameters;
    }

    public static void setUserCommandParameters(String[] userCommandParameters) {
        ImageEditor.userCommandParameters = userCommandParameters;
    }

    public static String[] getInputArray() {
        return inputArray;
    }

    public static void setInputArray(String[] inputArray) {
        ImageEditor.inputArray = inputArray;
    };

    public static Session getCurrentSession() {
        if (currentSession != null){
            return currentSession;
        }
        else {
            return null;
        }
    }

    public static void setCurrentSession(Session currentSession) {
        ImageEditor.currentSession = currentSession;
    }

    public static Image getCurrentImage() {
        return currentImage;
    }

    public static void setCurrentImage(Image currentImage) {
        ImageEditor.currentImage = currentImage;
    }


    public void load() {

    }

    public void save() {

    }

    public static void switchSession() {

    }

    public void collage() {

    }

    public static void help() {
        if (inputArray.length == 1){
            //TODO: Add a list of all commands
            System.out.println("List of all commands");
        }
        else {
            userCommandParameters = inputArray[1].split("\\s+", 1);
            System.out.println(Command.valueOf(userCommandParameters[0].toUpperCase()).help());
        }
    }

    public static void exit() {
        exitRequested = true;
    }

}