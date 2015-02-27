//UIUC CS125 FALL 2014 MP. File: RainGame.java, CS125 Project: PairProgramming, Version: 2015-02-23T21:30:19-0600.400781264
/**
 * @author afolta2, ADD NET ID HERE replace-this-with-your-netids-on-this-line-here-with-a-comma-between-them
 */
public class RainGame {

	public static void main(String[] args) {
		// To get points type your netids above (CORRECTLY-Please double check your partner correctly spells your netid correctly!!)
		// Your netid is the unique part of your @illinois email address
		// Do not put your name or your UIN. 
		// REMEMBER TO COMMIT this file...
	
		int x=0, y=0, dx=0, dy=0, score = 0;
		String text = "";
		long startTime =System.currentTimeMillis();
		//long abs_startTime=System.currentTimeMillis();											//I was having trouble with this so i just made																				
		int level=0;																				//the score increase once per turn
		
		
		//start screen
		Zen.setColor(0, 0, 0);																		//background for start screen and highscores (fake)
		Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());									
		Zen.setColor(0, 255, 0);
		Zen.drawText("ENTER STARTING LEVEL (0-99): __ ",Zen.getZenHeight()/3,Zen.getZenHeight()/2);	//write instructions, q to quit? enter starting level?
																									//how to get int?

		Zen.sleep(2000);// sleep for 2s
		
		Zen.setFont("Helvetica-40");
		while (Zen.isRunning()) {

			if (text.length() == 0) {
				y = (int)(Math.random()*(7*Zen.getZenHeight()/8-35) + Zen.getZenHeight()/8+35);		//random starting y point													
				x = (int)(Math.random()*(Zen.getZenWidth()-70));									//more visible when starting
				dx = (int)((Math.random()*2-1)*level);  											//random velocity, to increase difficulty (+/-)
				dy = (int)((Math.random()*2-1)*level);   											//velocity increases with level increase, starts at 0
				text = "" + (int) (Math.random() * 999);
				long elapsed = System.currentTimeMillis() - startTime;
				startTime = System.currentTimeMillis();
				score += (5000-elapsed)/1000;														//score based on how quickly answered, can be negative
				level++;																			//level increases with time
			}
			Zen.setColor(125, 38, 205);																//background for score and level
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
			
			Zen.setColor(54, 45, 188);																//not bright pink background
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight()/8);

			Zen.setColor(0, 255, 0);
			Zen.drawText(text, x, y);
			
			Zen.drawText("Level: "+level,10,50);													//better visibility
			Zen.drawText("Score: "+score,400,50);													//score and level take up less space
			if (score<0){
				Zen.drawText("GAMEOVER",Zen.getZenHeight()/3 ,Zen.getZenHeight()/2 );				//gameover if score falls below zero
				break;																				//game finishes
			}
			
			x += dx;
			if (x+dx<0) 																				//the following makes the numbers bounce around the screen
				dx = -dx;
			else if (x+dx>(Zen.getZenWidth()-70))
				dx = -dx;
			y += dy;
			if (y+dy<Zen.getZenHeight()/8+35)
				dy = -dy;
			else if (y+dy>(7*Zen.getZenHeight()/8-35) + Zen.getZenHeight()/8+35) 
				dy = -dy;
			
			// Find out what keys the user has been pressing.
			String user = Zen.getEditText();
			// Reset the keyboard input to an empty string
			// So next iteration we will only get the most recently pressed keys.
			Zen.setEditText("");
			
			for(int i=0;i < user.length();i++) {
				char c = user.charAt(i);
				if(c == text.charAt(0))
					text = text.substring(1,text.length()); // all except first character
			}
			
			Zen.sleep(90);// sleep for 90 milliseconds

		}
	}

}
