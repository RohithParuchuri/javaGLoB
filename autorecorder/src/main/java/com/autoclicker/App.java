package com.autoclicker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class App {
    private static boolean listenersActive = true;
    public static void main(String[] args) {
        try {
            // Register the native hook
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        MouseListener mouseListener = new MouseListener();
        GlobalScreen.addNativeMouseListener(mouseListener);
        
        // Used to load mouseMotion system
        // GlobalScreen.addNativeMouseMotionListener(mouseListener);

        // Create an instance of the listener and add it
        KeyListener listener = new KeyListener();
        GlobalScreen.addNativeKeyListener(listener);
    }
    public static void stopListeners()
    {
        try {
            if (listenersActive) {
                GlobalScreen.unregisterNativeHook();
                listenersActive = false;
                System.out.println("Listeners stopped.");
            }
        } catch (NativeHookException ex) {
            System.err.println("Error removing native hooks.");
            System.err.println(ex.getMessage());
        }
    }
    public static void runRest()
    {
        ClickData.printData();
    }
}