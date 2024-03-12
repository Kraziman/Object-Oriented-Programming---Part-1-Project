import java.util.ArrayList;
import java.util.Stack;

public class Session {
    private int sessionID;
    private static ArrayList<Image> images;
    /*Purpose of this Stack is to save all the changes in the current session and
    have the ability to undo*/
    private Stack<Image[]> sessionChanges; //TODO: maybe temp, maybe make your own stack or use list

    public Session() {
        images = new ArrayList<>();
        //TODO: finish the constructor
        //TODO: Add ID generator
    }


    //TODO: Fix the add method acording to the changes of the way its called :)
    public static void add(){
        String imageType;
        try {
            if (ImageEditor.getCurrentSession() == null){
                ImageEditor.setCurrentSession(new Session());
            }

            ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
            ImageTypeHandler.setDirectory(ImageEditor.getUserCommandParameters()[0]);
            imageType = Image.checkImageType(ImageEditor.getUserCommandParameters()[0]);
            images.add(ImageTypeHandler.getHandle().get(ImageType.valueOf(imageType))); //TODO: Not sure if this works, needs testing

            System.out.println(imageType); //TODO: Remove this after its tested
            ImageTypeHandler.setDirectory(null);
        }
        catch (InvalidPathException e){
            System.out.println(e.getMessage());
        }

        //TODO: Add a way to write the data of the TIP: This will be done in image's respective extension class
        // image in its object and make it the current image that's being edited in the editor

    }

    public void undo(){

    }

    public void redo(){

    }

    public void sessionInfo(){

    }
}
