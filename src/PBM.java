public class PBM extends Image{
    private boolean[] imageData;
    public PBM(String directory) {
        super(directory);
    }

    @Override
    public ImageType getImageType(){
        return ImageType.PBM;
    }

}
