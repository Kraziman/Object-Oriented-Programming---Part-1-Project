import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Image {
    public String directory = null;
    public ArrayList<String> imageData;
    public int imageDataStart;
    public String magicNumber;
    public ArrayList<String> imageComments;
    public String RGBValue;
    public Image imageHistory;
    public File file;

    public Image(String directory, String magicNumber, String RGBValue){
        this.directory = directory;
    }

    public ImageType getImageType(){
        return null;
    }

    public void readImageData(){

    }

    //TODO: Add functionality to change the type of the image(either add it as a general
    // function of the program or just so it makes collages with different image types easier to make

    public void rotate(){
        //TODO: make it so it gets its direction directly from the ImageEditor's parameters
    }

    public void negative(){

    }

    public void grayscale(){

    }

    public void monochrome(){

    }

    public static String checkImageType(String imagePath) throws InvalidPathException {

        String imageExtension;

        int SlashIndex1 = imagePath.lastIndexOf("/");
        int SlashIndex2 = imagePath.lastIndexOf("\\");
        int lastSlashIndex = Math.max(SlashIndex1,SlashIndex2);
        int dotIndex;
        String temp;

        if (lastSlashIndex != -1){
            temp = imagePath.substring(lastSlashIndex);
            dotIndex = temp.lastIndexOf(".");
        }
        else{
            temp = imagePath;
            dotIndex = temp.lastIndexOf(".");
        }

        if (dotIndex != -1 && dotIndex < imagePath.length() ){
            return imageExtension = temp.substring(dotIndex + 1).toUpperCase();
        }
        else{
            throw new InvalidPathException("Invalid path: \"" + imagePath + "\", or file does not exist.");
        }

    }

    public static ArrayList<String> imageReader(String directory){
        ArrayList<String> imageData = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(directory))){
            String line;
            while ((line = reader.readLine()) != null){
                imageData.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageData;
    }

}
