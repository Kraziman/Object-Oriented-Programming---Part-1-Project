public class NEGATIVE implements CommandObject{
    @Override
    public void handle(){
        if (ImageEditor.getCurrentImage() != null){
            ImageEditor.getCurrentImage().negative();
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
    public String help(){
        return "negative - adds negative filter to the current image.";
    }
}
