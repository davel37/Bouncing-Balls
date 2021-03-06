import java.awt.*;
import java.awt.color.*;
import java.applet.*;
import java.util.Random;

public class BounceTwo  extends Applet implements Runnable{
	Thread T = null;
	//Array to store ten colors.
	private static Color [] colors = {Color.RED, Color.ORANGE,Color.BLUE,Color.BLACK,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.PINK, Color.YELLOW,Color.GRAY};
	private int c = 0; //Count to count the colors.
	private Color ballColor[];
	private Ball balls[];
	private int width, height, NumBalls;
	//private Graphics graphic;  //For double buffering
	//private Image img;
	public void init(){
		this.width = getSize().width;
		this.height = getSize().height;
		this.NumBalls = Integer.parseInt(getParameter("Balls"));
		//double buffering attempt to avoid flickering!Not working as yet!May return if time allows!
		//img = createImage(getWidth(),getHeight());
		//graphic = img.getGraphics();
		//Create new Ball object to store the number of balls from the HTML file.
		     balls = new Ball[NumBalls];
		//New Color array to store the number of balls and assign a color.
             ballColor = new Color[NumBalls];
        //Loop through the number of balls.If counter passes 10, reset and start the counter again.
          for (int i= 0; i < NumBalls;i++){
        	  
        	  if (c >= 10){
        		  c =0; 		  
        	  }	  
        	ballColor[i] = colors[c];
       balls[i]= new Ball(ballColor[i]);
        	c++;
          }
	}
        	  public void start() {
        		  
        			if (T == null) {
        				T = new Thread(this);
        				T.start();
        			}
        		}
         public void run (){
			while(true){
		//Loop through the number of balls and call the moveball method.		
				for ( int i= 0;i < NumBalls;i++){
							
					balls[i].moveball();
				    repaint();
				}
		
				try {
					Thread.sleep(50); 
				    }
				catch (InterruptedException e) { 
					return; // silently exit
				    }	
		}
		}
			public synchronized void paint(Graphics g) {
				//Loop through the balls and draw the required amount.
			 for (int k= 0; k<NumBalls;k++){
				g.setColor(ballColor[k]);// set the Color.The color array.
			 g.fillOval((int) balls[k].getX(), (int) balls[k].getY(), 20, 20);
			 }
			 }
	}
