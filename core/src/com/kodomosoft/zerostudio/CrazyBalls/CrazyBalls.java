package com.kodomosoft.zerostudio.CrazyBalls;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kodomosoft.zerostudio.CrazyBalls.screen.AbstractScreen;
import com.kodomosoft.zerostudio.CrazyBalls.screen.Instructions;
import com.kodomosoft.zerostudio.CrazyBalls.screen.LevelScreen;
import com.kodomosoft.zerostudio.CrazyBalls.screen.MainScreen;
import com.kodomosoft.zerostudio.CrazyBalls.screen.PlayScreen;
import com.kodomosoft.zerostudio.CrazyBalls.screen.SecuenceScreen;

/**
 * Esta es la clase Principal o el Game; de aqui e de donde tomamos todo lo
 * necesario para nustro juego
 * */
public class CrazyBalls extends Game {

	/**************** VARIABLES DE INSTANCIA ****************/
	public static AssetManager MANAGER;// = new AssetManager();
	private SpriteBatch batch;
	public final AbstractScreen GAME, LEVELS, SECUENCE, MENU, INSTRUCTIONS;
	private int Level = 0;
	public static Preferences prefs;
	public static int countBallslevel;
	public static int levelx;
	public static int rulesArray = 0;
	public static int[] SecondsLimits = new int[12];
	// public static Sound wavSound, menuSong;

	public static String levelRules[] = new String[12];
	public float width, height;

	/******************************************************/

	/* CONSTRUCTOR DE LA CLASE PRINCIPAL (EL GAME) */
	public CrazyBalls() {
		GAME = new PlayScreen(this);
		LEVELS = new LevelScreen(this);
		SECUENCE = new SecuenceScreen(this);
		MENU = new MainScreen(this);
		INSTRUCTIONS = new Instructions(this);
	}

	void initPrefs() {
		prefs = Gdx.app.getPreferences("scores.txt");
		// prefs.clear();
	}

	@Override
	public void create() {
		initPrefs();
		initLevelsRules();

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		// Iniciamos nuestro Spritebatch, el que
		// vamos a usar en todo nuestro juego
		batch = new SpriteBatch();

		for (int j = 1; j <= 1; j++) {
			prefs.putBoolean("Level" + j, true);
		}
		prefs.flush();
		// Gdx.app.error("sds", "prefserror");

		// wavSound =
		// Gdx.audio.newSound(Gdx.files.internal("Pickup_remBall.wav"));
		// Sound menuSong =
		// Gdx.audio.newSound(Gdx.files.internal("background.ogg"));

		// Cargamos todos los recursos que nesesitaremos en el juego a
		// nuestro
		// AssetManager
		MANAGER = new AssetManager();
		MANAGER.load("playScreen.png", Texture.class);
		MANAGER.load("face1.png", Texture.class);
		MANAGER.load("face2.png", Texture.class);
		MANAGER.load("face3.png", Texture.class);
		MANAGER.load("btnPlay.png", Texture.class);
		MANAGER.load("face1.png", Texture.class);
		MANAGER.load("backButton.png", Texture.class);
		MANAGER.load("background.png", Texture.class);
		MANAGER.load("btnExit.png", Texture.class);
		MANAGER.load("btnOptions.png", Texture.class);
		MANAGER.load("levels.png", Texture.class);
		MANAGER.load("instructions.png", Texture.class);
		MANAGER.load("l1.png", Texture.class);
		MANAGER.load("l2.png", Texture.class);
		MANAGER.load("l3.png", Texture.class);
		MANAGER.load("l4.png", Texture.class);
		MANAGER.load("l5.png", Texture.class);
		MANAGER.load("l6.png", Texture.class);
		MANAGER.load("l7.png", Texture.class);
		MANAGER.load("l8.png", Texture.class);
		MANAGER.load("l9.png", Texture.class);
		MANAGER.load("l10.png", Texture.class);
		MANAGER.load("l11.png", Texture.class);
		MANAGER.load("l12.png", Texture.class);
		MANAGER.load("level1.png", Texture.class);
		MANAGER.load("level2.png", Texture.class);
		MANAGER.load("level3.png", Texture.class);
		MANAGER.load("level4.png", Texture.class);
		MANAGER.load("level5.png", Texture.class);
		MANAGER.load("level6.png", Texture.class);
		MANAGER.load("level7.png", Texture.class);
		MANAGER.load("level8.png", Texture.class);
		MANAGER.load("level9.png", Texture.class);
		MANAGER.load("level10.png", Texture.class);
		MANAGER.load("level11.png", Texture.class);
		MANAGER.load("level12.png", Texture.class);
		MANAGER.load("levelsBack.png", Texture.class);
		MANAGER.load("retry.png", Texture.class);
		MANAGER.load("Pickup_remBall.wav", Sound.class);
		MANAGER.load("background.ogg", Sound.class);

		while (!MANAGER.update()) {
			// Todo lo que sea
		}

		setScreen(MENU);

		// menuSong.play();
		// menuSong.loop();

	}

	private void initLevelsRules() {
		/******
		 * Bolas
		 * 
		 * Amarilla = 1 Azul = 2 Roja = 3
		 */

		// levelRules[0] = "1,2,3";
		// levelRules[1] = "1,1,3,2";
		// levelRules[2] = "1,2,2,3,3,2";
		// levelRules[3] = "2,3,1,3,3,3,1,2";
		// levelRules[4] = "1,3,1,3,2,3,3,2,2,3";
		// levelRules[5] = "3,1,2,3,1,2,3,3,3,3,1,1";
		// levelRules[6] = "3,2,2,2,1,1,1,2,2,3,3,2,3,2,1";
		// levelRules[7] = "3,3,2,2,2,2,2,3,3,3,1,1,1,1,2,2,2";
		// levelRules[8] = "#Level1:1,2,3";
		// levelRules[9] = "#Level1:1,2,3";
		// levelRules[10] = "#Level1:1,2,3";
		// levelRules[11] = "#Level1:1,2,3";

		String[] nums = "3,3,5,5,7,7,9,9,11,11,13,13".split(",");

		for (int i = 0; i < SecondsLimits.length; i++)
			SecondsLimits[i] = Integer.parseInt(nums[i]);
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		MANAGER.dispose();
	}

	/**
	 * Instancia el ivel Seleccionado por el Usuario segun el Boton Tocado
	 * 
	 * @this.Level=Level;
	 * */
	public void setLevel(int Level) {
		this.Level = Level;
	}

	/**
	 * Recupera el Nivel en el Que se encuentra el Juego, para poder setear las
	 * reglas necesarias de cada nivel y asi saber en que nivel estamos
	 * 
	 * @return Level;
	 * */
	public int getLevel() {
		return Level;
	}

	public int getBallLevel() {
		int balls;
		balls = this.Level * 5;
		return balls;
	}

	/**
	 * Recupera la instancia compartida de SpriteBatch
	 * 
	 * @return SpriteBatch en uso por el juego
	 */
	public SpriteBatch getSpriteBatch() {
		return this.batch;
	}

	/**
	 * Metodo que retorna un numero aleatorio entre min & max
	 * 
	 * @author zerokull
	 * @param max
	 *            el numero maximo del rango
	 * @param min
	 *            el numero minimo del rango
	 * @return (int)(Math.random()*max)+min
	 */
	public static int genRandom(int max, int min) {
		return (int) (Math.random() * max) + min;
	}

	// public void onBackPressed(){
	// // do something here and don't write super.onBackPressed()
	// }

	@Override
	public void pause() {
		super.pause();
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		super.resume();
		// TODO Auto-generated method stub

	}
}
