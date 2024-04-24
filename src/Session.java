import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Session {
    private int sessionID;
    private ArrayList<Image> images;
    /*Purpose of this Stack is to save all the changes in the current session and
    have the ability to undo*/
    private Stack<Image[]> sessionChanges; //TODO: maybe temp, maybe make your own stack or use list
    // TODO: In case this stays make it so it saves the last version of the session class before the change in every single method that changes something.

    public Session() {
        images = new ArrayList<>();
        //TODO: finish the constructor
        //TODO: Add ID generator
        for (int i=0; true; i++){
            if (!Files.exists(Path.of("Sessions/Session_" + i))){
                sessionID = i;
                break;
            }
        }
        new File("Sessions/Session_" + sessionID + "/Images").mkdirs();
    }

    public int getSessionID() {
        return sessionID;
    }

    public static void add(){
        //TODO: ADD a check if a image with the same name exists, and change its name to name_1.extension.
        //TODO: Fix a bug where object of a non-existent image is created and saved in the session.
        //TODO: Make it so images are automatically saved in the Session's folder when added.
        ImageType imageType;
        try {
            if (ImageEditor.getCurrentSession() == null){
                ImageEditor.setCurrentSession(new Session());
            }

            // Set the image directory in userCommandParameters
            ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
            imageType = ImageType.valueOf(Image.checkImageType(ImageEditor.getUserCommandParameters()[0]));
            ImageEditor.setCurrentImage(imageType.handle(ImageEditor.getUserCommandParameters()[0]));
            ImageEditor.getCurrentSession().getImages().add(ImageEditor.getCurrentImage());
        }
        catch (InvalidPathException e){
            System.out.println(e.getMessage());
        }

        System.out.println("ADDED!");
        //TODO: Check if the image exists first, then finish the readImageData methods in the PBM/PGM/PPM classes

    }

    public void undo(){

    }

    public void redo(){

    }

    public void sessionInfo(){

    }

    public void switchImage(){
        //TODO: If the session has no images, or has only one image return appropriate message,
        // first show a list of all the images in the session. Then ask
        // which image would you like to switch to. Then switch to the image.
        if (images != null){
            int currentNumber = 0;
            int i;
            int choice;
            System.out.println("Select and image from the list:");


            Scanner scanner = new Scanner(System.in);
            do {
                i = 1;
                for (Image image : images){
                    System.out.print(i + ". " + image.fileName);
                    if (image.equals(ImageEditor.getCurrentImage())){
                        System.out.print("\t\t<-Current Image");
                        currentNumber = i-1;
                    }
                    System.out.print("\n");
                    i++;
                }

                System.out.print("Please choose the number corresponding to the image you'd like to switch to: ");
                choice = scanner.nextInt() - 1;

                if (currentNumber == choice){
                    System.out.print("\nYou selected the current image. Was that choice correct[Y][N]?");
                    String answer = scanner.next();
                    if (answer.equalsIgnoreCase("Y")){
                        break;
                    }
                }
                else if (choice <= -1 || choice >= images.size()){
                    System.out.println("\nInvalid choice. Please select a number from the list!");
                }
                else {
                    System.out.print("You selected image " + String.valueOf(choice+1) + ". Was this choice correct[Y][N]?");
                    String answer = scanner.next();
                    if (answer.equalsIgnoreCase("Y")){
                        break;
                    }
                }
            }while (true);

            ImageEditor.setCurrentImage(ImageEditor.getCurrentSession().images.get(choice));

        }
        else{
            System.out.println("No Images in Session #" + sessionID);
        }
    }


    public ArrayList<Image> getImages() {
        return images;
    }
}
