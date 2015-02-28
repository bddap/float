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
	private static Satellite underControl;
	static boolean hasValidSatellite = false;
	
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
        
        if (hasValidSatellite){
        	controlSatellite(key);
        }
    }
	
	private void controlSatellite(int key) {
		final double tvstep = 0.01;
		final double rvstep = 0.1;
		
		if (key == GLFW_KEY_W){
        	underControl.linearVTranslateLocal(0, 0, tvstep);
        }
		else if (key == GLFW_KEY_A){
        	underControl.linearVTranslateLocal(0, 0, -tvstep);
        }else if (key == GLFW_KEY_S){
        	underControl.linearVTranslateLocal(tvstep, 0, 0.0);        	
        }else if (key == GLFW_KEY_D){
        	underControl.linearVTranslateLocal(-tvstep, 0, 0.0);        	
        }else if (key == GLFW_KEY_UP){
        	underControl.rotvel = underControl.rotvel.rotate(rvstep, 1, 0, 0);
        }else if (key == GLFW_KEY_DOWN){
        	underControl.rotvel = underControl.rotvel.rotate(-rvstep, 1, 0, 0);
        }else if (key == GLFW_KEY_LEFT){
        	underControl.rotvel = underControl.rotvel.rotate(rvstep, 0, 1, 0);
        }else if (key == GLFW_KEY_RIGHT){
        	underControl.rotvel = underControl.rotvel.rotate(-rvstep, 0, 1, 0);
        }
	}

	void onKeyRelease(int key){
		if ( key == GLFW_KEY_ESCAPE)
            glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
        if ( key == GLFW_KEY_E)
            BddapIO.toggleHideMouse(window);
        
       	keyStates[key] = false;
	}
	
	void takeControlOf(Satellite toControl){
		underControl = toControl;
		hasValidSatellite = true;
	}
	
	void printKeyStates(){
		for (int i = 0; i<keyStates.length; i++){
        	if (keyStates[i]){
        		System.out.print("1");
        	}else{
        		System.out.print("0");
        	}
        	if (i%4==3)
        		System.out.print(" ");
        	if (i%64==63)
        		System.out.println();
        }
        System.out.println();
        System.out.println();
	}
}