package com.kodomosoft.zerostudio.CrazyBalls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.kodomosoft.zerostudio.CrazyBalls.actor.RemsBallActor;

public class SecuenceScreen extends AbstractScreen {

	private Stage stage;
	private int cont;
	private RemsBallActor[] Balls;
	
	private int he = 0;
	private int ii = 0;
	private int w = 105;
	private int limit = 4;
	private int rpw = 40;
	private int rph = 450;

	public SecuenceScreen(CrazyBalls game) {
		super(game);
		
	}
	
	@Override
	public void show() {
		//Inicializa el Stage y lo asigna a la Entrada
		stage = new Stage();//400, 800, true, game.getSpriteBatch());
		Gdx.input.setInputProcessor(stage);
		
		cont=0;
		
		//Tomamos el orden del level de game.levelRules, lo partimos
		//y los asignamos al arreglo temporal de String "temp[]"
		String temp[] = CrazyBalls.levelRules[game.getLevel()-1].split(","); // le indicamos que haga el corte en cada ','
		
		Balls = new RemsBallActor[temp.length];
		
		inicialiceSecuence(temp);
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
		delay(1, game.getLevel());
		if(cont>0){
			game.setScreen(game.GAME);
		}
		
	}
	
	@Override
	public void resize(int width, int height) {
//		stage.setViewport(400, 800, true);
	}
	
	@Override
	public void hide() {
		super.hide();
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
	}
	
	private void delay(final int indice, double seg) {
		float delay = (float) seg; // seconds

		Timer.schedule(new Task() {
			@Override
			public void run() {
				// Do your work
				cont = indice;
			}
		}, delay);
	}
	
	private void inicialiceSecuence(String[] temp) {
		for (int i = 0; i < Balls.length; i++) {
			
			int wi = ii * w; //105
			
			if(wi == w*limit){ //315
				he += (w+3);
				wi = 0;
				ii = 0;
			}
			
			Balls[i] = new RemsBallActor(rpw + wi, rph - he, Integer.parseInt(temp[i]));
			Balls[i].setVelocidad(0, 0);
			stage.addActor(Balls[i]);
			
			ii++;
		
		}
		he=0; ii=0;
	}

}
