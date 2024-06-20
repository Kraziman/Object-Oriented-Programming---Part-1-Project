public class SAVEAS implements CommandObject{
    @Override
    public void handle() {
        if (ImageEditor.getInputArray().length <= 1) {
            throw new InvalidCommandFormat("Invalid command format! Try save <directory>");
        }
        if (ImageEditor.getCurrentImage() != null){
            ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
            ImageEditor.saveas(ImageEditor.getCurrentImage(), ImageEditor.getUserCommandParameters()[0]);
        }
        else {
            if (ImageEditor.getCurrentSession() == null){
                System.out.println("You need to create/open a session first!");
                return;
            }
            if (ImageEditor.getCurrentSession().getImages().isEmpty()){
                System.out.println("There are no images in the current session!");
                return;
            }
            if (ImageEditor.getCurrentImage() == null){
                System.out.println("You need to select an image before trying that!");
                return;
            }
            System.out.println("Unexpected error!");
        }
    }

    @Override
    public String help() {
        return "saveas <directory> - saves the current image in the given directory.";
    }
}
