import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Session {
    private int sessionID;
    private ArrayList<Image> images;
    private ArrayList<String> sessionData;
    private File file;
    /*Purpose of this Stack is to save all the changes in the current session and
    have the ability to undo*/
    private Stack<Image[]> sessionChanges; //TODO: maybe temp, maybe make your own stack or use list
    // TODO: In case this stays make it so it saves the last version of the session class before the change in every single method that changes something.

    public Session() {
        images = new ArrayList<>();
        sessionData = new ArrayList<>();
        //TODO: finish the constructor
        //TODO: Add ID generator
        for (int i=0; true; i++){
            this.file = new File("Sessions/Session_" + i + ".data");
            if (!this.file.exists()){
                sessionID = i;
                break;
            }
        }
    }

    public Session(int sessionID) throws InvalidPathException {
        images = new ArrayList<>();
        sessionData = new ArrayList<>();
        this.file = new File ("Sessions/Session_" + sessionID + ".data");
        if (!this.file.exists()){
            throw new InvalidPathException("Session does not exist!");
        }
        this.sessionID = sessionID;
        readSessionData();
    }

    public int getSessionID() {
        return sessionID;
    }

    public static void add(){
        //TODO: ADD a check if a image with the same name exists, and change its name to name_1.extension.
        //TODO: Fix a bug where object of a non-existent image is created and saved in the session.
        //TODO: Make it so images are automatically saved in the Session's folder when added.
        ImageType imageType;
        try {
            if (ImageEditor.getCurrentSession() == null){
                ImageEditor.setCurrentSession(new Session());
            }

            // Set the image directory in userCommandParameters
            ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
            imageType = ImageType.valueOf(Image.checkImageType(ImageEditor.getUserCommandParameters()[0]));
            ImageEditor.setCurrentImage(imageType.handle(ImageEditor.getUserCommandParameters()[0]));
            ImageEditor.getCurrentSession().getImages().add(ImageEditor.getCurrentImage());
            ImageEditor.getCurrentSession().writeSessionData(ImageEditor.getCurrentImage());
        }
        catch (InvalidPathException e){
            System.out.println(e.getMessage());
        }

        System.out.println("ADDED!");
        //TODO: Check if the image exists first, then finish the readImageData methods in the PBM/PGM/PPM classes

    }

    public void undo(){

    }

    public void redo(){

    }

    public void sessionInfo(){
        System.out.println("\nInformation for Session #" + sessionID + ":");
        System.out.println("There are total of " + images.size() + " images");
        if (!images.isEmpty()){
            int i = 1;
            for (Image image : images){
                System.out.println(i + ". " + image.fileName);
                i++;
            }
            System.out.println();
        }
    }

    public void switchImage(){
        //TODO: If the session has no images, or has only one image return appropriate message,
        // first show a list of all the images in the session. Then ask
        // which image would you like to switch to. Then switch to the image.
        if (images != null){
            int currentNumber = 0;
            int i;
            int choice;
            System.out.println("Select and image from the list:");


            Scanner scanner = new Scanner(System.in);
            do {
                i = 1;
                for (Image image : images){
                    System.out.print(i + ". " + image.fileName);
                    if (image.equals(ImageEditor.getCurrentImage())){
                        System.out.print("\t\t<-Current Image");
                        currentNumber = i-1;
                    }
                    System.out.print("\n");
                    i++;
                }

                System.out.print("Please choose the number corresponding to the image you'd like to switch to: ");
                choice = scanner.nextInt() - 1;

                if (currentNumber == choice){
                    System.out.print("\nYou selected the current image. Was that choice correct[Y][N]?");
                    String answer = scanner.next();
                    if (answer.equalsIgnoreCase("Y")){
                        break;
                    }
                }
                else if (choice <= -1 || choice >= images.size()){
                    System.out.println("\nInvalid choice. Please select a number from the list!");
                }
                else {
                    System.out.print("You selected image " + String.valueOf(choice+1) + ". Was this choice correct[Y][N]?");
                    String answer = scanner.next();
                    if (answer.equalsIgnoreCase("Y")){
                        break;
                    }
                }
            }while (true);

            ImageEditor.setCurrentImage(ImageEditor.getCurrentSession().images.get(choice));

        }
        else{
            System.out.println("No Images in Session #" + sessionID);
        }
    }


    public ArrayList<Image> getImages() {
        return images;
    }

    private void readSessionData(){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            this.sessionData = new ArrayList<>();
            for (String line = reader.readLine(); line != null; line = reader.readLine()){
                this.sessionData.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String magicNumber = null, comment = null, dimensions = null, RGBValue = null, RGBData = null, directory = null;
        int dataCounter = 0; // Counts the number of read rows for each image seperatly.
        // 0 - magicNumber, 1 - comment, 2 - dimensions, 3 - RGBValue, 4 - RGBData, 5 - Directory, 6 - Create Image

        for (String line : sessionData){
            if (magicNumber == null || RGBData == null || dimensions == null || RGBValue == null || directory == null){
                //insert the read data in the corresponding String
                if (!line.contains("#") && dataCounter == 1){
                    dataCounter++;
                }
                if (magicNumber == "P1" && dataCounter == 3){
                    dataCounter++;
                }

                switch (dataCounter){
                    case 0:
                        magicNumber = line;
                        break;
                    case 1:
                        comment = line;
                        break;
                    case 2:
                        dimensions = line;
                        break;
                    case 3:
                        RGBValue = line;
                        break;
                    case 4:
                        RGBData = line;
                        break;
                    case 5:
                        directory = line;
                        break;
                }

                dataCounter++;
            }
            else{
                //create image file
                switch (magicNumber){
                    case "P1":
                        this.images.add(new PBM(magicNumber, comment, dimensions, RGBValue, RGBData, directory));
                        break;
                    case "P5":
                        this.images.add(new PGM(magicNumber, comment, dimensions, RGBValue, RGBData, directory));
                        break;
                    case "P6":
                    case "P3":
                        this.images.add(new PPM(magicNumber, comment, dimensions, RGBValue, RGBData, directory));
                        break;
                }
                dataCounter = 0;
                magicNumber = null;
                comment = null;
                dimensions = null;
                RGBValue = null;
                RGBData = null;
                directory = null;
            }
        }

        /*
        * Example of Session File Structure
        * magic number <--- depending on the magic number it will read the next lines differently
        * comment?
        * dimension
        * rgbvalue?
        * imageData <---- write everything on the same row so its more compact
        * directory
        * empty space
        * magic number <--- depending on the magic number it will read the next lines differently
        * comment?
        * dimension
        * rgbvalue?
        * imageData <---- write everything on the same row so its more compact
        * directory
        * empty space
        * */
    }

    protected void writeSessionData(Image image){
        StringBuilder temp = new StringBuilder();
        if (temp != null){
            for (int i = 0; i< image.imageData.size(); i++){
                if (i < image.imageDataStart){
                    sessionData.add(image.imageData.get(i) + "\n");
                }
                else{
                    temp.append(image.imageData.get(i)).append(" ");
                }
            }
            sessionData.add(String.valueOf(temp) + "\n");
            sessionData.add(image.directory + "\n");
            sessionData.add("\n");
        }
        else {
            sessionData = null;
            for (Image image1 : images){
                for (int i = 0; i< image1.imageData.size(); i++){
                    if (i < image1.imageDataStart){
                        sessionData.add(image1.imageData.get(i) + "\n");
                    }
                    else{
                        temp.append(image1.imageData.get(i)).append(" ");
                    }
                }
                sessionData.add(String.valueOf(temp) + "\n");
                sessionData.add(image.directory + "\n");
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, false))){
            for (String data : sessionData){
                writer.write(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
