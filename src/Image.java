public abstract class Image {
    public String directory = null;
    public MagicNumber magicNumber = null;
    public int[][] imageSize;
    public String[] imageComments;

    public Image(String directory, MagicNumber magicNumber){
        this.directory = directory;
        this.magicNumber = magicNumber;
        this.imageSize = imageSize(directory);
    }

    public int[][] imageSize(String directory){
        int x = 0;
        int y = 0;

        //TODO: Add logic for getting the image size(TIP - first row is 'magic number' and
        // then every row that starts with '#' is a comment and should be skipped and maybe
        // saved somewhere so they can be rewritten back into the image file)

        return new int[x][y];
    }

    //TODO: Add functionality to change the type of the image(either add it as a general
    // function of the program or just so it makes collages with different image types easi

    public void rotate(Direction d){

    }

    public void negative(){

    }

    public void grayscale(){

    }

    public void monochrome(){

    }

}
