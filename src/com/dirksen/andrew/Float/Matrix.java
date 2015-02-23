package com.dirksen.andrew.Float;

public class Matrix {
	double[][] mat;
	
	Matrix(double matData[][]){
		this.mat = matData;
	}
	
	Matrix(){
		mat = new double[][]
			{
				{ 1, 0, 0, 0},
				{ 0, 1, 0, 0},
				{ 0, 0, 1, 0},
				{ 0, 0, 0, 1}
			};
	}
	
	void multiply(double other[][]){
		double result[][] = 
			{
				{ mat[0][0]*other[0][0]+mat[0][1]*other[1][0]+mat[0][2]*other[2][0], mat[0][0]*other[0][1]+mat[0][1]*other[1][1]+mat[2][0]*other[2][1], 0, 0},
				{ 0, 1, 0, 0},
				{ 0, 0, 1, 0},
				{ 0, 0, 0, 1}
			};
		
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
}
