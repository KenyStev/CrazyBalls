package com.kodomosoft.zerostudio.CrazyBalls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.kodomosoft.zerostudio.CrazyBalls.listener.InputDYAListener;

public class Instructions extends AbstractScreen {
	
	private Stage stage;
	private Image img1;

	public Instructions(CrazyBalls game) {
		super(game);
	}

	@Override
	public void show() {
		stage =  new Stage();//400, 800, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		//Crear Fondo
		Texture txt = new Texture("instructions.png");
		txt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion txtr = new TextureRegion(txt, 480, 800);
		Image img = new Image(txtr);
		stage.addActor(img);
		
		Texture bck = CrazyBalls.MANAGER.get("backButton.png", Texture.class);
		bck.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion bck1 = new TextureRegion(bck, 80, 54);
	
		//Creamos el Boton de back para regresar al Menu Principal
		img1 = new Image(bck1);
		img1.setPosition(50, 90);
		img1.addListener(new InputDYAListener(img1, game, 3));
		stage.addActor(img1);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
			
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
//		stage.setViewport(400, 800, true);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
	}

}