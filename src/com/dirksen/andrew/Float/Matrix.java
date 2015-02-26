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
}
