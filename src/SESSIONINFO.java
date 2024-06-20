public class SESSIONINFO implements CommandObject{
    @Override
    public void handle(){
        if (ImageEditor.getCurrentSession() != null){
            ImageEditor.getCurrentSession().sessionInfo();
        }
        else{
            System.out.println("You need to make/open a session before using this command!");
        }
    }

    @Override
    public String help(){
        return "sessioninfo - displays information about the current session";
    }
}
