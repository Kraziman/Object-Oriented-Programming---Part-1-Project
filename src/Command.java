public enum Command {

    SAVEAS{
        @Override
        public void handle(){
            if (ImageEditor.getInputArray().length <= 1) {
                throw new InvalidCommandFormat("Invalid command format! Try save <directory>");
            }
            else if (ImageEditor.getCurrentImage() != null){
                ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
                ImageEditor.saveas(ImageEditor.getCurrentImage(), ImageEditor.getUserCommandParameters()[0]);
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
            return "saveas <directory> - saves the current image in the given directory.";
        }

    },
    SAVE{
        @Override
        public void handle(){
            if (ImageEditor.getCurrentImage() != null){
                ImageEditor.save(ImageEditor.getCurrentImage());
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
            return "save - saves the current image in its original directory";
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
            return "grayscale - adds grayscale filter to the current image.";
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
            return "monochrome - adds monochrome filter to the current image.";
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
            return "negative - adds negative filter to the current image.";
        }

    },
    UNDO{
        @Override
        public void handle(){
            Session.undo();
        }

        @Override
        public String help(){
            return "undo - undoes the last changes in the current sessions";
        }

    },
    REDO{
        @Override
        public void handle(){
            Session.redo();
        }

        @Override
        public String help(){
            return "redo - redoes the last changes undoed changes in the current session.";
        }

    },
    ADD{
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
            return "sessioninfo - displays information about the current session";
        }

    },
    NEWSESSION{
        @Override
        public void handle() {
            ImageEditor.setCurrentSession(new Session());
        }

        @Override
        public String help() {
            return "newsession - create a new empty session.";
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
            return "switchsession <Session ID> - switches to another existing session";
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
            return "switchimage - switches to another image in the current session.";
        }

    },
    COLLAGE{
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

    },
    HELP{
        @Override
        public void handle(){
            ImageEditor.help();
        }

        @Override
        public String help(){
            return "help or help <command> - Displays information for all commands or gives additional information to how a command works";
        }

    },
    EXIT{
        @Override
        public void handle(){
            ImageEditor.exit();
        }

        @Override
        public String help(){
            return "exit - exits the program";
        }

    },
    ROTATE{
        @Override
        public void handle(){
            if (ImageEditor.getInputArray().length <= 1){
                throw new InvalidCommandFormat("Invalid command format! Try rotate <direction>");
            }
            else if (ImageEditor.getCurrentImage() != null){
                ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
                ImageEditor.getCurrentImage().rotate(Direction.valueOf(ImageEditor.getUserCommandParameters()[0].toUpperCase()));
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
            return "direction <LEFT or RIGHT> - rotates the current image 90 degrees left or right";
        }

    };





    public abstract void handle();
    public abstract String help();
}