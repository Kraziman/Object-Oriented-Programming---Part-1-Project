package com.kraziman.imageEditor.exceptions;

public class InvalidCommandFormat extends IllegalArgumentException{
    public InvalidCommandFormat(String s){
        super(s);
    }
}
