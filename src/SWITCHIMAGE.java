public class SWITCHIMAGE implements CommandObject{
    @Override
    public void handle(){
        if (ImageEditor.getCurrentSession() != null){
            ImageEditor.getCurrentSession().switchImage();
        }
        else {
            System.out.println("You need to create/select a session first!");
        }
    }

    @Override
    public String help(){
        return "switchimage - switches to another image in the current session.";
    }
}
