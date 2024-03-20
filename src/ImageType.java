public enum ImageType {
    PPM{
        public Image handle(String directory){
            return new PPM(directory);
        }
    },
    PGM{
        public Image handle(String directory){
            return new PGM(directory);
        }
    },
    PBM{
        public Image handle(String directory){
            return new PBM(directory);
        }
    };

    public abstract Image handle(String directory);

}
