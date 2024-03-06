import java.util.HashMap;
import java.util.Map;

public class ImageTypeHandler {
    private static final Map<ImageType, Image> handle = new HashMap<>();
    private static String directory;

    static {
        handle.put(ImageType.PBM, new PBM(directory));
        handle.put(ImageType.PGM, new PGM(directory));
        handle.put(ImageType.PPM, new PPM(directory));
    }

    public static Map<ImageType, Image> getHandle() {
        return handle;
    }

    public static String getDirectory() {
        return directory;
    }

    public static void setDirectory(String directory) {
        ImageTypeHandler.directory = directory;
    }
}
