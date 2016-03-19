package alienKiller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	public static final int ID=1;
	boolean instructions=true;
	int score;
	Alien[] aliens=new Alien[9];
	
	
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
		
		
	}
	@Override
	public void update(GameContainer app, StateBasedGame game, int delta) throws SlickException {
		
		
	}
	@Override
	public int getID() {
		return ID;
	}
	

}
