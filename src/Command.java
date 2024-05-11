import java.io.IOException;

public enum Command {

    SAVEAS{
        @Override
        public void handle(){

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    SAVE{
        @Override
        public void handle(){

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    GRAYSCALE{
        @Override
        public void handle(){
            if (ImageEditor.getCurrentImage() != null){
                ImageEditor.getCurrentImage().grayscale();
            }
            else {
                if (ImageEditor.getCurrentSession() == null){
                    System.out.println("You need to create/open a session first!");
                }
                else if (ImageEditor.getCurrentSession().getImages().isEmpty()){
                    System.out.println("There are no images in the current session!");
                }
                else if (ImageEditor.getCurrentImage() == null){
                    System.out.println("You need to select an image before trying that!");
                }
                else {
                    System.out.println("Unexpected error!");
                }
            }
        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    MONOCHROME{
        @Override
        public void handle(){
            if (ImageEditor.getCurrentImage() != null){
                ImageEditor.getCurrentImage().monochrome();
            }
            else {
                if (ImageEditor.getCurrentSession() == null){
                    System.out.println("You need to create/open a session first!");
                }
                else if (ImageEditor.getCurrentSession().getImages().isEmpty()){
                    System.out.println("There are no images in the current session!");
                }
                else if (ImageEditor.getCurrentImage() == null){
                    System.out.println("You need to select an image before trying that!");
                }
                else {
                    System.out.println("Unexpected error!");
                }
            }
        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    NEGATIVE{
        @Override
        public void handle(){
            if (ImageEditor.getCurrentImage() != null){
                ImageEditor.getCurrentImage().negative();
            }
            else {
                if (ImageEditor.getCurrentSession() == null){
                    System.out.println("You need to create/open a session first!");
                }
                else if (ImageEditor.getCurrentSession().getImages().isEmpty()){
                    System.out.println("There are no images in the current session!");
                }
                else if (ImageEditor.getCurrentImage() == null){
                    System.out.println("You need to select an image before trying that!");
                }
                else {
                    System.out.println("Unexpected error!");
                }
            }
        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    UNDO{
        @Override
        public void handle(){

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    REDO{
        @Override
        public void handle(){

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    ADD{
        @Override
        public void handle(){
            Session.add();
        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    SESSIONINFO{
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
            return "Command Help Info";
        }

    },
    NEWSESSION{
        @Override
        public void handle() {
            ImageEditor.setCurrentSession(new Session());
        }

        @Override
        public String help() {
            return null;
        }
    },
    SWITCHSESSION{
        @Override
        public void handle(){
            if (ImageEditor.getInputArray().length <= 1){
                throw new InvalidCommandFormat("Invalid command format! Try switchsession <session id>");
            }
            else {
                ImageEditor.switchSession();
            }

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    SWITCHIMAGE{
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
            return "Command Help Info";
        }

    },
    COLLAGE{
        @Override
        public void handle(){

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    HELP{
        @Override
        public void handle(){
            ImageEditor.help();
        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    EXIT{
        @Override
        public void handle(){
            ImageEditor.exit();
        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    ROTATE{
        @Override
        public void handle(){
            if (ImageEditor.getInputArray().length <= 1){
                throw new InvalidCommandFormat("Invalid command format! Try rotate <direction>");
            }
            else {
                ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
                ImageEditor.getCurrentImage().rotate(Direction.valueOf(ImageEditor.getUserCommandParameters()[0].toUpperCase()));
            }
        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    };




    public abstract void handle();
    public abstract String help();
}