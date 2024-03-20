import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PBM extends Image{
    private boolean[] imageData;

    public PBM(String directory) {
        super(directory, "P1", "1");
        //TODO: Use Try catch block to check if image exists.(If IO.File reads
        // null the file does not exist and it should return exception, maybe a custom exception)
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
        return ImageType.PBM;
    }

    @Override
    public void readImageData(){
        //TODO: add functionality
    }

}
