package com.company.command;

public class CommandState {

    Command undo;
    Command redo;

    public CommandState(Command undo, Command redo){
        this.undo = undo;
        this.redo = redo;

    }

}
