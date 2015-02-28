package com.dirksen.andrew.Float;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import org.lwjgl.glfw.GLFWKeyCallback;
//TODO import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class InputHandler {
	private GLFWKeyCallback keyCallback;
	//TODO private GLFWMouseButtonCallback mouseCallBack;
	boolean keyStates[];
	final long window;
	Satellite underControl;
	
	InputHandler(long window){
		this.window = window;
		keyStates = new boolean[GLFW_KEY_LAST];	//java always initializes boolean as false
		initKeyboard();
	}
	
	boolean isKeyDown(int key){
		return keyStates[key];
	}
	
	void destroy(){
        keyCallback.release();
	}
	
	private void initKeyboard(){
		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
        	
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
            	if (key <= GLFW_KEY_LAST){	//prevent out of bounds on keyStates
	            	if (action == GLFW_PRESS){
	            		onKeyPress(key);
	            	} else if (action == GLFW_RELEASE){
	            		onKeyRelease(key);
	            	}
            	}
            }
        });
	}
	
	void onKeyPress(int key){        
        keyStates[key] = true;
        System.out.println("You pressed " + (char)key);
    }
	
	void onKeyRelease(int key){
		if ( key == GLFW_KEY_ESCAPE)
            glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
        if ( key == GLFW_KEY_E)
            BddapIO.toggleHideMouse(window);
        
       	keyStates[key] = false;
	}
}