import java.io.File;
import java.io.IOException;

public class PGM extends Image{

    public PGM(String directory) {
        super(directory, "P5", "255");
        try{
            this.file = new File(directory);
            if (!file.exists()){
                throw new IOException();
            }
            readImageData();
        } catch (IOException e) {
            System.out.println("File does not exist or invalid directory!");
        }
    }

    @Override
    public ImageType getImageType(){
        return ImageType.PGM;
    }

    @Override
    public void readImageData(){
        //TODO: add functionality
    }
}
