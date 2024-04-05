import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class PPM extends Image{

    public PPM(String directory) {
        super(directory, "P6", "255");
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
        return ImageType.PPM;
    }

    @Override
    public void readImageData(){
        imageData = Image.imageReader(directory);
        for (int i = 0; i < imageData.size(); i++){
            if (imageData.get(i).contains("#")){
                imageDataStart = i +1;
            }
        }
        if (imageDataStart == 0){
            //Start reading Image at imageDataStart+3
        }
        else {
        //Start reading Image at imageDataStart+2
        }
    }
}
