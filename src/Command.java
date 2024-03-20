import java.util.HashMap;
import java.util.Map;

public enum Command {

    LOAD{
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

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    MONOCHROME{
        @Override
        public void handle(){

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    NEGATIVE{
        @Override
        public void handle(){

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

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    },
    SWITCH{
        @Override
        public void handle(){

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

        }

        @Override
        public String help(){
            return "Command Help Info";
        }

    };

    public abstract void handle();
    public abstract String help();
}