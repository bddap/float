//based off of the example from the lwjg 3 website

package com.dirksen.andrew.Float;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class BddapIO {
	private int hieght,width;//,originalHieght,originalWidth;
	private String windowName;
	private long myWindow;	//used to identify corresponding window
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;	//for keyboard input
    private static int monitorHieght,monitorWidth;
    private long initTime;
	//public boolean[] pressedKeys;
	
	BddapIO(int width, int hieght, String windowName){
		this.width = width;
		this.hieght = hieght;
		this.windowName = windowName;
		//pressedKeys = new boolean[64];
		initWindow();
		initTime = System.currentTimeMillis();
	}
	
	BddapIO(int width, int hieght){
		this(width,hieght,"");
	}
	
	BddapIO(){
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
        
        //ubuntu with unity crashes here
        //see https://github.com/glfw/glfw/issues/368
        System.out.println("Unity halts here. If your game stops at this line, you need to update unity.\n" +
        		"If there is no unity update yet, you can try using gnome:\n" +
        		"http://askubuntu.com/questions/450294/how-to-switch-from-unity-to-gnome");
        myWindow = glfwCreateWindow(width, hieght, windowName, NULL, NULL);
        System.out.println("Good thing you are not using unity. :)");
		
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
        
        initGl();
        
        //test
        
        asteroid = new Asteroid(-2,-1,-10);
	}
	
	private void initGl() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
        GL11.glClearDepth(1.0); 
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL); 
        
        GL11.glMatrixMode(GL11.GL_PROJECTION); 
        GL11.glLoadIdentity();

        setFrustum(Math.PI/2);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	}
	
	void setFrustum(double fov){
		double near = 0.001;
		double far = 100.0;
		double aspect = hieght/width;
		double right = Math.tan(fov/2)*near;
		double left = -right;
		double top = right * aspect;
		double bottom = -top;
		GL11.glFrustum(left, right, bottom, top, near, far);
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
	
	Asteroid asteroid;
	
	void testUpdate(){
		float age = age();
		//System.out.println("poop is yummy");	//Good one
    	float blu = (float)(Math.sin(age)/2+0.5);
    	float red = (float)(Math.sin(age*0.99f)/2+0.5);
    	float gre = (float)(Math.sin(age*1.01f)/2+0.5);
    	
    	GL11.glClearColor(red, gre, blu, 0.5f);
    	prepareToDraw();

        //glTranslated(1,5,-10);
    	//glRotated(Math.sin(age)*1000, Math.sin(age), Math.cos(age), 0);
    	//GL11.glScalef(10, 10, 10);
    	
        asteroid.draw();
        printCurrentModelviewMatrix();
        
        sendToScreen();
        
        inputUpdate();
	}
	
	private void prepareToDraw(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        GL11.glLoadIdentity(); 
	}
	
	private void sendToScreen(){
		 glfwSwapBuffers(myWindow); // swap the color buffers
	}
	
	void inputUpdate(){
	     // Poll for window events. The key callback above will only be
	     // invoked during this call.
		 glfwPollEvents();
	}
	
	void printCurrentModelviewMatrix(){
		FloatBuffer modelview = BufferUtils.createFloatBuffer(16);
    	GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelview);
    	for(int i = 0 ; i < 16 ; i++)
    	{
    		if (i % 4 == 0)
    			System.out.println();
    		System.out.print(modelview.get(i) + " ");
    	}
		System.out.println();
	}
}
