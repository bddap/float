package com.dirksen.andrew.Float;

public class Matrix {
	double[][] mat;
	
	Matrix(double matData[][]){
		this.mat = matData;
	}
	
	Matrix(){
		mat = new double[][]{
			{ 1, 0, 0, 0},
			{ 0, 1, 0, 0},
			{ 0, 0, 1, 0},
			{ 0, 0, 0, 1}
			};
	}
	
	void multiply(double other[][]){
		double result[][] = new double[4][4];
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				for(int k = 0; k < 4; k++){
					result[i][j] += mat[i][k] * other[k][j];
				}
			}
		}
	}

	void multiply(Matrix otherMat){
		multiply(otherMat.mat);
	}
	
	static Matrix translation(double x, double y, double z){
		return new Matrix(new double[][]{
					{ 1, 0, 0, x},
					{ 0, 1, 0, y},
					{ 0, 0, 1, z},
					{ 0, 0, 0, 1}
					});
	}
	
	static Matrix identity(){
		return new Matrix(new double[][]{
					{ 1, 0, 0, 0},
					{ 0, 1, 0, 0},
					{ 0, 0, 1, 0},
					{ 0, 0, 0, 1}
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

		return new Matrix(new double[][]{
					{ t*x*x+c, t*x*y - s*z, t*x*z + s*y, 0},
					{ t*x*y+s*z, t*y*y+c, t*y*z-s*x, 0},
					{ t*x*z-s*y, t*y*z+s*x, t*z*z+c, 0},
					{ 0, 0, 0, 1}	//might need a 0 here instead of a 1
					});
	}
}
