package boardPlay;

import java.io.*;
import java.util.Hashtable;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;

public class Nodes {
	int id;
	int xCoordinate;
	int yCoordinate;
	int[] connected;
	public Nodes(int i, int x, int y, int[]connect){
		id=i;
		xCoordinate=x*64-32;
		yCoordinate=y*64-32;
		connected=connect;
	}
	public static Hashtable<Integer,Nodes> getConnections(File board) throws IOException{
		BufferedReader read=new BufferedReader(new FileReader(board)); 
		String line=read.readLine();
		Hashtable<Integer,Nodes> map=new Hashtable<Integer,Nodes>();
		while(line!=null){
			String[] make=line.split(",");
			int id=Integer.parseInt(make[0]);
			int x=Integer.parseInt(make[1]);
			int y=Integer.parseInt(make[2]);
			int[] connection=new int[make.length-3];
			for(int a=3;a<=make.length-1;a++){
				connection[a-3]=Integer.parseInt(make[a]);
			}
			Nodes a=new Nodes(id,x,y,connection);
			map.put(a.id, a);
			line=read.readLine();
		}
		read.close();
		return map;
	}
	public void getPath(Hashtable<Integer,Nodes> map,int roll,Pieces piece, GameContainer game) throws SlickException{
		Input input=game.getInput();
		for(int a=1;a<=roll;a++){
			Nodes point=map.get(piece.getCurrentTile());
			if(point.connected.length==1){
				piece.setCurrentTile(point.connected[0]);
			}
			else if(point.connected.length==2){
				int x=input.getMouseX();
				int y=input.getMouseY();
				Nodes first=map.get(point.connected[0]);
				MouseOverArea option1=new MouseOverArea(game,new Image("\\Assets\\Cloud1.png"),new Rectangle(first.xCoordinate-32,first.yCoordinate-32,64,64));
				Nodes second=map.get(point.connected[1]);
				MouseOverArea option2=new MouseOverArea(game,new Image("\\Assets\\Cloud1.png"),new Rectangle(second.xCoordinate-32,second.yCoordinate-32,64,64));
				if(option1.isMouseOver()){
					if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
						piece.setCurrentTile(point.connected[0]);
					}
				}
				if(option2.isMouseOver()){
					if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
						piece.setCurrentTile(point.connected[1]);
					}
				}
			}
			
		}
	}
}
