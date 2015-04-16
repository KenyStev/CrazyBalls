package com.kodomosoft.zerostudio.CrazyBalls.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;

public class RemsBallActor extends Actor {
	
	private Texture ball;
	private TextureRegion Ball;
	
	//Variables Mover Ball
	private Vector2 velocidad = new Vector2(500,500);

	public RemsBallActor(float x, float y, int face) {
		
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
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		moveBy(velocidad.x * delta, velocidad.y * delta);
		checkPosition();
	}
	
	//metodo para setar la velocidad
	public void setVelocidad(int x, int y){
		velocidad.set(x, y);
	}
	
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
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(Ball, getX(), getY(), getOriginX(), getOriginY(), 
				getWidth(), getHeight(), getScaleX(), getScaleY(), 
				getRotation());
	}

}
