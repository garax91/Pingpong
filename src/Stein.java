import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Stein {
	
	private BufferedImage look;
	private int posx;
	private int posy;
	private boolean existiert = true;
	public static int anzahlGetroffen;
	
	Stein(int posx, int posy){
		try {
			look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testStein_gedreht.png"));
		} catch (IOException e) {e.printStackTrace();}
		this.posx = posx;
		this.posy = posy;
	}
	Stein(){
		try {
			look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testStein_gedreht.png"));
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void update(Ball ball){
		
			//getroffene steine "verschwinden"
			//ball prallt ab
/*a*/		if(ball.getSpeedx()>0 && ball.getPosx()+ball.getLook().getWidth()>posx && (ball.getPosx()+ball.getLook().getWidth())-(ball.getSpeedx()*ball.getTimeSinceLastFrame())< posx && existiert
					&& (ball.getPosy()+(ball.getLook().getHeight()/2)>posy && ball.posy+(ball.getLook().getHeight()/2)<posy+look.getHeight() 
							|| ball.getPosy()>posy && ball.posy<posy+look.getHeight() 
							|| ball.getPosy()+(ball.getLook().getHeight())>posy && ball.posy+(ball.getLook().getHeight())<posy+look.getHeight())){
	
				ball.setPosx(posx-ball.getLook().getWidth());
				ball.setSpeedx(ball.speedx*-1);
				existiert = false;
				anzahlGetroffen++;
			}
/*b*/		if(ball.getSpeedy()<0 && ball.getPosy()<posy+look.getHeight() && ball.getPosy()-(ball.getSpeedy()*ball.getTimeSinceLastFrame())> posy+look.getHeight() && existiert
					&& (ball.getPosx()+(ball.getLook().getWidth()/2)>posx && ball.getPosx()+(ball.getLook().getWidth()/2)<posx+look.getWidth() 
							|| ball.getPosx()>posx && ball.getPosx()<posx+look.getWidth()
							|| ball.getPosx()+(ball.getLook().getWidth())>posx && ball.getPosx()+(ball.getLook().getWidth())<posx+look.getWidth())){
				
				ball.setPosy(posy+look.getHeight());
				ball.setSpeedy(ball.speedy*-1);
				existiert = false;
				anzahlGetroffen++;
			}
/*c*/		if(ball.getSpeedx()<0 && ball.getPosx()<posx+look.getWidth() && ball.getPosx()-(ball.getSpeedx()*ball.getTimeSinceLastFrame())> posx+look.getWidth() && existiert
					&& (ball.getPosy()+(ball.getLook().getHeight()/2)>posy && ball.getPosy()+(ball.getLook().getHeight()/2)<posy+look.getHeight()
							|| ball.getPosy()>posy && ball.posy<posy+look.getHeight()
							|| ball.getPosy()+(ball.getLook().getHeight())>posy && ball.posy+(ball.getLook().getHeight())<posy+look.getHeight())){
				
				ball.setPosx(posx+look.getWidth());
				ball.setSpeedx(ball.speedx*-1);
				existiert = false;
				anzahlGetroffen++;
			}
/*d*/		if(ball.getSpeedy()>0 && ball.getPosy()+ball.getLook().getHeight()>posy && (ball.getPosy()+ball.getLook().getHeight())-(ball.getSpeedy()*ball.getTimeSinceLastFrame())< posy && existiert
					&& (ball.getPosx()+(ball.getLook().getWidth()/2)>posx && ball.getPosx()+(ball.getLook().getWidth()/2)<posx+look.getWidth()
							|| ball.getPosx()>posx && ball.getPosx()<posx+look.getWidth()
							|| ball.getPosx()+(ball.getLook().getWidth())>posx && ball.getPosx()+(ball.getLook().getWidth())<posx+look.getWidth())){
			
				ball.setPosy(posy-ball.getLook().getHeight());
				ball.setSpeedy(ball.speedy*-1);
				existiert = false;
				anzahlGetroffen++;
			}
		
	}
	
	public BufferedImage getLook(){
		return look;
	}
	public int getX(){
		return posx;
	}
	public int getY(){
		return posy;
	}
	public boolean getExestiert(){
		return existiert;
	}
	public void setExestiert(boolean exestiert){
		this.existiert = exestiert;
	}
}
