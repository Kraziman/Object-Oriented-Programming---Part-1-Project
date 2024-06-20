package com.kraziman.imageEditor.commands;

import com.kraziman.imageEditor.interfaces.CommandObject;
import com.kraziman.imageEditor.enums.Direction;
import com.kraziman.imageEditor.exceptions.InvalidCommandFormat;
import com.kraziman.imageEditor.ie.ImageEditor;

public class ROTATE implements CommandObject {
    @Override
    public void handle(){
        if (ImageEditor.getInputArray().length <= 1){
            throw new InvalidCommandFormat("Invalid command format! Try rotate <direction>");
        }
        if (ImageEditor.getCurrentImage() != null){
            ImageEditor.setUserCommandParameters(ImageEditor.getInputArray()[1].split("\\s+", 1));
            ImageEditor.getCurrentImage().rotate(Direction.valueOf(ImageEditor.getUserCommandParameters()[0].toUpperCase()));
        }
        else {
            if (ImageEditor.getCurrentSession() == null){
                System.out.println("You need to create/open a session first!");
                return;
            }
            if (ImageEditor.getCurrentSession().getImages().isEmpty()){
                System.out.println("There are no images in the current session!");
                return;
            }
            if (ImageEditor.getCurrentImage() == null){
                System.out.println("You need to select an image before trying that!");
                return;
            }
            System.out.println("Unexpected error!");
        }
    }

    @Override
    public String help(){
        return "direction <LEFT or RIGHT> - rotates the current image 90 degrees left or right";
    }
}
