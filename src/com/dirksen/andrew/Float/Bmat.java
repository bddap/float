package com.dirksen.andrew.Float;

public class Bmat{
	static double[][] identity(){
		double[][] iden = {
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
				};
		return iden;
	}
	
	static double[][] translation(double x, double y, double z){
		double[][] tran = {
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{x, y, z, 1}
				};
		return tran;
	}

	static double[][] scale(double x, double y, double z){
		return new double[][]{
				{x, 0, 0, 0},
				{0, y, 0, 0},
				{0, 0, z, 0},
				{0, 0, 0, 1}
				};
	}
	
	static double[][] scale(double s){
		return new double[][]{
				{s, 0, 0, 0},
				{0, s, 0, 0},
				{0, 0, s, 0},
				{0, 0, 0, 1}
				};
	}
	
	static double[][] rotation(double theta, double x, double y, double z){
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

		return new double[][]{
				{t*x*x+c,  	t*x*y - s*z,	t*x*z + s*y,	0},
				{t*x*y+s*z,	t*y*y+c,		t*y*z-s*x, 		0},
				{t*x*z-s*y,	t*y*z+s*x,		t*z*z+c, 		0},
				{0,			0, 				0, 				1}
				};
	}
}
