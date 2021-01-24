package com.company.command;

import java.util.Stack;

public class CommandStack {

    static Stack<CommandState> undoStack = new Stack<>();
    static Stack<CommandState> redoStack = new Stack<>();

    public void undoState(){
        if(undoStack.size() > 0) {
            redoStack.push(undoStack.peek());
            undoStack.pop().undo.execute();
        }

    }

    public void redoState() {
        if(redoStack.size() > 0) {
            undoStack.push(redoStack.peek());
            redoStack.pop().redo.execute();
        }
    }

        public static void addState(CommandState state) {
            undoStack.push(state);
            redoStack.clear();
    }



}
