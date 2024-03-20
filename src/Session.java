import java.util.ArrayList;
import java.util.Stack;

public class Session {
    private int sessionID;
    private static ArrayList<Image> images;
    /*Purpose of this Stack is to save all the changes in the current session and
    have the ability to undo*/
    private Stack<Image[]> sessionChanges; //TODO: maybe temp, maybe make your own stack or use list
    // TODO: In case this stays make it so it saves the last version of the session class before the change in every single method that changes something.

    public Session() {
        images = new ArrayList<>();
        //TODO: finish the constructor
        //TODO: Add ID generator
    }


    public static void add(){
        ImageType imageType;
        try {
            if (ImageEditor.getCurrentSession() == null){
                ImageEditor.setCurrentSession(new Session());
            }

            // Set the image directory in userCommandParameters
            ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
            imageType = ImageType.valueOf(Image.checkImageType(ImageEditor.getUserCommandParameters()[0]));
            images.add(imageType.handle(ImageEditor.getUserCommandParameters()[0])); //TODO: Not sure if this works, needs testing

        }
        catch (InvalidPathException e){
            System.out.println(e.getMessage());
        }

        //TODO: Check if the image exists first, then finish the readImageData methods in the PBM/PGM/PPM classes

    }

    public void undo(){

    }

    public void redo(){

    }

    public void sessionInfo(){

    }

}
