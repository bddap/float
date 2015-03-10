package com.dirksen.andrew.Float;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import org.lwjgl.glfw.GLFWKeyCallback;
//TODOE import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class InputHandler {
	private GLFWKeyCallback keyCallback;
	//private GLFWMouseButtonCallback mouseCallBack;
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
            	if (0 <= key & key <= GLFW_KEY_LAST){	//prevent out of bounds on keyStates
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
		final double tvstep = 0.05;
		final double rvstep = 0.01;
		
		if (key == GLFW_KEY_E){
        	underControl.thrust(new Velocity(0,0,-tvstep));
        }else if (key == GLFW_KEY_S){
			underControl.thrust(new Velocity(-tvstep,0,0));
        }else if (key == GLFW_KEY_D){
        	underControl.thrust(new Velocity(0,0,tvstep));     	
        }else if (key == GLFW_KEY_F){
        	underControl.thrust(new Velocity(tvstep,0,0));    	
        }else if (key == GLFW_KEY_A){
        	underControl.thrust(new Velocity(0,tvstep,0));    	
        }else if (key == GLFW_KEY_V){
        	underControl.thrust(new Velocity(0,-tvstep,0));    	
        }else
        if (key == GLFW_KEY_I){
        	underControl.thrust(new AngularVelocity(-rvstep, 1, 0, 0));
        }else if (key == GLFW_KEY_K){
        	underControl.thrust(new AngularVelocity(rvstep, 1, 0, 0));
        }else if (key == GLFW_KEY_J){
        	underControl.thrust(new AngularVelocity(-rvstep, 0, 1, 0));
        }else if (key == GLFW_KEY_L){
        	underControl.thrust(new AngularVelocity(rvstep, 0, 1, 0));
        }else if (key == GLFW_KEY_SEMICOLON){
        	underControl.thrust(new AngularVelocity(rvstep, 0, 0, 1));
        }else if (key == GLFW_KEY_N){
        	underControl.thrust(new AngularVelocity(-rvstep, 0, 0, 1));
        }
	}

	void onKeyRelease(int key){
		if ( key == GLFW_KEY_ESCAPE)
            glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
        if ( key == GLFW_KEY_R)
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