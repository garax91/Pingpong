import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Leben {
	private static BufferedImage look;
	private int leben=3;
	
	public Leben(int leben){
		try {
			look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testHerz.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.leben = leben;
	}
	
	public int lebenVerlieren(){
		leben--;
		if(leben==0){
										//spiel muss angehalten werden, ect
			//MeldungVerloren meldung = new MeldungVerloren(seite);
			//meldung.setVisible(true);
	/*		try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {e.printStackTrace();}	
		*///	System.exit(0);
		}
		return leben;
	}
	public void lebenBekommen(){
		leben++;
	}
	

	
	public BufferedImage getLook(){
		return look;
	}
	public int getLeben(){
		return leben;
	}
	

}
