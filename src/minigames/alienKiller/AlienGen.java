package minigames.alienKiller;

import java.util.Date;
import java.util.Random;

public class AlienGen implements Runnable {
	long startTime = System.currentTimeMillis();
	long elapsedTime = 0L;
	long loopStart=System.currentTimeMillis();
	long loopTime=0L;
	int num;
	Alien[] aliens;
	
	public AlienGen(Alien[] aliens){
		this.aliens=aliens;
	}
	public long getElapsedTime(){return elapsedTime;}


	@Override
	public void run() {
		while(elapsedTime<1*60*1000){
			elapsedTime=(new Date()).getTime()-startTime;
			Random ran=new Random();
			while (loopTime < 50) {
			    loopTime = (new Date()).getTime() - loopStart;
			}
			if(loopTime==50){
				num=ran.nextInt(9);
				if(aliens[num].getVisible()==false){
					aliens[num].setVisible(true);
				}
				loopTime=0;
				loopStart=System.currentTimeMillis();
			}	
		}	
	}

}
