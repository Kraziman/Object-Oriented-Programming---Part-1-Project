import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PBM extends Image{

    public PBM(String directory) {
        super(directory, "P1", 1);
        this.fileName = checkImageName(directory);
        this.imageData = null;
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

        public PBM(String magicNumber, String comment, String dimensions, String RGBValue, String RGBData, String directory){
            super(directory, "P1", 1);
            this.fileName = checkImageName(directory);
            imageData.add(magicNumber);
            if (comment != null) imageData.add(comment);
            imageData.add(dimensions);
            if (RGBValue != null) imageData.add(RGBValue);
            imageData.add(RGBData);

            readImageData();
        }

    @Override
    public void rotate(Direction d){
        String[][] tempString = new String[imageHeight][imageWidth];
        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                tempString[i][j] = imageRGBData.get(i * imageWidth + j);
            }
        }
        switch (d) {
            case LEFT:
                imageRGBData = new ArrayList<>();
                for (int i = 0; i < imageWidth; i++) {
                    for (int j = 0; j < imageHeight; j++) {
                        imageRGBData.add(tempString[j][imageWidth - 1 - i]);
                    }
                }
                break;
            case RIGHT:
                imageRGBData = new ArrayList<>();
                for (int i = 0; i < imageWidth; i++) {
                    for (int j = imageHeight - 1; j >= 0; j--) {
                        imageRGBData.add(tempString[j][i]);
                    }
                }
                break;
        }
        imageData.set(imageDataStart-1, imageHeight + " " + imageWidth);
        int temp = imageHeight;
        imageHeight = imageWidth;
        imageWidth = temp;

        for (int i = imageDataStart; i < imageData.size(); i++){
            imageData.set(i, imageRGBData.get(i-imageDataStart));
        }
    }

    @Override
    public void readImageData(){
        if (imageData == null) imageData = Image.imageReader(directory);
        imageDataStart = 2;
        for (int i = 0; i < imageData.size(); i++){
            if (imageData.get(i).contains("#")){
                imageDataStart = i+2;
            }
        }
        StringBuilder temp = new StringBuilder();
        for (int i = imageDataStart; i < imageData.size(); i++){
            temp.append(imageData.get(i));
        }
        String[] tempString = String.valueOf(temp).split("\\s+");

        imageRGBData.addAll(Arrays.asList(tempString));

        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 0; i < imageDataStart; i++){
            tempArray.add(imageData.get(i));
        }
        imageData = new ArrayList<>();
        imageData.addAll(tempArray);
        imageData.addAll(imageRGBData);

        tempString = imageData.get(imageDataStart-1).split("\\s+");
        imageWidth = Integer.parseInt(tempString[0]);
        imageHeight = Integer.parseInt(tempString[1]);
    }

    @Override
    public void negative(){
        imageRGBData.replaceAll(s -> String.valueOf(1 - Integer.parseInt(s)));
        for (int i = imageDataStart; i< imageData.size(); i++){
            imageData.set(i, imageRGBData.get(i-imageDataStart));
        }
    }

    @Override
    public void grayscale(){
        System.out.println("Cannot do that with image type PBM!");
    }

    @Override
    public void monochrome(){
        System.out.println("Image is already monochrome!");
    }
}
