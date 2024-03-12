import java.util.HashMap;
import java.util.Map;

public enum Command {

    LOAD{

    },
    SAVE{

    },
    GRAYSCALE{

    },
    MONOCHROME{

    },
    NEGATIVE{

    },
    UNDO{

    },
    REDO{

    },
    ADD{
        @Override
        public void handle(){
            Session.add();
        }

    },
    SESSIONINFO{

    },
    SWITCH{

    },
    COLLAGE{

    },
    HELP{

    },
    EXIT{

    },
    ROTATE{

    };

    public abstract void handle();
}