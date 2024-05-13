import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ImageEditor {
    private static boolean exitRequested = false;
    private static Command userCommand = null;
    private static String[] userCommandParameters = null;
    private static String input;
    private static String[] inputArray;
    private static Session currentSession;
    private static Image currentImage;
    private static int currentImageIndex;

    public ImageEditor() {
        if (!Files.exists(Path.of("Sessions/"))){
            new File("Sessions/").mkdir();
        }

        Scanner scanner = new Scanner(System.in);

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

    public static int getCurrentImageIndex() {
        return currentImageIndex;
    }

    public static void setCurrentImageIndex(int currentImageIndex) {
        ImageEditor.currentImageIndex = currentImageIndex;
    }


    public void save() {

    }

    public void saveas(){

    }

    public static void switchSession() {
        if (currentSession != null && !currentSession.getImages().isEmpty()){
            currentSession.writeSessionData();
        }
        userCommandParameters = inputArray[1].split("\\s+", 1);

        try {
            currentSession = new Session(Integer.parseInt(userCommandParameters[0]));
            currentImage = currentSession.getImages().getFirst();
        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        }
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