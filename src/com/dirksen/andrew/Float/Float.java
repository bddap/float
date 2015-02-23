package com.dirksen.andrew.Float;

public class Float {

	public static void main(String[] args) {
		GameWindow window = new GameWindow(500,500,"float");
		window.hideMouse();
		while(!window.shouldClose()){
			window.testUpdate();
		}
		window.destroy();
	}
}
