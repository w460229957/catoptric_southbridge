package controller;

public class ControllerNative {
     public native void moveMirror(int row,int col,int motor,int direction,int steps);
     public native void reset();
     public native void test();
     public native void quit();
     public native void invokeNativeConsole();
     //TODO: add more native methods here
     public native void getPendingMessages();
}
