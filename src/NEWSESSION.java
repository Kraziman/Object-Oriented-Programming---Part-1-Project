public class NEWSESSION implements CommandObject{
    @Override
    public void handle() {
        ImageEditor.setCurrentSession(new Session());
    }

    @Override
    public String help() {
        return "newsession - create a new empty session.";
    }
}
