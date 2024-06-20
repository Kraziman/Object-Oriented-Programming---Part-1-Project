public class COLLAGE implements CommandObject{
    @Override
    public void handle(){
        if (ImageEditor.getCurrentSession() == null){
            System.out.println("You need to create/open a session first!");
        }
        else {
            ImageEditor.getCurrentSession().collage();
        }

    }

    @Override
    public String help(){
        return "collage - creates collage from all the images in the current session";
    }
}
