import java.util.Scanner;
public class ImageEditor {
    private static boolean exitRequested = false;
    private static Command userCommand = null;
    private static String[] userCommandParameters = null;


    public ImageEditor(){
        Scanner scanner = new Scanner(System.in);

        do{
            try{
                System.out.print("Enter a command (or type 'exit' to exit): ");

                if (exitRequested){
                    break;
                }

                userCommand = Command.valueOf(scanner.nextLine().toUpperCase());
                executeCommand(userCommand);

            }
            catch (IllegalArgumentException e){
                e.getStackTrace();
                System.out.println("Unknown command");
                userCommand = null;
            }
        } while (userCommand == null);
    };

    private static void executeCommand(Command c){

        switch(c){
            case ADD:
                //TODO: Add code for this command
                break;
            case EXIT:
                //TODO: Add code for this command
                break;
            case HELP:
                //TODO: Add code for this command
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
        }

        userCommand = null;
    }
}