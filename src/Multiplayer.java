import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;


public class Multiplayer {
	public static void main(String[] args) {


		boolean vollbildModus = false;	//später hauptmenüabfrage!!
										//später auflösung -> hauptmenüabfrage
		final int FELDGRÖßEX = 1024; 
		final int FELDGRÖßEY = 768;
		
		boolean start = false;
		float pausenCooldown = 0;
		
		int anzahlXSteine = 7;		//16
		int anzahlYSteine = 14;
		int xAbstand = 40;
		int yAbstand = 50;
		Stein[][] stein = new Stein[anzahlXSteine][anzahlYSteine];						
		int xRichtung = xAbstand; 
		int yRichtung = yAbstand;
		for(int i = 0; i<stein.length; i++){
			for(int j=0; j<stein[i].length; j++){
				stein[i][j] = new Stein(xRichtung+340, yRichtung-12);
				yRichtung+=yAbstand;
			}
			yRichtung=yAbstand;
			xRichtung+=xAbstand;
		}

		Leben lebenRechts = new Leben(3);
		Leben lebenLinks = new Leben(3);
		Rand rand = new Rand();								
		Spieler spielerLinks = new Spieler(25, 200, FELDGRÖßEX, FELDGRÖßEY, rand);							//startpos, auflösung, auflösung, ..
		Spieler spielerRechts = new Spieler(FELDGRÖßEX-25, 200, FELDGRÖßEX, FELDGRÖßEY, rand);
		
		Random rnd = new Random();
		int anzahlSpecialSteine = 60;
		int rndX;
		int rndY;
		SpecialStein[] xStein = new SpecialStein[anzahlSpecialSteine];
		for(int i=0; i<xStein.length; i++){
			do{
				rndX = rnd.nextInt(anzahlXSteine);
				rndY = rnd.nextInt(anzahlYSteine);
			}while(stein[rndX][rndY].getExestiert()==false);
			xStein[i] = new SpecialStein(stein[rndX][rndY].getX(), stein[rndX][rndY].getY(), i%6, spielerLinks, spielerRechts, lebenRechts, lebenLinks);
			stein[rndX][rndY].setExestiert(false);
		}
		
		Ball ball1 = new Ball(1, 300, FELDGRÖßEY-50, 250, 250, rand, spielerLinks, spielerRechts, stein, lebenRechts, lebenLinks, FELDGRÖßEX, FELDGRÖßEY);	//posx, posy, speedx, speedy,
		Ball ball2 = new Ball(2, FELDGRÖßEX-300, 50, -250, 250, rand, spielerLinks, spielerRechts, stein, lebenRechts, lebenLinks, FELDGRÖßEX, FELDGRÖßEY);	//posx, posy, speedx, speedy,
		Siegabfrage siegabfrage = new Siegabfrage();
		
		Meldung meldung = new Meldung(FELDGRÖßEX, FELDGRÖßEY);
									
		
		Frame f = new Frame(spielerLinks, spielerRechts, ball1, ball2, lebenLinks, lebenRechts, rand, 1024, 768, meldung, stein, xStein);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(FELDGRÖßEX, FELDGRÖßEY);
		f.setUndecorated(true);		//fullscreen
	//	f.setVisible(true);			//wird bei fullscreen nicht benötigt
		f.setResizable(false);
		f.setLocationRelativeTo(null);
			
		
		if(vollbildModus){
			DisplayMode displayMode = new DisplayMode(FELDGRÖßEX, FELDGRÖßEY, 16, 60);   
			GraphicsEnvironment enviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice device = enviroment.getDefaultScreenDevice();
			
			device.setFullScreenWindow(f);
			device.setDisplayMode(displayMode);
		}else{
			f.setVisible(true);			//wird bei fullscreen nicht benötigt
		}
		
		
		f.makeStrate(); 	
		
		
		long lastFrame = System.currentTimeMillis();
		while(true){
			if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)){System.exit(0);}
			long thisFrame = System.currentTimeMillis();
			float timeSinceLastFrame = ((float)(thisFrame - lastFrame))/1000f;
			lastFrame = thisFrame;
			
			/*pausenfunktion
			zwischen pause an-aus-an-aus-etc muss min 1 sekunde vergehen
			spiel wird tatsächlich angehalten (lastFrame =  systemzeit)*/
			
			pausenCooldown+=timeSinceLastFrame;
			
			if(Keyboard.isKeyDown(KeyEvent.VK_P) && pausenCooldown > 1){
				boolean pause = true;
				pausenCooldown = 0;
				while(pause){
					f.paintMeldung(false, true);
					if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)){System.exit(0);}
					pausenCooldown+=timeSinceLastFrame;
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {e.printStackTrace();}
					if(Keyboard.isKeyDown(KeyEvent.VK_P) && pausenCooldown > 1){
						pause = false;
					}
				}
				lastFrame = System.currentTimeMillis();
				pausenCooldown = 0;
			}
			
			
			
			
			spielerLinks.update(timeSinceLastFrame, 1);
			spielerRechts.update(timeSinceLastFrame, 2);
			for(int i = 0; i<stein.length; i++){
				for(int j = 0; j<stein[i].length; j++){
					stein[i][j].update(ball1);
					stein[i][j].update(ball2);
				}
			}
			for(int i = 0; i<xStein.length; i++){
				xStein[i].update(1, ball1);
				xStein[i].update(2, ball2);
				if(xStein[i].getExestiert()==false && xStein[i].getEffekt() != null){
					xStein[i].getEffekt().update(timeSinceLastFrame);
				}
			}
			ball1.update(timeSinceLastFrame);
			ball2.update(timeSinceLastFrame);
			siegabfrage.update(timeSinceLastFrame, lebenLinks, lebenRechts);
			
			
			f.repaintScreen();
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {e.printStackTrace();}
			
			
			
			//startet das spiel nach dem ersten frame (zeichnen):
			while(!start){
				if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)){System.exit(0);}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {e.printStackTrace();}
				
				f.paintMeldung(true, false);
				
				if(Keyboard.isKeyDown(KeyEvent.VK_ENTER)){
					start= true;
					lastFrame = System.currentTimeMillis();
				}
			}
		}
	}
}

