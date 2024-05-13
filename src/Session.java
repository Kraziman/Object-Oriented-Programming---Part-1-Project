import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Session {
    private final int sessionID;
    private ArrayList<Image> images;
    private ArrayList<String> sessionData;
    private File file;
    private static ArrayList<sessionHistory> undo; //TODO: Save the last instance of the session before a change and makes the redo arraylist null
    private static ArrayList<sessionHistory> redo; //TODO: removes the last saved instance in undo, and redoes the last changes
    public Session() {
        images = new ArrayList<>();
        sessionData = new ArrayList<>();
        undo = new ArrayList<>();
        redo = new ArrayList<>();
        for (int i=0; true; i++){
            this.file = new File("Sessions/Session_" + i + ".data");
            if (!this.file.exists()){
                sessionID = i;
                break;
            }
        }
        ImageEditor.setCurrentImageIndex(0);

    }

    public Session(int sessionID) throws InvalidPathException {
        images = new ArrayList<>();
        sessionData = new ArrayList<>();
        undo = new ArrayList<>();
        redo = new ArrayList<>();
        this.file = new File ("Sessions/Session_" + sessionID + ".data");
        if (!this.file.exists()){
            throw new InvalidPathException("Session does not exist!");
        }
        this.sessionID = sessionID;
        readSessionData();
        ImageEditor.setCurrentImageIndex(0);
    }

    public int getSessionID() {
        return sessionID;
    }

    public static void add(){
        Session.undoRedoChange();
        ImageType imageType;
        try {
            if (ImageEditor.getCurrentSession() == null){
                ImageEditor.setCurrentSession(new Session());
            }

            ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
            imageType = ImageType.valueOf(Image.checkImageType(ImageEditor.getUserCommandParameters()[0]));
            ImageEditor.setCurrentImage(imageType.handle(ImageEditor.getUserCommandParameters()[0]));
            ImageEditor.getCurrentSession().getImages().add(ImageEditor.getCurrentImage());
            ImageEditor.setCurrentImageIndex(ImageEditor.getCurrentSession().getImages().size()-1);
        }
        catch (InvalidPathException e){
            System.out.println(e.getMessage());
        }

        System.out.println("ADDED!");
        if (ImageEditor.getCurrentSession() != null) {
            ImageEditor.getCurrentSession().writeSessionData();
        }

    }

    //TODO: FIX undo,redo and undoRedoChange, cause they don't fucking work SMH MY FUCKING HEAD
    public static void undo(){
        if (undo == null || undo.isEmpty()){
            System.out.println("Nothing to undo!");
        }
        else {
            redo.add(undo.getLast());
            ImageEditor.setCurrentSession(undo.getLast().getSession());
            ImageEditor.setCurrentImageIndex(undo.getLast().getCurrentImageIndex());
            ImageEditor.setCurrentImage(ImageEditor.getCurrentSession().getImages().get(ImageEditor.getCurrentImageIndex()));
            undo.removeLast();
            if (ImageEditor.getCurrentSession() != null) {
                ImageEditor.getCurrentSession().writeSessionData();
            }
        }

    }

    public static void redo(){
        if (redo == null || redo.isEmpty()){
            System.out.println("Nothing to redo!");
        }
        else {
            undo.add(redo.getLast());
            ImageEditor.setCurrentSession(redo.getLast().getSession());
            ImageEditor.setCurrentImageIndex(redo.getLast().getCurrentImageIndex());
            ImageEditor.setCurrentImage(ImageEditor.getCurrentSession().getImages().get(ImageEditor.getCurrentImageIndex()));
            redo.removeLast();
            if (ImageEditor.getCurrentSession() != null) {
                ImageEditor.getCurrentSession().writeSessionData();
            }
        }
    }

    public static void undoRedoChange(){
        undo.add(new sessionHistory(ImageEditor.getCurrentSession(), ImageEditor.getCurrentImageIndex()));
        redo = new ArrayList<>();

    }

    public void sessionInfo(){
        System.out.println("\nInformation for Session #" + sessionID + ":");
        System.out.println("There are total of " + images.size() + " images");
        if (!images.isEmpty()){
            int i = 1;
            for (Image image : images) {
                System.out.print(i + ". " + image.fileName);
                if (image.equals(ImageEditor.getCurrentImage())) {
                    System.out.print("\t\t<-Current Image");
                }
                System.out.print("\n");
                i++;
            }
        }
    }

    public void switchImage(){
        if (images != null){
            if (images.size() <= 1){
                System.out.println("Cannot switch to another image when there is only one image in the session!");
            }
            else {
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
                        System.out.print("You selected image " + (choice + 1) + ". Was this choice correct[Y][N]?");
                        String answer = scanner.next();
                        if (answer.equalsIgnoreCase("Y")){
                            Session.undoRedoChange();
                            ImageEditor.setCurrentImageIndex(choice-1);
                            break;
                        }
                    }
                }while (true);

                ImageEditor.setCurrentImage(ImageEditor.getCurrentSession().images.get(choice));
            }

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
        int dataCounter = 0; // Counts the number of read rows for each image separately.
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
        * rgb value?
        * imageData <---- write everything on the same row so its more compact
        * directory
        * empty space
        * magic number <--- depending on the magic number it will read the next lines differently
        * comment?
        * dimension
        * rgb value?
        * imageData <---- write everything on the same row so its more compact
        * directory
        * empty space
        * */
    }

    protected void writeSessionData(){
        StringBuilder temp = new StringBuilder();
            sessionData = new ArrayList<>();
            for (Image image : images){
                for (int i = 0; i< image.imageData.size(); i++){
                    if (i < image.imageDataStart){
                        sessionData.add(image.imageData.get(i) + "\n");
                    }
                    else{
                        temp.append(image.imageData.get(i)).append(" ");
                    }
                }
                sessionData.add(temp + "\n");
                sessionData.add(image.directory + "\n");
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
