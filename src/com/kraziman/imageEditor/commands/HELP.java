package com.kraziman.imageEditor.commands;

import com.kraziman.imageEditor.interfaces.CommandObject;
import com.kraziman.imageEditor.ie.ImageEditor;

public class HELP implements CommandObject {
    @Override
    public void handle(){
        ImageEditor.help();
    }

    @Override
    public String help(){
        return "help or help <command> - Displays information for all commands or gives additional information to how a command works";
    }
}
