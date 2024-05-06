import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Image {
    protected String directory;
    protected String fileName;
    protected ArrayList<String> imageData;
    protected int imageDataStart;
    protected ArrayList<String> imageRGBData;
    private String magicNumber;
    protected int RGBValue;
    protected File file;
    protected int imageWidth;
    protected int imageHeight;

    public Image(String directory, String magicNumber, int RGBValue){
        this.directory = directory;
        this.fileName = checkImageName(directory);
    }

    public Image (String magicNumber, String comment, String dimensions, String RGBValue, String RGBData, String directory){
        this.magicNumber = magicNumber;
        this.RGBValue = Integer.parseInt(RGBValue);
        this.directory = directory;
        this.fileName = checkImageName(directory);
    }

    public ImageType getImageType(){
        return null;
    }

    public void readImageData(){

    }

    //TODO: Add functionality to change the type of the image(either add it as a general
    // function of the program or just so it makes collages with different image types easier to make

    public void rotate(Direction d){
    }

    public void negative(){

    }

    public void grayscale(){

    }

    public void monochrome(){

    }

    public static String checkImageType(String imagePath) throws InvalidPathException {


        String imageName = checkImageName(imagePath);
        int dotIndex = imageName.lastIndexOf(".");


        if (dotIndex != -1 && dotIndex < imagePath.length() ){
            return imageName.substring(dotIndex + 1).toUpperCase();
        }
        else{
            throw new InvalidPathException("Invalid path: \"" + imagePath + "\", or file does not exist.");
        }

    }

    protected static String checkImageName(String imagePath){

        int SlashIndex1 = imagePath.lastIndexOf("/");
        int SlashIndex2 = imagePath.lastIndexOf("\\");
        int lastSlashIndex = Math.max(SlashIndex1,SlashIndex2);
        String imageName;

        if (lastSlashIndex != -1){
            imageName = imagePath.substring(lastSlashIndex+1);
        }
        else{
            imageName = imagePath;
        }

        return imageName;
    }

    public static ArrayList<String> imageReader(String directory){
        ArrayList<String> imageData = new ArrayList<>();
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
