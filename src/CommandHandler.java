import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private static final Map<Command, Runnable> handle = new HashMap<>();
    private static Session session;
    private static Image image;
    private static ImageEditor imageEditor;

    static{
        //TODO: Add parameters to the methods
        //TODO: Instead of using these variables use directly the static values from ImageEditor
        handle.put(Command.ADD, () -> session.add(image));
        handle.put(Command.UNDO, () -> session.undo());
        handle.put(Command.REDO, () -> session.redo());
        handle.put(Command.SESSIONINFO, () -> session.sessionInfo());

        handle.put(Command.LOAD, () -> imageEditor.load());
        handle.put(Command.SAVE, () -> imageEditor.save());
        handle.put(Command.SWITCH, () -> imageEditor.switchSession());
        handle.put(Command.COLLAGE, () -> imageEditor.collage());
        handle.put(Command.HELP, () -> imageEditor.help());
        handle.put(Command.EXIT, () -> imageEditor.exit());

        handle.put(Command.ROTATE, () -> image.rotate());
        handle.put(Command.NEGATIVE, () -> image.negative());
        handle.put(Command.GRAYSCALE, () -> image.grayscale());
        handle.put(Command.MONOCHROME, () -> image.monochrome());

    }

    public static Map<Command, Runnable> getHandle() {
        return handle;
    }
}
