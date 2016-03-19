package minigames.alienKiller;

import java.awt.Font;

import org.newdawn.slick.*;


public class Game extends BasicGame implements Runnable{
	public static final int ID=1;
	boolean instructions=true;
	int score=0;
	Alien[] aliens;
	AlienGen generator;
	boolean gameOver=false;
	String[] finalScores;
	
	public Game(String name){
		super(name);
	}
	@Override
	public void run() {
		try {
			AppGameContainer minigame=new AppGameContainer(new Game("Alien Exterminator"));
			minigame.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void init(GameContainer app) throws SlickException {
		aliens = new Alien[9];
		try{
			for(int a=0;a<9;a++){
				aliens[a]=new Alien(a,app);
			}
		} catch(Exception e){e.printStackTrace();}
		generator=new AlienGen(aliens);	
	}
	
	public void render(GameContainer app, Graphics g) throws SlickException {
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
		
		if(gameOver){
			String done="Game Over";
			int index=0;
			for(String fin:finalScores){
				font.drawString(app.getWidth()/2-font.getWidth(fin), app.getHeight()+(index*font.getHeight(fin)),fin);
				index++;
			}
			g.setColor(Color.green);
			font.drawString(app.getWidth()/2-font.getWidth(done),font.getHeight()+5, done);
			String back="Press enter when you're ready to continue.";
			font.drawString(app.getWidth()/2-font.getWidth(back), 2*font.getHeight()+10, back);
		}

		for(Alien a:aliens){
			Image swirl=a.blackhole;
			swirl.draw(a.getXCoordinate(), a.getYCoordinate());
			if(a.getVisible()&&!a.getHit()){
				a.getAvatar().draw(a.getXCoordinate(), a.getYCoordinate());
			}
		}
		g.drawString(Integer.toString(score), 0, 0);
	}

	public void update(GameContainer app, int delta) throws SlickException {
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
					if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
						a.setHit(true);
						a.setVisible(false);
						score+=5;
					}
				}
			}
			if(generator.getElapsedTime()>=1*60*1000){
				giveScore();
				gameOver=true;
				
			}
		}
		
		
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
	public int giveScore(){return score;}
	public String[] finalScores(){
		finalScores=new String[4];
		
		return finalScores;
	}


}
