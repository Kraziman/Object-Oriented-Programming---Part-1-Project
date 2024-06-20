public class REDO implements CommandObject{
    @Override
    public void handle(){
        Session.redo();
    }

    @Override
    public String help(){
        return "redo - redoes the last changes undoed changes in the current session.";
    }
}
