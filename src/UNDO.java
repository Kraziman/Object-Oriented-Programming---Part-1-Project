public class UNDO implements CommandObject{
    @Override
    public void handle(){
        Session.undo();
    }

    @Override
    public String help(){
        return "undo - undoes the last changes in the current sessions";
    }
}
