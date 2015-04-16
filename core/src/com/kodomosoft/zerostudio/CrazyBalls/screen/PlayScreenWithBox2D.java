package com.kodomosoft.zerostudio.CrazyBalls.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.kodomosoft.zerostudio.CrazyBalls.actor.Ball;

public class PlayScreenWithBox2D extends AbstractScreen {
	
	/**/
	Box2DDebugRenderer renderer;
	OrthographicCamera camera;
	Body body_ground;
	Fixture fixture_ground;
	World world;
	SpriteBatch batch;
	Ball ball;
	
	private final float TIMESTEP = 1 / 60f;
	private final int VELOCITYITERATION = 6, POSITIONITERATION = 2;
	private float gravity = -12;
	/*DONE*/
	/*
	private Stage stage;
	private float 	x = 0,
					y = 0;
	private int cantidad = 5,
				contador = 0;
	private Image backButton, retryBtn, level1Title;
	
	public static TimerActor time;
	
	private int cont = 0;
	private String LevelActual;
	private String BallsToLevels[];*/

	public PlayScreenWithBox2D(CrazyBalls game) {
		super(game);
		
		
	}

	@Override
	public void show() {
		/**/
		batch = game.getSpriteBatch();
		
		renderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(400,800);
		world = new World(new Vector2(0, gravity), true);

		createGround();
		
		ball = new Ball(world, 200, 200, 1);
		/*DONE*/
		/*stage =  new Stage(400, 800, true, batch);
		Gdx.input.setInputProcessor(stage);
		
		this.LevelActual = CrazyBallsMain.levelRules[game.getLevel()-1];
		String BallsToLevels[] = this.LevelActual.split(",");
		this.BallsToLevels=BallsToLevels;
		cont=0;
		CrazyBallsMain.rulesArray=0;
		
		//Crear Fondo
		Texture txt = CrazyBallsMain.MANAGER.get("playScreen.png", Texture.class);
		txt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion txtr = new TextureRegion(txt, 480, 800);
		Image img = new Image(txtr);
		stage.addActor(img);

		Texture bck = CrazyBallsMain.MANAGER.get("levelsBack.png", Texture.class);
		bck.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion bck1 = new TextureRegion(bck, 67, 51);
		backButton = new Image(bck1);
		backButton.addListener(new InputDYAListener(backButton, game, 0));
		backButton.setPosition(380, 20);
		stage.addActor(backButton);
		
		Texture ret = CrazyBallsMain.MANAGER.get("retry.png", Texture.class);
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
		Texture level = CrazyBallsMain.MANAGER.get(varLevel, Texture.class);
		level.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion level1 = new TextureRegion(level, 60, 52);
		level1Title = new Image(level1);
		level1Title.setPosition(145, 720);
		stage.addActor(level1Title);
		
		
		CrazyBallsMain.countBallslevel = cantidad;
		CrazyBallsMain.levelx = game.getLevel();
		
		// Aniadimos el Timer
		time = new TimerActor(new BitmapFont(Gdx.files.internal("fonts/ArialBlack.fnt")));
		time.setPosition(stage.getWidth()/2*1.3f, stage.getHeight()/2*1.9f);
		stage.addActor(time);*/
		
	}

	private void createGround() {
		FixtureDef fDef = new FixtureDef();
		BodyDef bodyDef = new BodyDef();
		fDef.restitution = 0.5f;
		fDef.density = 1;
		fDef.friction = 0.5f;

		ChainShape shape = new ChainShape();

		Vector2[] vectors = new Vector2[5];

		vectors[0] = new Vector2(0, 100);
		vectors[1] = new Vector2(game.width, 100);
		vectors[2] = new Vector2(game.width, game.height-100);
		vectors[3] = new Vector2(0, game.height-100);
		vectors[4] = new Vector2(0, 100);
		shape.createChain(vectors);
		fDef.shape = shape;

		bodyDef.position.x = 2;
		bodyDef.position.y = 2;
		bodyDef.type = BodyType.StaticBody;

		body_ground = world.createBody(bodyDef);
		System.out.println(body_ground + "Check bodydef");
		System.out.println(fDef + "   check fdef");

		fixture_ground = body_ground.createFixture(fDef);
		shape.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		world.step(TIMESTEP, VELOCITYITERATION, POSITIONITERATION);
		
		batch.begin();
		/*
		if(contador<cantidad){
			createBall();
		}*/
		
		camera.setToOrtho(false, 400, 800);
		batch.setProjectionMatrix(camera.combined);
		
		batch.end();
		renderer.render(world, camera.combined);
	}

	/**
	 * Crea nuevas pelotas
	 * y asignarlas al esenario en una posicion aleatoria.
	 * @author zerokull
	 */
	private void createBall(){
		/*
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
			Ball ball = new Ball(world, x, y, face);
			ball.setVelocidad(-300, 300);
			ball.getBody().applyForce(1000, 1000,
					ball.getBody().getLocalCenter().x, 
					ball.getBody().getLocalCenter().y, true);
			stage.addActor(ball);
			ball.addListener(new InputDYAListener(ball, -1, face, game));
			contador++;
//		}*/
	}
	
	/**
	 * Metodo que genera almenos las pelotas que se debes atrapar y algunas mas.
	 * @author zerokull
	 * @return face, face es el indice que clasifica los tres tipos de pelota.
	 */
	private int generateFaceBall(){
		/*int face = CrazyBallsMain.genRandom(3, 1);
		if(cont<BallsToLevels.length){
			face = Integer.parseInt(BallsToLevels[cont]);
			cont++;
			return face;
		}else{
			return face;
		}*/
		return 0;
	}

	@Override
	public void resize(int width, int height) {
		//stage.setViewport(400, 800, true);
		
		//time.setPosition(stage.getWidth()/2*1.3f, stage.getHeight()/2*1.9f);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		//this.cont=0;
		//this.contador=0;
	}

	@Override
	public void dispose() {
		super.dispose();
		//stage.dispose();
	}

}
