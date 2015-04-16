package com.kodomosoft.zerostudio.CrazyBalls.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.kodomosoft.zerostudio.CrazyBalls.actor.ButtonImage;
import com.kodomosoft.zerostudio.CrazyBalls.screen.SecuenceScreen;

public class LevelsListener extends InputListener {

	/*VARIABLES DE INSTANCIA*/
	private ButtonImage button;
	private CrazyBalls game;
	
	/*CONSTRUCTOR DEL LISTENER PARA LOS BOTONES DEL MENU DE NIVELES*/
	public LevelsListener(ButtonImage imgl1,int Level, CrazyBalls game) {
		this.button=imgl1;
		this.game=game;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		this.button.setColor(1f, 1f, 1f, 0.5f);
		game.setLevel(this.button.getLevel());
		CrazyBalls.levelRules[game.getLevel()-1] = asigneLevelRule();
//		System.out.println(game.levelRules[game.getLevel()-1]);
		
		return true;
		
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		game.setScreen(new SecuenceScreen(game));
		
	}
	
	/**
	 * Crea una Secuencia aleatoria de numeros que son las reglas del level
	 * y lo devuelve todo como un String al arreglo levelRules de la clase principal.
	 * @author zerokull
	 * @return secuence, seceunce es la secuencia aleatoria que genero para el nivel designado.
	 */
	private String asigneLevelRule() {
		String secuence = new String();
		int x = game.getLevel();
		
		//Segun el nivel en que estemos asigna la cantidad de pelotas en la secuencia
		if(x>10)
			x=10;
		else if(x>8)
			x=9;
		else if(x>6)
			x=8;
		else if(x>4)
			x=7;
		else if(x>2)
			x=5;
		else if(x>0)
			x=3;
		
		for (int i = 0; i < x; i++) {
			secuence += CrazyBalls.genRandom(3,1);
			if (i < x-1)
				secuence += ",";
		}
		System.out.println("\n"+secuence);
		return secuence;
	}

}
