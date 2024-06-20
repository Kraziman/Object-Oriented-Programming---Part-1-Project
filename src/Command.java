public enum Command {

    SAVEAS{
        @Override
        public CommandObject handle(){
            return new SAVEAS();
        }

    },
    SAVE{
        @Override
        public CommandObject handle(){
            return new SAVE();
        }

    },
    GRAYSCALE{
        @Override
        public CommandObject handle(){
            return new GRAYSCALE();
        }

    },
    MONOCHROME{
        @Override
        public CommandObject handle(){
            return new MONOCHROME();
        }

    },
    NEGATIVE{
        @Override
        public CommandObject handle(){
            return new NEGATIVE();
        }
    },
    UNDO{
        @Override
        public CommandObject handle(){
            return new UNDO();
        }

    },
    REDO{
        @Override
        public CommandObject handle(){
            return new REDO();
        }

    },
    ADD{
        @Override
        public CommandObject handle(){
            return new ADD();
        }

    },
    SESSIONINFO{
        @Override
        public CommandObject handle(){
            return new SESSIONINFO();
        }

    },
    NEWSESSION{
        @Override
        public CommandObject handle() {
            return new NEWSESSION();
        }

    },
    SWITCHSESSION{
        @Override
        public CommandObject handle(){
            return new SWITCHSESSION();
        }

    },
    SWITCHIMAGE{
        @Override
        public CommandObject handle(){
            return new SWITCHIMAGE();
        }

    },
    COLLAGE{
        @Override
        public CommandObject handle(){
            return new COLLAGE();
        }

    },
    HELP{
        @Override
        public CommandObject handle(){
            return new HELP();
        }


    },
    EXIT{
        @Override
        public CommandObject handle(){
            return new EXIT();
        }


    },
    ROTATE{
        @Override
        public CommandObject handle(){
            return new ROTATE();
        }

    };

    public abstract CommandObject handle();
}