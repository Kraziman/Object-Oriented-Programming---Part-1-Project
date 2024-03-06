import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private  Map<Command, Runnable> commands = new HashMap<>();
    private Session session;
    private Image image;
    private ImageEditor imageEditor;
    private Direction direction;

    public CommandHandler(){
        //TODO: Add parameters to the methods
        commands.put(Command.ADD, () -> session.add(image));
        commands.put(Command.UNDO, () -> session.undo());
        commands.put(Command.REDO, () -> session.redo());
        commands.put(Command.SESSIONINFO, () -> session.sessionInfo());

        commands.put(Command.LOAD, () -> imageEditor.load());
        commands.put(Command.SAVE, () -> imageEditor.save());
        commands.put(Command.SWITCH, () -> imageEditor.switchSession());
        commands.put(Command.COLLAGE, () -> imageEditor.collage());
        commands.put(Command.HELP, () -> imageEditor.help());
        commands.put(Command.EXIT, () -> imageEditor.exit());

        commands.put(Command.ROTATE, () -> image.rotate(direction));
        commands.put(Command.NEGATIVE, () -> image.negative());
        commands.put(Command.GRAYSCALE, () -> image.grayscale());
        commands.put(Command.MONOCHROME, () -> image.monochrome());

    }


}
