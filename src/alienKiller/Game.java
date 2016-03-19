package alienKiller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	public static final int ID=1;
	boolean instructions=true;
	int score=0;
	Alien[] aliens=new Alien[9];
	AlienGen generator;
	
	public Game(){
		super();
	}
	
	@Override
	public void init(GameContainer app, StateBasedGame game) throws SlickException {
		try{
			for(int a=0;a<9;a++){
				aliens[a]=new Alien(a,app);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		generator=new AlienGen(aliens);
		
	}
	@Override
	public void render(GameContainer app, StateBasedGame game, Graphics g) throws SlickException {
		if(instructions){
			g.drawString("Click the Aliens to zap them as they appear. Zap the most aliens to win \n Hit Enter to begin", app.getWidth()/2,app.getHeight()/2);
			
		}
		for(Alien a:aliens){
			a.blackhole.draw(a.getXCoordinate(), a.getYCoordinate());
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