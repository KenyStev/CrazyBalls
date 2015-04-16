package com.kodomosoft.zerostudio.CrazyBalls.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ButtonImage extends Image {
	
	private int Level;

	public ButtonImage(TextureRegion region, int level) {
		super(region);
		this.Level=level;
	}
	
	public int getLevel(){
		return Level;
	}

}
