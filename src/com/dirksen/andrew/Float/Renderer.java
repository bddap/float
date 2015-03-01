package com.dirksen.andrew.Float;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

public class Renderer {
	final long windowId;
	private double fov = Math.PI/4;
	
	Renderer(long windowId){
		this.windowId = windowId;
		initGl();
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

        refreshFrustum();

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	}
	
	void refreshFrustum(){
		//get current window size
		ByteBuffer wb = BufferUtils.createByteBuffer(4);
		ByteBuffer hb = BufferUtils.createByteBuffer(4);
		glfwGetWindowSize(windowId, wb, hb);
		int w = wb.getInt(0);
		int h = hb.getInt(0);
		
		//calculate frustum
		double near = 0.001;
		double far = 100.0;
		double aspect = h/w;
		double right = Math.tan(fov/2)*near;
		double left = -right;
		double top = right * aspect;
		double bottom = -top;
		GL11.glFrustum(left, right, bottom, top, near, far);	//apply
	}

	void setFov(double fov){
		this.fov = fov;
		refreshFrustum();
	}
	
	void setClearColor(	float r, float g, float b, float a){
    	GL11.glClearColor(r, g, b, a);
	}
	
	void prepareToDraw(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        GL11.glLoadIdentity(); 
	}
	
	void sendToScreen(){
		 glfwSwapBuffers(windowId); // swap the color buffers
	}
	
	void setAsCamera(Satellite cam){
		
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
