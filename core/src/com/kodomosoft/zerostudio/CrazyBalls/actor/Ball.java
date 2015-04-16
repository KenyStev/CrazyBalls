package com.kodomosoft.zerostudio.CrazyBalls.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;
import com.utils.BodyEditorLoader;

public class Ball extends Sprite {
	
	private Texture ball;
	private TextureRegion Ball;
	
	/*Implementation of Box2D*/
	World world;
	Body body;
	Fixture fixture;
	/*DONE*/
	
	//Variables Mover Ball
	private Vector2 velocidad = new Vector2(500,500);

	public Ball(World w, float x, float y, int face) {
		world = w;
		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("bodies/balls-crazy.json"));
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 5;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.5f;
		
//		
//		CircleShape circle = new CircleShape();
//		circle.setRadius(5f);
//		
//		fixtureDef.shape = circle;
		body = world.createBody(bodyDef);
		
		
//		fixture = body.createFixture(fixtureDef);
//		fixture.setSensor(false);
//		fixture.setUserData(this);
//		circle.dispose();
		
		//Estas Variables son para 
		int widt = 75; 
		int hei = 67;
		
		if(face == 3)
		{
			widt = 82;
			hei = 63;
		}else if(face == 2)
		{
			widt = 89;
			hei =59;
		}
			
		String ruta = "face"+face+".png";
		System.out.print(ruta);
		ball = CrazyBalls.MANAGER.get(ruta, Texture.class);
		Ball = new TextureRegion(ball, widt, hei);
		setSize(Ball.getRegionWidth(), Ball.getRegionHeight());
		setPosition(x, y);
		
		loader.attachFixture(body, "face"+face, fixtureDef, widt);
		System.out.println("Paso del loader wow!!");
	}
	/*
	@Override
	public void act(float delta) {
		super.act(delta);
		
		moveBy(velocidad.x * delta, velocidad.y * delta);
		checkPosition();
	}*/
	
	//metodo para setar la velocidad
	public void setVelocidad(int x, int y){
		velocidad.set(x, y);
	}
	
	public Body getBody()
	{
		return body;
	}
	/*
	//metodo para comprobar que la bola o salga de los limites de la pantalla (Rebote)
	private void checkPosition(){
		if(getX()<1){
			velocidad.x *= -1;
		}else if(getRight() > (getStage().getWidth())-1){
			velocidad.x *= -1;
		}
		
		if(getY()<100){
			velocidad.y *= -1;
		}else if(getTop() > (getStage().getHeight())-100){
			velocidad.y *= -1;
		}
	}*/
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(Ball, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
