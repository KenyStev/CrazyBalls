package com.kodomosoft.zerostudio.CrazyBalls.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.kodomosoft.zerostudio.CrazyBalls.listener.InputDYAListener;

/**
 * ButtonActor es una clase que crea los bottones del menu principal 
 * dependiendo del indice del menu que se le d��� siendo:
 * 1 - Boton de Play
 * 2 - Boton de Opciones
 * 3 - Boton de Exit
 * */

public class ButtonActor extends Image {

	/*VARIABLES DE INSTANCIA*/
	private Texture TextButton;
	private TextureRegion buttontxt;
	private int buttonIn;
	
	/*CONTRUCTOR DE LOS BOTONES DEL MENU PRINCIPAL*/
	public ButtonActor(int button, CrazyBalls game) {
		this.buttonIn=button;
		
		//Tomamos la Textura Segun el Indice
		switch(buttonIn){
		case 1:
			TextButton = CrazyBalls.MANAGER.get("btnPlay.png", Texture.class);
			break;
		case 2:
			TextButton = CrazyBalls.MANAGER.get("btnOptions.png", Texture.class);
			break;
		case 3:
			TextButton = CrazyBalls.MANAGER.get("btnExit.png", Texture.class);
			break;
		}
		
		TextButton.setFilter(TextureFilter.Linear, TextureFilter.Linear);

//		buttontxt = new TextureRegion(TextButton, 241, 108);
//
//		buttontxt = new TextureRegion(TextButton, 221, 89);

		buttontxt = new TextureRegion(TextButton, 241, 108);
		setSize(buttontxt.getRegionWidth(), buttontxt.getRegionHeight());
		addListener(new InputDYAListener(this, game, button-1));
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(buttontxt, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
