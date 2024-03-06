import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class ImageEditor {
    private static boolean exitRequested = false;
    private static Command userCommand = null;
    private static String[] userCommandParameters = null;
    int numberOfParameters = 0;
    private static String input;
    private static String[] inputArray;
    private static Session currentSession;
    private static Image currentImage;


    /*Purpose of this Stack is to save all the changes of a given image and
    have the ability to undo(Maybe put it in the Session class instead of here)*/
    private Stack<Image[]> imageChanges; //TODO: maybe temp, maybe make your own stack or use list

    public ImageEditor() {
        Scanner scanner = new Scanner(System.in);

        //TODO: When adding the image and the session is null a new session needs to be created.
        do {
            try {
                if (exitRequested) {
                    break;
                }

                System.out.print("Enter a command (or type 'exit' to exit): ");

                input = scanner.nextLine();
                inputArray = input.split("\\s+", 2);

                userCommand = Command.valueOf(inputArray[0]);
                CommandHandler.getHandle().get(userCommand).run();
            } catch (IllegalArgumentException e) {
                e.getStackTrace();
                System.out.println("Unknown command!");
            }
            userCommand = null;
        } while (true);
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
    }

    ;

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

    public void help() {

    }

    public void exit() {

    }

}