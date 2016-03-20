package boardPlay;

import java.util.Random;

import org.newdawn.slick.*;

public class Dice {
	Random ran;
	Animation roll;
	int sides;
	int currentValue;
	SpriteSheet faces;
	
	public Dice(Image[] die, Image sprites){
		ran=new Random();
		sides=6;
		roll=new Animation(die,50, true);
		faces=new SpriteSheet(sprites,50,50);
	}
	
	public int getCurrentValue(){return currentValue;}
	public Animation getAnimation(){return roll;}
	
	public Animation rollDice(){
		Animation x=roll;
		currentValue=ran.nextInt(5)+1;
		x.addFrame(faces.getSprite(currentValue-1, 0), 100);	
		return x;
	}
	
	

}
