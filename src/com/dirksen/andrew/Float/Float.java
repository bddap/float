package com.dirksen.andrew.Float;

public class Float {

	public static void main(String[] args) {
		BddapIO window = new BddapIO(500,500,"float");
		window.hideMouse();
		while(!window.shouldClose()){
			window.testUpdate();
		}
		window.destroy();
	}
}
