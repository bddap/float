package com.dirksen.andrew.Float;

import Jama.Matrix;

public class AngularVelocity {
	//Angular Velocity will likely represent a local transform that is applied to A Rotation every tick
	
	double theta;
	double x;
	double y;
	double z;
	
	AngularVelocity(double theta, double x, double y, double z){
		this.theta = theta;
		this.x = x;
		this.y = y;
		this.z = z;
		
		normalize();
	}

	AngularVelocity() {
		this(0,1,0,0);
	}
	
	private void normalize(){
		double l = Math.sqrt(x*x+y*y+z*z);
		x /= l;
		y /= l;
		z /= l;
	}
	
	Matrix matrix(){
		return new Matrix(Bmat.rotation(theta, x, y, z));
	}
}
