//based off of the example from the lwjg 3 website

package com.dirksen.andrew.Float;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.GL_TRUE;	//trying too keep
import static org.lwjgl.opengl.GL11.GL_FALSE;	//openGl code in Renderer.class
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GLContext;

public class BddapIO {
	private int hieght,width;//,originalHieght,originalWidth;
	private String windowName;
	final long windowId;	//used to identify corresponding window
    private GLFWErrorCallback errorCallback;
    GLFWWindowSizeCallback windowResize;
    private static int monitorHieght,monitorWidth;
    private long initTime;
    InputHandler input;
    Renderer render;
	
	BddapIO(int width, int hieght, String windowName){
		this.width = width;
		this.hieght = hieght;
		this.windowName = windowName;
		//pressedKeys = new boolean[64];
		windowId = initWindow();
		initTime = System.currentTimeMillis();
	}
	
	BddapIO(int width, int hieght){
		this(width,hieght,"");
	}
	
	BddapIO(){
		this(500,500, "");
	}
	
	void destroy(){
        glfwDestroyWindow(windowId);
        input.destroy();
        
        // Terminate GLFW and release the GLFWerrorfun
        glfwTerminate();
        errorCallback.release();
	}

	private long initWindow() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
		
		//start glfw
		if ( glfwInit() != GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
		
		//configure window
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // set resizable
        
        //ubuntu with unity crashes here
        //see https://github.com/glfw/glfw/issues/368
        System.out.println("Unity halts here. If your game stops at this line, you need to update unity.\n" +
        		"If there is no unity update yet, you can try using gnome:\n" +
        		"http://askubuntu.com/questions/450294/how-to-switch-from-unity-to-gnome");
        long windowId = glfwCreateWindow(width, hieght, windowName, NULL, NULL);
        System.out.println("Good thing you are not using unity. :)");
		
        if ( windowId == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        
        input = new InputHandler(windowId);
        
        /* 	unfinished
		GLFW.glfwSetWindowSizeCallback(myWindow, windowResize = new GLFWWindowSizeCallback(){
			@Override
            public void invoke(long window, int width, int height){
				setFrustum(Math.PI/4, width, height);
			}
        });
        */
        
        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        monitorWidth = GLFWvidmode.width(vidmode);
        monitorHieght = GLFWvidmode.height(vidmode);
        System.out.println("Monitor width == "+monitorWidth);
        System.out.println("Monitor hieght == "+monitorHieght);
        
        // Make the OpenGL context current
        glfwMakeContextCurrent(windowId);

		// Enable v-sync
        glfwSwapInterval(1);	//TODO: play around with this number
        
        //put window in front/make window visible
        //not sure which
        glfwShowWindow(windowId);
        
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GLContext.createFromCurrent();
        
        render = new Renderer(windowId);
        
        return windowId;
	}
	
	
	

	static void hideMouse(long window){
        glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED );
	}
	
	static void showMouse(long window){
        glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
	}
	
	static void toggleHideMouse(long window){
		if (glfwGetInputMode(window, GLFW.GLFW_CURSOR) == GLFW.GLFW_CURSOR_NORMAL){
	        glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED );
		}
		else{
	        glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
		}
	}
	
	float age(){
		return (float)(System.currentTimeMillis()-initTime)/1.0E3f;
		
	}
	
	boolean shouldClose(){
		return (glfwWindowShouldClose(windowId) == GL_TRUE);
	}
	
	//begin test code
	Asteroid asteroid;
	private boolean didInit = false;
	
	void testUpdate(){
		if (didInit == false){
			asteroid = new Asteroid(
	        		new Matrix(Matrix.translation(0, 0, -10)),
	        		new Matrix(Matrix.translation(0.0, 0.0, 0)),
	        		new Matrix(Matrix.rotation(0, 0, 0, 1)),
	        		new Matrix(Matrix.rotation(0.0, 1, 1, 1))
	        		);
			input.takeControlOf(asteroid);
			didInit = true;
		}
		//float age = age();
		//System.out.println("poop is yummy");	//Good one
    	//float r = (float)(Math.sin(age*0.99f)/2+0.5);
    	//float g = (float)(Math.sin(age*1.01f)/2+0.5);
    	//float b = (float)(Math.sin(age)/2+0.5);
    	
    	//render.setClearColor(r,g,b,0.0f);
    	
    	render.prepareToDraw();
    	
    	asteroid.tick();
        asteroid.draw();
        //render.printCurrentModelviewMatrix();
        
        render.sendToScreen();
        
        inputUpdate();
	}
	//end test code
	
	void inputUpdate(){
	     // Poll for window events. The key callback above will only be
	     // invoked during this call.
		 glfwPollEvents();
	}
}
