public class PPM extends Image{

    public PPM(String directory) {
        super(directory);
    }

    @Override
    public ImageType getImageType(){
        return ImageType.PPM;
    }
}
