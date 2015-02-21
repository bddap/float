//based off of the example from the lwjg 3 website

package com.dirksen.andrew.Float;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwGetInputMode;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class GameWindow {
	private int hieght,width;//,originalHieght,originalWidth;
	private String windowName;
	private long myWindow;	//used to identify corresponding window
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;	//for keyboard input
    private static int monitorHieght,monitorWidth;
    private long initTime;
	//public boolean[] pressedKeys;
	
	GameWindow(int width, int hieght, String windowName){
		this.width = width;
		this.hieght = hieght;
		this.windowName = windowName;
		//pressedKeys = new boolean[64];
		initWindow();
		initTime = System.currentTimeMillis();
	}
	
	GameWindow(int width, int hieght){
		this(width,hieght,"");
	}
	
	GameWindow(){
		this(500,500, "");
	}
	
	void destroy(){
        glfwDestroyWindow(myWindow);
        keyCallback.release();
        
        // Terminate GLFW and release the GLFWerrorfun
        glfwTerminate();
        errorCallback.release();
	}

	private void initWindow() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
		
		//start glfw
		if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
		
		//configure window
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // set resizable
        
        myWindow = glfwCreateWindow(width, hieght, windowName, NULL, NULL);
        if ( myWindow == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(myWindow, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
                if ( key == GLFW_KEY_E && action == GLFW_RELEASE )
                    toggleHideMouse();
            }
        });
        
        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        monitorWidth = GLFWvidmode.width(vidmode);
        monitorHieght = GLFWvidmode.height(vidmode);
        System.out.println("Monitor width == "+monitorWidth);
        System.out.println("Monitor hieght == "+monitorHieght);
        
        // Make the OpenGL context current
        glfwMakeContextCurrent(myWindow);

		// Enable v-sync
        glfwSwapInterval(1);	//TODO: play around with this number
        
        //put window in front/make window visible
        //not sure which
        glfwShowWindow(myWindow);
        
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GLContext.createFromCurrent();
	}
	
	void hideMouse(){
        glfwSetInputMode(myWindow, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED );
	}
	
	void showMouse(){
        glfwSetInputMode(myWindow, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
	}
	
	void toggleHideMouse(){
		if (glfwGetInputMode(myWindow, GLFW.GLFW_CURSOR) == GLFW.GLFW_CURSOR_NORMAL){
	        glfwSetInputMode(myWindow, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED );
		}
		else{
	        glfwSetInputMode(myWindow, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
		}
	}
	
	float age(){
		return (float)(System.currentTimeMillis()-initTime)/1.0E3f;
		
	}
	
	boolean shouldClose(){
		return (glfwWindowShouldClose(myWindow) == GL_TRUE);
	}
	
	void testUpdate(){
		float age = age();
		
    	float blu = (float)(Math.sin(age)/2+0.5);
    	float red = (float)(Math.sin(age*0.99f)/2+0.5);
    	float gre = (float)(Math.sin(age*1.01f)/2+0.5);
    	
    	//System.out.println(blu);
        glClearColor(red, gre, blu, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glfwSwapBuffers(myWindow); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
	}
}
