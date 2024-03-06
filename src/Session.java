import java.util.ArrayList;

public class Session {
    private int sessionID;
    private ArrayList<Image> images;

    public Session() {
        //TODO: finish the constructor
    }

    public void add(Image image){
        String imageType;
        try {
            ImageTypeHandler.setDirectory(ImageEditor.getUserCommandParameters()[1]);
            ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
            imageType = Image.checkImageType(ImageEditor.getUserCommandParameters()[0]);
            images.add(ImageTypeHandler.getHandle().get(ImageType.valueOf(imageType))); //TODO: Not sure if this works, needs testing
            System.out.println(imageType);
        }
        catch (InvalidPathException e){
            System.out.println(e.getMessage());
        }

        //TODO: Add a way to write the data of the
        // image in its object and make it the current image that's being edited in the editor

    }

    public void undo(){

    }

    public void redo(){

    }

    public void sessionInfo(){

    }
}
