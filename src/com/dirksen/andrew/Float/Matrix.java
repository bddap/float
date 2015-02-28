package com.dirksen.andrew.Float;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;

public class Matrix {
	double mat[];
	DoubleBuffer matb;
	ByteBuffer bb;
	
	Matrix(double matData[]){
		initMatb();
		this.mat = matData;
		this.matb.put(mat);
		matb.position(0);
	}
	
	Matrix(DoubleBuffer matData){
		initMatb();
		this.matb = matData;
		this.mat = matb.array();
		matb.position(0);
	}
	
	Matrix(){
		initMatb();
		mat = new double[]{
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
			};
		this.matb.put(mat);
		matb.position(0);
	}
	
	private void initMatb(){
		bb = ByteBuffer.allocateDirect(16*8);
		bb.order(ByteOrder.nativeOrder());    // use the device hardware's native byte order
		matb = bb.asDoubleBuffer();
	}
	
	private Matrix multiply(double other[]){
		double result[] = new double[16];
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				for(int k = 0; k < 4; k++){
					result[i*4+j] += mat[i*4+k] * other[k*4+j];
				}
			}
		}
		
		return new Matrix(result);
	}

	Matrix multiply(Matrix otherMat){
		return multiply(otherMat.mat);
	}

	Vector multiply(double x, double y, double z){
		double result[] = new double[3];
		
		result[0] = mat[0*4+0]*x + mat[0*4+1]*y + mat[0*4+2]*z + mat[0*4+3];
		result[1] = mat[1*4+0]*x + mat[1*4+1]*y + mat[1*4+2]*z + mat[1*4+3];
		result[2] = mat[2*4+0]*x + mat[2*4+1]*y + mat[2*4+2]*z + mat[2*4+3];

		System.out.print(result[0]+" ");
		System.out.print(result[1]+" ");
		System.out.println(result[2]+" ");
		
		return new Vector(result);
	}
	
	static Matrix translation(double x, double y, double z){
		return new Matrix(new double[]{
					1, 0, 0, 0,
					0, 1, 0, 0,
					0, 0, 1, 0,
					x, y, z, 1
					});
	}
	
	static Matrix identity(){
		return new Matrix(new double[]{
					1, 0, 0, 0,
					0, 1, 0, 0,
					0, 0, 1, 0,
					0, 0, 0, 1
					});
	}
	
	static Matrix rotation(double theta, double x, double y, double z){
		double length;
		double c,s,t;

		// normalize
		length = Math.sqrt(x*x + y*y + z*z);

		// too close to 0, can't make a normalized vector
		if (length < .000001){
			System.out.println("BAD, rotation axis is not a nomalized vector.");
		}
		

		x /= length;
		y /= length;
		z /= length;

		// do the trig
		c = Math.cos(theta);
		s = Math.sin(theta);
		t = 1-c;

		return new Matrix(new double[]{
					t*x*x+c, t*x*y - s*z, t*x*z + s*y, 0,
					t*x*y+s*z, t*y*y+c, t*y*z-s*x, 0,
					t*x*z-s*y, t*y*z+s*x, t*z*z+c, 0,
					0, 0, 0, 1
					});
	}
	
	static Matrix scale(double x, double y, double z){
		return new Matrix(new double[]{
				x, 0, 0, 0,
				0, y, 0, 0,
				0, 0, z, 0,
				0, 0, 0, 1
				});
	}

	static void printDubbaray(double dubs[]){	//for print()
		for (int i = 0; i < dubs.length; i++){
	    	if (i % 4 == 0){
		    	System.out.println();
	    	}
	    	System.out.print(dubs[i]+ " ");
	    }
	    System.out.println();
	}
	
	void print(){
		System.out.print("\nmat:");
		printDubbaray(mat);
		
		//System.out.print("\nmatb:");
		//printDubbaray(matb.array());
	}

	Matrix rotate(double theta, double x, double y, double z){
		return Matrix.rotation(theta, x, y, z).multiply(this);
	}
	
	Matrix translate(double x, double y, double z){
		return this.multiply(Matrix.translation(x, y, z));
	}
}