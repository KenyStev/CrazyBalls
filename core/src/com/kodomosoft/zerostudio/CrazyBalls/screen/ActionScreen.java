
package com.kodomosoft.zerostudio.CrazyBalls.screen;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.kodomosoft.zerostudio.CrazyBalls.actor.RemsBallActor;

public class ActionScreen extends AbstractScreen {
	
	private Stage stage;
	private float 	x = 0,
					y = 0;
	private int cantidad = 10,
				contador = 0;
	

	public ActionScreen(CrazyBalls game) {
		super(game);
	}

	@Override
	public void show() {
		//Crear Fondo
		/*Image imgFondo = new Image(CrazyBallsMain.MANAGER.get("background.png", Texture.class));
		imgFondo.setFillParent(true);
		stage.addActor(imgFondo);*/
		
		stage = new Stage();//400, 800, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		Texture txt = CrazyBalls.MANAGER.get("background.png", Texture.class);
		txt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion txtr = new TextureRegion(txt, 480, 800);
		stage = new Stage();
		Image img = new Image(txtr);
		stage.addActor(img);
		img.setFillParent(true);
		
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		
		if(contador<cantidad){
			createBall();
			contador++;
		}
		
		stage.draw();
	}
	
	//Metodo para crea nuevas pelotas
	private void createBall(){
		
		try{
		x = ((0.01f * stage.getWidth() + 
				0.8f * stage.getWidth() * (float) Math.random()));
			
		y = ((0.01f * stage.getHeight() + 
				0.8f * stage.getHeight() * (float) Math.random()));
		}catch(Exception e){
			
		}
		
		
		Random randomGenerator = new Random();
		int face = randomGenerator.nextInt(3 - 1 + 1) + 1;
		
		RemsBallActor ball = new RemsBallActor(x, y, face);
		ball.setVelocidad(-300, 300);
		stage.addActor(ball);
	}

	@Override
	public void resize(int width, int height) {
//		stage.setViewport(width, height, true);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
	}

}
