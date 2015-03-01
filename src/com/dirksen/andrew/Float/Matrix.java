package com.dirksen.andrew.Float;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;

public class Matrix{
	double mat[];
	DoubleBuffer dbuf;
	ByteBuffer bbuf;
	
	//mat must be length 16
	Matrix(double mat[]){
		this.mat = mat;
		linkbufs();
	}
	
	private void linkbufs(){
		bbuf = ByteBuffer.allocateDirect(16*8);
		bbuf.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
		dbuf = bbuf.asDoubleBuffer();
		
		//test
		//System.out.println(dbuf.hasArray());
		
		//TODO try wrap() instead of put()
		dbuf.put(mat);
		dbuf.position(0);
		
		//test
		//System.out.println(dbuf.hasArray());
	}
	
	DoubleBuffer getDbuf(){
		if (!dbuf.hasArray()){
			linkbufs();
		}
		return dbuf;
	}
	
	//multiplies a and b, placing the result in result, 
	static void multiply(double[] a, double[] b, double[] result){
		if (!(a.length == 16 & b.length == 16 & result.length == 16)){
			System.err.println("One or more of the arguments to matrix multiply was not length 16.\n" +
					"This could have cray results on your spaceship man.");
		}
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				for(int k = 0; k < 4; k++){
					result[i*4+j] += a[i*4+k] * b[k*4+j];
				}
			}
		}
	}
	
	//This method may be less efficient, try multiply(double[], double[], double[])
	static Matrix multiply(Matrix a, Matrix b){
		double[] result = new double[16];
		multiply(a.mat, b.mat, result);
		return new Matrix(result);
	}
	
	Matrix inverse(){
		double[] result = {
				mat[2*4+2]*mat[3*4+3]*mat[4*4+4] +
				mat[2*4+3]*mat[3*4+4]*mat[4*4+2] +
				mat[2*4+4]*mat[3*4+2]*mat[4*4+3] -
				mat[2*4+2]*mat[3*4+4]*mat[4*4+3] -
				mat[2*4+3]*mat[3*4+2]*mat[4*4+4] -
				mat[2*4+4]*mat[3*4+3]*mat[4*4+2],
				
				mat[1*4+2]*mat[3*4+4]*mat[4*4+3] +
				mat[1*4+3]*mat[2*4+3]*mat[4*4+4] +
				mat[1*4+4]*mat[3*4+3]*mat[4*4+4] -
				mat[1*4+2]*mat[3*4+3]*mat[4*4+4] -
				mat[1*4+3]*mat[3*4+4]*mat[4*4+2] -
				mat[1*4+4]*mat[3*4+2]*mat[4*4+3],
				
				mat[1*4+2]*mat[2*4+3]*mat[4*4+4] +
				mat[1*4+3]*mat[2*4+4]*mat[4*4+2] +
				mat[1*4+4]*mat[2*4+2]*mat[4*4+3] -
				mat[1*4+2]*mat[2*4+4]*mat[4*4+3] -
				mat[1*4+3]*mat[2*4+2]*mat[4*4+4] -
				mat[1*4+4]*mat[2*4+3]*mat[4*4+2],
				
				mat[1*4+2]*mat[2*4+4]*mat[3*4+3] +
				mat[1*4+3]*mat[2*4+2]*mat[3*4+4] +
				mat[1*4+4]*mat[2*4+3]*mat[3*4+2] -
				mat[1*4+3]*mat[2*4+3]*mat[3*4+4] -
				mat[1*4+3]*mat[2*4+4]*mat[3*4+2] -
				mat[1*4+4]*mat[2*4+2]*mat[3*4+3],
				
				mat[2*4+1]*mat[3*4+4]*mat[4*4+3] +
				mat[2*4+3]*mat[3*4+2]*mat[4*4+4] +
				mat[2*4+4]*mat[3*4+3]*mat[4*4+1] -
				mat[2*4+1]*mat[3*4+3]*mat[4*4+4] -
				mat[2*4+3]*mat[3*4+4]*mat[4*4+1] -
				mat[2*4+4]*mat[3*4+1]*mat[4*4+3],
				
				mat[1*4+1]*mat[3*4+3]*mat[4*4+4] +
				mat[1*4+3]*mat[3*4+4]*mat[4*4+1] +
				mat[1*4+4]*mat[3*4+1]*mat[4*4+3] -
				mat[1*4+1]*mat[3*4+4]*mat[4*4+3] -
				mat[1*4+3]*mat[3*4+1]*mat[4*4+4] -
				mat[1*4+4]*mat[3*4+3]*mat[4*4+1],
		
				mat[1*4+1]*mat[2*4+4]*mat[4*4+3] +
				mat[1*4+3]*mat[2*4+1]*mat[4*4+4] +
				mat[1*4+4]*mat[2*4+3]*mat[4*4+1] -
				mat[1*4+1]*mat[2*4+4]*mat[3*4+3] -
				mat[1*4+3]*mat[2*4+4]*mat[4*4+1] -
				mat[1*4+4]*mat[2*4+3]*mat[3*4+1],
		
				mat[1*4+1]*mat[2*4+3]*mat[3*4+4] +
				mat[1*4+3]*mat[2*4+4]*mat[3*4+1] +
				mat[1*4+4]*mat[2*4+1]*mat[3*4+3] -
				mat[1*4+1]*mat[2*4+4]*mat[3*4+3] -
				mat[1*4+3]*mat[2*4+1]*mat[3*4+4] -
				mat[1*4+4]*mat[2*4+3]*mat[3*4+1],
		
				mat[2*4+1]*mat[3*4+2]*mat[4*4+4] +
				mat[2*4+2]*mat[3*4+4]*mat[4*4+1] +
				mat[2*4+4]*mat[3*4+1]*mat[4*4+2] -
				mat[2*4+1]*mat[3*4+4]*mat[4*4+2] -
				mat[2*4+2]*mat[3*4+1]*mat[4*4+4] -
				mat[2*4+4]*mat[3*4+2]*mat[4*4+1],
		
				mat[1*4+1]*mat[3*4+4]*mat[4*4+2] +
				mat[1*4+2]*mat[3*4+1]*mat[4*4+4] +
				mat[1*4+4]*mat[3*4+2]*mat[4*4+1] -
				mat[1*4+1]*mat[3*4+2]*mat[4*4+4] -
				mat[1*4+2]*mat[3*4+4]*mat[4*4+1] -
				mat[1*4+4]*mat[2*4+2]*mat[4*4+1],
		
				mat[1*4+1]*mat[2*4+2]*mat[4*4+4] +
				mat[1*4+2]*mat[2*4+4]*mat[1*4+4] +
				mat[1*4+4]*mat[2*4+1]*mat[4*4+2] -
				mat[1*4+1]*mat[2*4+4]*mat[4*4+2] -
				mat[1*4+2]*mat[2*4+1]*mat[4*4+4] -
				mat[1*4+4]*mat[2*4+2]*mat[4*4+1],
		
				mat[1*4+1]*mat[2*4+4]*mat[3*4+2] +
				mat[1*4+2]*mat[2*4+1]*mat[3*4+4] +
				mat[1*4+4]*mat[2*4+2]*mat[3*4+1] -
				mat[1*4+1]*mat[2*4+2]*mat[3*4+4] -
				mat[1*4+2]*mat[2*4+4]*mat[3*4+1] -
				mat[1*4+4]*mat[2*4+1]*mat[3*4+2],
		
				mat[2*4+1]*mat[3*4+3]*mat[4*4+2] +
				mat[2*4+2]*mat[3*4+1]*mat[4*4+3] +
				mat[2*4+3]*mat[3*4+2]*mat[4*4+1] -
				mat[2*4+1]*mat[3*4+2]*mat[4*4+3] -
				mat[2*4+1]*mat[3*4+3]*mat[4*4+1] -
				mat[2*4+3]*mat[3*4+1]*mat[4*4+2],
		
				mat[1*4+1]*mat[3*4+2]*mat[4*4+3] +
				mat[1*4+2]*mat[3*4+3]*mat[4*4+1] +
				mat[1*4+3]*mat[3*4+1]*mat[4*4+2] -
				mat[1*4+1]*mat[3*4+3]*mat[4*4+2] -
				mat[1*4+2]*mat[3*4+1]*mat[4*4+3] -
				mat[3*4+1]*mat[3*4+2]*mat[4*4+1],
		
				mat[1*4+1]*mat[2*4+3]*mat[4*4+2] +
				mat[1*4+2]*mat[2*4+1]*mat[4*4+3] +
				mat[1*4+3]*mat[2*4+2]*mat[4*4+1] -
				mat[1*4+1]*mat[2*4+2]*mat[4*4+3] -
				mat[1*4+2]*mat[2*4+3]*mat[4*4+1] -
				mat[1*4+3]*mat[2*4+1]*mat[4*4+2],
		
				mat[1*4+1]*mat[2*4+2]*mat[3*4+3] +
				mat[1*4+2]*mat[2*4+3]*mat[3*4+1] +
				mat[1*4+3]*mat[2*4+1]*mat[3*4+2] -
				mat[1*4+1]*mat[2*4+3]*mat[3*4+2] -
				mat[1*4+2]*mat[2*4+1]*mat[3*4+3] -
				mat[1*4+3]*mat[2*4+2]*mat[3*4+1]
		};	//Typing this was inconvenient.
		
		return new Matrix(result);
	}

	double[] multiplyByVector(double x, double y, double z){
		
		double rx = mat[4*0+0]*x + mat[0*0+1]*y + mat[0*0+2]*z;
		double ry = mat[4*1+0]*x + mat[4*1+1]*y + mat[4*1+2]*z;
		double rz = mat[4*2+0]*x + mat[4*2+1]*y + mat[4*2+2]*z;
		
		System.out.println("original "+x+" "+y+" "+z+"\n" +
				"New "+rx+" "+ry+" "+rz);
		
		return new double[]{rx,ry,rz};
	}
	
	static double[] translation(double x, double y, double z){
		return new double[]{
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				x, y, z, 1
				};
	}
	
	static double[] identity(){
		return new double[]{
				1, 0, 0, 0,
				0, 1, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1
				};
	}
	
	static double[] rotation(double theta, double x, double y, double z){
		double length;

		// normalize
		length = Math.sqrt(x*x + y*y + z*z);

		// too close to 0, can't make a normalized vector
		if (length < .000001){
			System.out.println("BAD, axis vector is too small for normalization.");
		}
		
		x /= length;
		y /= length;
		z /= length;

		// do the trig
		double c = Math.cos(theta);
		double s = Math.sin(theta);
		double t = 1-c;

		return new double[]{
				t*x*x+c,  	t*x*y - s*z,	t*x*z + s*y,	0,
				t*x*y+s*z,	t*y*y+c,		t*y*z-s*x, 		0,
				t*x*z-s*y,	t*y*z+s*x,		t*z*z+c, 		0,
				0,			0, 				0, 				1
				};
	}
	
	static double[] scale(double x, double y, double z){
		return new double[]{
				x, 0, 0, 0,
				0, y, 0, 0,
				0, 0, z, 0,
				0, 0, 0, 1
				};
	}

	static void print(double mat[]){	//for print()
		for (int i = 0; i < mat.length; i++){
	    	if (i % 4 == 0){
		    	System.out.println();
	    	}
	    	System.out.print(mat[i]+ " ");
	    }
	    System.out.println();
	}
}