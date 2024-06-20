public class EXIT implements CommandObject{
    @Override
    public void handle(){
        ImageEditor.exit();
    }

    @Override
    public String help(){
        return "exit - exits the program";
    }
}
