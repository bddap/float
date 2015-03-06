package com.dirksen.andrew.Float;

public class Float {
	public static void main(String[] args) {
		BddapIO bio = new BddapIO(500,500,"float");
		while(!bio.shouldClose()){
			bio.testUpdate();
		}
		bio.destroy();
	}
}
