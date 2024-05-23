import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PGM extends Image {

    public PGM(String directory) {
        super(directory, "P5", 255);
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

    public PGM(String magicNumber, String comment, String dimensions, String RGBValue, String RGBData, String directory){
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
    public ImageType getImageType(){
        return ImageType.PGM;
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
        imageDataStart = 3;
        for (int i = 0; i < imageData.size(); i++){
            if (imageData.get(i).contains("#")){
                imageDataStart = i+3;
            }
        }
        StringBuilder temp = new StringBuilder();
        for (int i = imageDataStart; i < imageData.size(); i++){
            temp.append(imageData.get(i));
        }
        String[] tempString = String.valueOf(temp).split("\\s+");

        for (int i = 0; i < tempString.length; i+=3){
            imageRGBData.add(tempString[i]);
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
        imageRGBData.replaceAll(s -> String.valueOf(255 - Integer.parseInt(s)));
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
    public void grayscale(){
        System.out.println("Image is already in grayscale");
    }

    @Override
    public void monochrome(){
        Session.undoRedoChange();
        for (int i = 0; i < imageRGBData.size(); i++){
            if (Integer.parseInt(imageRGBData.get(i)) < 128){
                imageRGBData.set(i, "0");
            }
            else{
                imageRGBData.set(i, String.valueOf(RGBValue));
            }
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
}
