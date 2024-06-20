package com.kraziman.imageEditor.commands;

import com.kraziman.imageEditor.interfaces.CommandObject;
import com.kraziman.imageEditor.ie.ImageEditor;
import com.kraziman.imageEditor.session.Session;

public class NEWSESSION implements CommandObject {
    @Override
    public void handle() {
        ImageEditor.setCurrentSession(new Session());
    }

    @Override
    public String help() {
        return "newsession - create a new empty session.";
    }
}
