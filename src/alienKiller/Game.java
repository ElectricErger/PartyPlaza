package alienKiller;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	public static final int ID=1;
	boolean instructions=true;
	int score=0;
	Alien[] aliens;
	AlienGen generator;
	Image swirl;
	
	public Game(){
		super();
	}
	
	@Override
	public void init(GameContainer app, StateBasedGame game) throws SlickException {
		aliens = new Alien[9];
		try{
			for(int a=0;a<9;a++){
				aliens[a]=new Alien(a,app);
				swirl=aliens[a].blackhole;
			}
		} catch(Exception e){e.printStackTrace();}
		generator=new AlienGen(aliens);	
	}
	
	@Override
	public void render(GameContainer app, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		TrueTypeFont font=new TrueTypeFont(new Font("Arial",Font.PLAIN,40), false);
		if(instructions){
			String inst="Click the Aliens to zap them as they appear.";
			String win="Zap the most aliens to win.";
			String start="Hit Enter to begin.";
			font.drawString(app.getWidth()/2-(font.getWidth(inst)/2), app.getHeight()/2-font.getHeight(inst)-10, inst);	
			font.drawString(app.getWidth()/2-(font.getWidth(win)/2), app.getHeight()/2, win);
			font.drawString(app.getWidth()/2-(font.getWidth(start)/2), app.getHeight()/2+10+font.getHeight(start), start);
		}
		
		
		System.out.println(aliens[0]);
		for(Alien a:aliens){
			Image swirl=a.blackhole;
			swirl.draw(a.getXCoordinate(), a.getYCoordinate());
			if(a.getVisible()&&!a.getHit()){
				a.getAvatar().draw(a.getXCoordinate(), a.getYCoordinate());
			}
		}
		g.drawString(Integer.toString(score), 0, 0);
		
		
	}
	@Override
	public void update(GameContainer app, StateBasedGame game, int delta) throws SlickException {
		Input input=app.getInput();
		if(input.isKeyPressed(Input.KEY_ENTER)){
			instructions=false;
		}
		while(!instructions){
			int x=input.getMouseX();
			int y=input.getMouseY();
			for(Alien a:aliens){
				boolean hit=containsMouse(x,y,a);
				if(hit){
					a.setHit(true);
					a.setVisible(false);
					score+=5;
				}
			}
		}
		
		
	}
	@Override
	public int getID() {
		return ID;
	}
	public boolean containsMouse(int x, int y, Alien alien){
		Image av=alien.getAvatar();
		int width=av.getWidth()/2;
		int height=av.getHeight()/2;
		int centreX=Math.round(av.getCenterOfRotationX());
		int centreY=Math.round(av.getCenterOfRotationY());
		if(centreX-width<x&&centreX+width>x){
			if(centreY-height<x&&centreY+height>x){
				return true;
			}
			else return false;
		}
		else return false;
		
	}

}
