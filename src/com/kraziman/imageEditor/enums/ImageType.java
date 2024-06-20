package com.kraziman.imageEditor.enums;

import com.kraziman.imageEditor.images.Image;
import com.kraziman.imageEditor.images.PBM;
import com.kraziman.imageEditor.images.PGM;
import com.kraziman.imageEditor.images.PPM;

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
