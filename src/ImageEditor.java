import java.io.File;
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
        Scanner scanner = new Scanner(System.in);
        new File("images").mkdir();

        //TODO: When adding the image and the session is null a new session needs to be created.
        do {
            try {
                System.out.print("Enter a command (or type 'exit' to exit): ");

                input = scanner.nextLine();
                inputArray = input.split("\\s+", 2);

                userCommand = Command.valueOf(inputArray[0].toUpperCase());
                userCommand.handle();

            } catch (IllegalArgumentException e) {
                e.getStackTrace();
                System.out.println("Unknown command!");
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
        return currentSession;
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

    private void executeCommand(Command c) {
        Map<String, Command>

        /*switch(c){
            case ADD:
                String imageType;
                try {
                    this.numberOfParameters = 1;
                    userCommandParameters = inputArray[1].split("\\s+", numberOfParameters);
                    imageType = Image.checkImageType(userCommandParameters[0]);
                    System.out.println(imageType);
                }
                catch (InvalidPathException e){
                    System.out.println(e.getMessage());
                }

                //TODO: Add code for this command
                break;
            case EXIT:
                exitRequested = true;

                //TODO: Add code for this command
                break;
            case HELP:
                commandList();
                //TODO: Add code for this command make it so it can have 0 or 1 parameters jf a person wants a help with a specific command
                break;
            case LOAD:
                //TODO: Add code for this command
                break;
            case SAVE:
                //TODO: Add code for this command
                break;
            case UNDO:
                //TODO: Add code for this command
                break;
            case CLOSE:
                //TODO: Add code for this command
                break;
            case SAVEAS:
                //TODO: Add code for this command
                break;
            case SWITCH:
                //TODO: Add code for this command
                break;
            case COLLAGE:
                //TODO: Add code for this command
                break;
            case SESSION:
                //TODO: Add code for this command
                break;
            case NEGATIVE:
                //TODO: Add code for this command
                break;
            case GRAYSCALE:
                //TODO: Add code for this command
                break;
            case MONOCHROME:
                //TODO: Add code for this command
                break;
        }*/

                userCommand = null;
    }


    public void load() {

    }

    public void save() {

    }

    public void switchSession() {

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