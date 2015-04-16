package com.kodomosoft.zerostudio.CrazyBalls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.kodomosoft.zerostudio.CrazyBalls.actor.RemsBallActor;
import com.kodomosoft.zerostudio.CrazyBalls.actor.TimerActor;
import com.kodomosoft.zerostudio.CrazyBalls.listener.InputDYAListener;

public class PlayScreen extends AbstractScreen {
	
	private Stage stage;
	private float 	x = 0,
					y = 0;
	private int cantidad = 5,
				contador = 0;
	private Image backButton, retryBtn, level1Title;
	
	public static TimerActor time;
	
	private int cont = 0;
	private String LevelActual;
	private String BallsToLevels[];

	public PlayScreen(CrazyBalls game) {
		super(game);
		
	}

	@Override
	public void show() {
		stage =  new Stage();//400, 800, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		this.LevelActual = CrazyBalls.levelRules[game.getLevel()-1];
		String BallsToLevels[] = this.LevelActual.split(",");
		this.BallsToLevels=BallsToLevels;
		cont=0;
		CrazyBalls.rulesArray=0;
		
		//Crear Fondo
		Texture txt = CrazyBalls.MANAGER.get("playScreen.png", Texture.class);
		txt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion txtr = new TextureRegion(txt, 480, 800);
		Image img = new Image(txtr);
		stage.addActor(img);

		Texture bck = CrazyBalls.MANAGER.get("levelsBack.png", Texture.class);
		bck.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion bck1 = new TextureRegion(bck, 67, 51);
		backButton = new Image(bck1);
		backButton.addListener(new InputDYAListener(backButton, game, 0));
		backButton.setPosition(380, 20);
		stage.addActor(backButton);
		
		Texture ret = CrazyBalls.MANAGER.get("retry.png", Texture.class);
		ret.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion ret1 = new TextureRegion(ret, 61, 61);
		
		retryBtn = new Image(ret1);
		retryBtn.addListener(new InputDYAListener(retryBtn, game, 4));
		retryBtn.setPosition(20, 20);
		stage.addActor(retryBtn);
		
		if (game.getLevel()>10)
			cantidad = 15;
		else if (game.getLevel()>8)
			cantidad = 13;
		else if (game.getLevel()>6)
			cantidad = 11;
		else if (game.getLevel()>4)
			cantidad = 9;
		else if (game.getLevel()>2)
			cantidad = 7;
		else if (game.getLevel()>0)
			cantidad = 6;
		
		String varLevel = "level"+game.getLevel()+".png";
		Texture level = CrazyBalls.MANAGER.get(varLevel, Texture.class);
		level.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion level1 = new TextureRegion(level, 60, 52);
		level1Title = new Image(level1);
		level1Title.setPosition(145, 720);
		stage.addActor(level1Title);
		
		
		CrazyBalls.countBallslevel = cantidad;
		CrazyBalls.levelx = game.getLevel();
		
		// Aniadimos el Timer
		time = new TimerActor(new BitmapFont(Gdx.files.internal("fonts/ArialBlack.fnt")));
		time.setPosition(stage.getWidth()/2*1.3f, stage.getHeight()/2*1.9f);
		stage.addActor(time);
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		
		if(contador<cantidad){
			createBall();
		}
		
		stage.draw();
	}

	/**
	 * Crea nuevas pelotas
	 * y asignarlas al esenario en una posicion aleatoria.
	 * @author zerokull
	 */
	private void createBall(){
		
		try{
		x = ((0.01f * stage.getWidth() + 
				0.8f * stage.getWidth() * (float) Math.random()));
			
		y = ((0.01f * stage.getHeight() + 
				0.8f * stage.getHeight() * (float) Math.random()));
		}catch(Exception e){
			
		}
		if(y>stage.getHeight()-100){
			y -= 110;
		}else if(y<100){
			y += 110;
		}
		
		int face = generateFaceBall();
//		if(face>0){
			RemsBallActor ball = new RemsBallActor(x, y, face);
			ball.setVelocidad(-300, 300);
			stage.addActor(ball);
			ball.addListener(new InputDYAListener(ball, -1, face, game));
			contador++;
//		}
	}
	
	/**
	 * Metodo que genera almenos las pelotas que se debes atrapar y algunas mas.
	 * @author zerokull
	 * @return face, face es el indice que clasifica los tres tipos de pelota.
	 */
	private int generateFaceBall(){
		int face = CrazyBalls.genRandom(3, 1);
		if(cont<BallsToLevels.length){
			face = Integer.parseInt(BallsToLevels[cont]);
			cont++;
			return face;
		}else{
			return face;
		}
	}

	@Override
	public void resize(int width, int height) {
//		stage.setViewport(400, 800, true);
		
		time.setPosition(stage.getWidth()/2*1.3f, stage.getHeight()/2*1.9f);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		this.cont=0;
		this.contador=0;
	}

	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
	}

}
