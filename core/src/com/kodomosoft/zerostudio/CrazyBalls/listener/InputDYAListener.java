package com.kodomosoft.zerostudio.CrazyBalls.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.kodomosoft.zerostudio.CrazyBalls.actor.Ball;
import com.kodomosoft.zerostudio.CrazyBalls.actor.RemsBallActor;
import com.kodomosoft.zerostudio.CrazyBalls.screen.PlayScreen;

/**
 * InputDYAListener es una Clase que a���������ade un listener a los botones del menu
 * principal o a las pelotipas del Juego, segun sea el indice; siendo: 
 * -1: el listener para las pelotitas (las Destrulle) 
 * 0: Cambia a la Screen de Niveles
 * 1: Cambia a la Screen de Instrucciones (INSTRUCTIONS) 
 * 2: Sale de la Aplicacion (Boton Exit) 
 * 3: Cambia a la Screen del Menu Principal (MENU)
 * 4: Cambian a la Screen del Juego (GAME)
 * */

public class InputDYAListener extends InputListener {

	/****** VARIABLES DE INSTANCIA ******/
	private CrazyBalls game;
	private int selector;
	private RemsBallActor ball;
	private Ball ball2;
	private int ballNumber=0;
	private Actor btn;
	private Sound wavSound;
	private int countRemoveBalls = 0;
	//private int rulesArray = 0;

	/**********************************/
	
	public InputDYAListener(Ball ball, int slc, int _ballNumber,
			CrazyBalls game) {
		this.ball2 = ball;
		this.selector = slc;
		this.ballNumber = _ballNumber;
		this.game = game;
		wavSound = CrazyBalls.MANAGER
				.get("Pickup_remBall.wav", Sound.class);
	}

	/**
	 * Constructor cuando es una pelota
	 * @param ball, es un objeto de tipo RemsBallActor
	 * @param slc, es un selector si es ball o button?
	 * -1 es para las ball
	 * 0 es para mandar a la pantalla de niveles
	 * 1 es para mandar a la pantalla de instrucciones
	 * 2 es para salir del juego
	 * 3 es para mandar a la pantalla menu
	 * 4 es para mandar a la pantalla de juego
	 * @param _ballNumber, es para verificar que las bolas se recojan en orden
	 * toma valores entre 1 & 3 segun el face de la ball asignada.
	 * @param game, es el game en uso (objeto de CrazyBallsMain), usado para setar las pantallas
	 * */
	public InputDYAListener(RemsBallActor ball, int slc, int _ballNumber,
			CrazyBalls game) {
		this.ball = ball;
		this.selector = slc;
		this.ballNumber = _ballNumber;
		this.game = game;
		wavSound = CrazyBalls.MANAGER
				.get("Pickup_remBall.wav", Sound.class);
	}

	/**********************************/

	/** Constructor Cuando es un Boton del Menu Principal (Actor)*/
	public InputDYAListener(Actor btn, CrazyBalls game, int slc) {
		this.btn = btn;
		this.game = game;
		this.selector = slc;
	}

	@Override
	public boolean touchDown(InputEvent e, float x, float y, int pointer,
			int button) {
		if (selector != -1 || selector == 4) {
			this.btn.setColor(1f, 0f, 0f, 0.5f);
		} else {
			this.ball.setColor(1f, 0f, 0f, 0.5f);
			
			wavSound.play();
			ball.remove();
			countRemoveBalls++; // acumulo las pelotas que se
													// van quitando

			String comprension = CrazyBalls.levelRules[game.getLevel()-1];
			String amor[] = comprension.split(",");

			System.out.print(Integer.parseInt(amor[CrazyBalls.rulesArray]));
			System.out.print(CrazyBalls.rulesArray);
			System.out.print(selector);
			if (Integer.parseInt(amor[CrazyBalls.rulesArray]) == ballNumber) {
				if (CrazyBalls.rulesArray == (amor.length - 1)) {
					if(PlayScreen.time.getSeg()==CrazyBalls.SecondsLimits[game.getLevel()-1] && PlayScreen.time.getMil()==0){
						unLook();
					}else if(PlayScreen.time.getSeg()<CrazyBalls.SecondsLimits[game.getLevel()-1]){
						unLook();
					}
					CrazyBalls.rulesArray = 0;
					game.setScreen(game.LEVELS);
				}
				CrazyBalls.rulesArray++;
			} else {
				game.setScreen(new PlayScreen(game));
			}
		}
		return true;
	}

	private void unLook() {
		CrazyBalls.prefs.putBoolean("Level"
				+ (CrazyBalls.levelx + 1), true);
		CrazyBalls.prefs.flush();
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		switch (selector) {

		case 0:
			game.setScreen(game.LEVELS);
			break;
		case 1:
			game.setScreen(game.INSTRUCTIONS);
			break;
		case 2:
			Gdx.app.exit();
			break;
		case 3:
			game.setScreen(game.MENU);
			break;
		case 4:
			game.setLevel(CrazyBalls.levelx);
			game.setScreen(game.GAME);
			break;
		}
		super.touchUp(event, x, y, pointer, button);
	}
}