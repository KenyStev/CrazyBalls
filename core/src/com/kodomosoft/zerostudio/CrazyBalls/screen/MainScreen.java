package com.kodomosoft.zerostudio.CrazyBalls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.kodomosoft.zerostudio.CrazyBalls.actor.ButtonActor;

public class MainScreen extends AbstractScreen {
	
	/********VARIABLES DE INSTANCIA*********/
	private ButtonActor btn1, btn2, btn3;
	private Stage stg;
	private Image fondo;
	
	private Sound wavSound, menuSong;
	/***************************************/

	/*CONSTRUCTOR DE LA PANTALLA DEL MENU PRINCIPAL*/
	public MainScreen(CrazyBalls game) {
		super(game);
	}

	@Override
	public void show() {
		
		//Inicializamos el Stage
		stg = new Stage();//400, 800, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stg);
		
		// Inicializamos los sonidos
		menuSong = CrazyBalls.MANAGER.get("background.ogg", Sound.class);
		menuSong.loop();
		menuSong.play();
		
		//Creamos el Fondo
		Texture txt = CrazyBalls.MANAGER.get("background.png", Texture.class);
		txt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion txtr = new TextureRegion(txt, 480, 800);
		fondo = new Image(txtr);
		stg.addActor(fondo);
		
		//Creando los Botones
		btn1 = new ButtonActor(1, game);
		btn1.setPosition(stg.getWidth()/2 - btn1.getWidth()/2, stg.getHeight()/2 + btn1.getHeight()/1.5f);
		stg.addActor(btn1);
		
		btn2 = new ButtonActor(2, game);
		btn2.setPosition(stg.getWidth()/2 - btn2.getWidth()/2, ((stg.getHeight()/2 - btn2.getHeight()/2) + 5));
		stg.addActor(btn2);
		
		btn3 = new ButtonActor(3, game);
		btn3.setPosition(stg.getWidth()/2 - btn3.getWidth()/2, stg.getHeight()/2 - btn3.getHeight()*1.5f);
		stg.addActor(btn3);
		
	
	}
	@Override
	public void resize(int width, int height) {
//		stg.setViewport(400, 800, true);
	}
	
	@Override
	public void render(float delta) {
		stg.act();
		stg.draw();
	}
	
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		menuSong.stop();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		stg.dispose();
	}
}
