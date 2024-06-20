public class SWITCHSESSION implements CommandObject{
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
}
