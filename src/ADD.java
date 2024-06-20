public class ADD implements CommandObject{
    @Override
    public void handle(){
        if (ImageEditor.getInputArray().length <= 1) {
            throw new InvalidCommandFormat("Invalid command format! Try add <directory>");
        }
        Session.add();
    }

    @Override
    public String help(){
        return "ADD <directory> - Adds the image from the given directory in the current session or a new session.";
    }
}
