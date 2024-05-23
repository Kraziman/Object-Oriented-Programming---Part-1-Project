import java.io.*;
import java.util.ArrayList;

public class PPM extends Image {

    public PPM(String directory) {
        super(directory, "P6", 255);
        this.fileName = checkImageName(directory);
        this.imageData = null;

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

    public PPM(String magicNumber, String comment, String dimensions, String RGBValue, String RGBData, String directory){
        super(directory, "P6", 255);
        this.fileName = checkImageName(directory);
        this.imageData = new ArrayList<>();
        imageData.add(magicNumber);
        if (comment != null) imageData.add(comment);
        imageData.add(dimensions);
        if (RGBValue != null) imageData.add(RGBValue);
        imageData.add(RGBData);

        readImageData();
    }

    public PPM(String magicNumber, String dimensions, String RGBValue, ArrayList<String> RGBData, String directory){
        super(directory, "P6", 255);
        this.fileName = checkImageName(directory);
        this.imageData = new ArrayList<>();
        imageData.add(magicNumber);
        imageData.add(dimensions);
        if (RGBValue != null) imageData.add(RGBValue);
        imageData.addAll(RGBData);

        readImageData();
    }

    @Override
    public ImageType getImageType(){
        return ImageType.PPM;
    }

    @Override
    public void rotate(Direction d){
        Session.undoRedoChange();
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

        System.out.print("\tDONE!\n");

        ImageEditor.getCurrentSession().getImages().set(ImageEditor.getCurrentImageIndex(), ImageEditor.getCurrentImage());

        if (ImageEditor.getCurrentSession() != null) {
            ImageEditor.getCurrentSession().writeSessionData();
        }
    }

    @Override
    public void readImageData(){
        if (imageData == null) imageData = Image.imageReader(directory);
        imageRGBData = new ArrayList<>();
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
        Session.undoRedoChange();
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

        System.out.print("\tDONE!\n");

        ImageEditor.getCurrentSession().getImages().set(ImageEditor.getCurrentImageIndex(), ImageEditor.getCurrentImage());

        if (ImageEditor.getCurrentSession() != null) {
            ImageEditor.getCurrentSession().writeSessionData();
        }
    }

    @Override
    public void grayscale(){
        Session.undoRedoChange();
        for (int i = 0; i < imageRGBData.size(); i++){
            String[] temp = imageRGBData.get(i).split(" ");
                StringBuilder tempString = new StringBuilder();
                for (int j =0; j < temp.length; j+=3){
                    temp[j] = String.valueOf((int)(Float.parseFloat(temp[j])*0.2990 + Float.parseFloat(temp[j+1])*0.5870 + Float.parseFloat(temp[j+2])*0.1140));
                    tempString.append(temp[j]).append(" ").append(temp[j]).append(" ").append(temp[j]).append(" ");
                }
                imageRGBData.set(i, String.valueOf(tempString));
        }
        for (int i = imageDataStart; i< imageData.size(); i++){
            imageData.set(i, imageRGBData.get(i-imageDataStart));
        }

        System.out.print("\tDONE!\n");

        ImageEditor.getCurrentSession().getImages().set(ImageEditor.getCurrentImageIndex(), ImageEditor.getCurrentImage());

        if (ImageEditor.getCurrentSession() != null) {
            ImageEditor.getCurrentSession().writeSessionData();
        }
    }

    @Override
    public void monochrome(){
        Session.undoRedoChange();
        //Convert to grayscale
        for (int i = 0; i < imageRGBData.size(); i++){
            String[] temp = imageRGBData.get(i).split("\\s+");
            StringBuilder tempString = new StringBuilder();
            for (int j =0; j < temp.length; j+=3){
                temp[j] = String.valueOf((int)(Float.parseFloat(temp[j])*0.2990 + Float.parseFloat(temp[j+1])*0.5870 + Float.parseFloat(temp[j+2])*0.1140));
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
                    if (Integer.parseInt(temp[j]) < 128.00){
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

        System.out.print("\tDONE!\n");

        ImageEditor.getCurrentSession().getImages().set(ImageEditor.getCurrentImageIndex(), ImageEditor.getCurrentImage());

        if (ImageEditor.getCurrentSession() != null) {
            ImageEditor.getCurrentSession().writeSessionData();
        }
    }
}
