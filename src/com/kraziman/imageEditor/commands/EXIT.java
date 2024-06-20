package com.kraziman.imageEditor.commands;

import com.kraziman.imageEditor.interfaces.CommandObject;
import com.kraziman.imageEditor.ie.ImageEditor;

public class EXIT implements CommandObject {
    @Override
    public void handle(){
        ImageEditor.exit();
    }

    @Override
    public String help(){
        return "exit - exits the program";
    }
}
