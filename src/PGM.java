public class PGM extends Image{

    public PGM(String directory) {
        super(directory);
    }

    @Override
    public ImageType getImageType(){
        return ImageType.PGM;
    }
}
