import java.io.*;
import java.util.ArrayList;

public class PPM extends Image{

    public PPM(String directory) {
        super(directory, "P6", "255");
        imageRGBData = new ArrayList<String>();
        imageData = new ArrayList<String>();
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
                imageDataStart = i;
            }
        }
        for (int i = imageDataStart+3; i < imageData.size(); i++){
            imageRGBData.add(imageData.get(i));
        }
    }

    @Override
    public void negative(){
        for (int i = 0; i < imageRGBData.size(); i++){
            String[] temp = new String[3];
            temp = imageRGBData.get(i).split(" ");
            for (int j = 0; j<temp.length; j++){
                temp[j] = String.valueOf(255 - Integer.parseInt(temp[j]));
            }
            imageRGBData.set(i, temp[0] + " " + temp[1] + " " + temp[2] + " ");
        }
        for (int i = imageDataStart+3; i< imageData.size(); i++){
            imageData.set(i, imageRGBData.get(i-imageDataStart-3));
        }
        /*try (BufferedWriter writer = new BufferedWriter(new FileWriter("images/new_ppm.ppm"))){
            //TODO: make the name of the new ppm file be "nameOfFile_NEW.ppm" and implement this in the save function
            for (String tempData : imageData){
                writer.write(tempData);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("\tDONE!\n");*/
    }
}
