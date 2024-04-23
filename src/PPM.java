import java.io.*;
import java.util.ArrayList;

public class PPM extends Image{

    public PPM(String directory) {
        super(directory, "P6", 255);
        this.fileName = checkImageName(directory);
        imageRGBData = new ArrayList<>();
        imageData = new ArrayList<>();
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
        imageData.set(imageDataStart-2, imageHeight + " " + imageWidth);
        int temp = imageHeight;
        imageHeight = imageWidth;
        imageWidth = temp;

        for (int i = imageDataStart; i < imageData.size(); i++){
            imageData.set(i, imageRGBData.get(i-imageDataStart));
        }
    }

    @Override
    public void readImageData(){
        imageData = Image.imageReader(directory);
        imageDataStart = 3;
        for (int i = 0; i < imageData.size(); i++){
            if (imageData.get(i).contains("#")){
                imageDataStart = i+3;
            }
        }
        StringBuilder temp = new StringBuilder();
        for (int i = imageDataStart; i < imageData.size(); i++){
            temp.append(imageData.get(i)).append(" ");
        }
        String[] tempString = String.valueOf(temp).split("\\s+");
        for (int i = 0; i < tempString.length; i+=3){
            imageRGBData.add(tempString[i] + " " + tempString[i+1] + " " + tempString[i+2] + " ");
        }
        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 0; i < imageDataStart; i++){
            tempArray.add(imageData.get(i));
        }
        imageData = new ArrayList<>();
        imageData.addAll(tempArray);
        imageData.addAll(imageRGBData);

        tempString = imageData.get(imageDataStart-2).split("\\s+");
        imageWidth = Integer.parseInt(tempString[0]);
        imageHeight = Integer.parseInt(tempString[1]);
    }

    @Override
    public void negative(){
        for (int i = 0; i < imageRGBData.size(); i++){
            String[] temp = imageRGBData.get(i).split(" ");
            StringBuilder tempString = new StringBuilder();
            for (int j = 0; j<temp.length; j++){
                tempString.append(255 - Integer.parseInt(temp[j])).append(" ");
            }
            imageRGBData.set(i, String.valueOf(tempString));
        }
        for (int i = 0; i< imageRGBData.size(); i++){
            imageData.set(imageDataStart + i, imageRGBData.get(i));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Sessions/Session_" + ImageEditor.getCurrentSession().getSessionID() + "/Images/" + fileName))){
            //TODO: make the name of the new ppm file be "nameOfFile_NEW.ppm" and implement this in the save function
            for (String tempData : imageData){
                writer.write(tempData);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("\tDONE!\n");
    }

    @Override
    public void grayscale(){
        for (int i = 0; i < imageRGBData.size(); i++){
            String[] temp = imageRGBData.get(i).split(" ");
                StringBuilder tempString = new StringBuilder();
                for (int j =0; j < temp.length; j+=3){
                    temp[j] = String.valueOf((int)(Float.parseFloat(temp[j])*0.2990 + Float.parseFloat(temp[j+1])*0.5870 + Float.parseFloat(temp[j+2])*0.1140));;
                    tempString.append(temp[j]).append(" ").append(temp[j]).append(" ").append(temp[j]).append(" ");
                }
                imageRGBData.set(i, String.valueOf(tempString));
        }
        for (int i = imageDataStart; i< imageData.size(); i++){
            imageData.set(i, imageRGBData.get(i-imageDataStart));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("images/new_grayscale_ppm.ppm"))){
            //TODO: make the name of the new ppm file be "nameOfFile_NEW.ppm" and implement this in the save function
            for (String tempData : imageData){
                writer.write(tempData);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("\tDONE!\n");
    }

    @Override
    public void monochrome(){ //TODO: FIX CAUSE DOESNT WORK
        //Convert to grayscale
        for (int i = 0; i < imageRGBData.size(); i++){
            String[] temp = imageRGBData.get(i).split("\\s+");
            StringBuilder tempString = new StringBuilder();
            for (int j =0; j < temp.length; j+=3){
                temp[j] = String.valueOf((int)(Float.parseFloat(temp[j])*0.2990 + Float.parseFloat(temp[j+1])*0.5870 + Float.parseFloat(temp[j+2])*0.1140));;
                tempString.append(temp[j]).append(" ").append(temp[j]).append(" ").append(temp[j]).append(" ");
            }
            imageRGBData.set(i, String.valueOf(tempString));
        }
        for (int i = imageDataStart; i< imageData.size(); i++){
            imageData.set(i, imageRGBData.get(i-imageDataStart));
        }
        //Convert to monochrome
        for (int i = 0; i < imageRGBData.size(); i++){
            String[] temp = imageRGBData.get(i).split("\\s+");
                StringBuilder tempString = new StringBuilder();
                for (int j =0; j < temp.length; j+=3){
                    if (Integer.parseInt(temp[j]) < 128.00){ //TODO: Fix wrong number format
                        temp[j] = "0";
                    }
                    else {
                        temp[j] = "255";
                    }
                    tempString.append(temp[j]).append(" ").append(temp[j]).append(" ").append(temp[j]).append(" ");
                }
                imageRGBData.set(i, String.valueOf(tempString));
        }
        for (int i = imageDataStart+3; i< imageData.size(); i++){
            imageData.set(i, imageRGBData.get(i-imageDataStart-3));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("images/new_monochrome_ppm.ppm"))){
            //TODO: make the name of the new ppm file be "nameOfFile_NEW.ppm" and implement this in the save function
            for (String tempData : imageData){
                writer.write(tempData);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("\tDONE!\n");
    }
}
