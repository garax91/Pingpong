import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Spieler {
	float f_posx;
	float f_posy;			//nur hin und her möglich
//	private Rectangle bounding;
	private BufferedImage look;
	private BufferedImage lookNormal;
	private BufferedImage lookGroß;
	private BufferedImage lookKlein;
	private int stabGröße = 1;
	private Rand rand;
	private int feldgrößey;
	
	
	
	
	public Spieler(int x, int y, int feldgrößex, int feldgrößey, Rand rand){
		try {
			lookNormal = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/Stab_multiplayer.png"));
			lookGroß = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/Stab_multiplayer_groß.png"));
			lookKlein = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/Stab_multiplayer_klein.png"));
		} catch (IOException e) {e.printStackTrace();}
//		bounding = new Rectangle(x, y, look.getWidth(), look.getHeight());
		this.f_posx = x;
		this.f_posy = y;
		this.rand = rand;
		this.feldgrößey = feldgrößey;
	}
	
	public void update(float timeSinceLastFrame, int spielernummer){
		
		if(stabGröße == 0){
			look=lookKlein;
		}else if(stabGröße == 1){
			look=lookNormal;
		}else if(stabGröße == 2){
			look=lookGroß;
		}
		
		
		int geschwindigkeit = 380;
		
		if(spielernummer == 1){
			if(Keyboard.isKeyDown(KeyEvent.VK_W))f_posy-=geschwindigkeit*timeSinceLastFrame;
			if(Keyboard.isKeyDown(KeyEvent.VK_S))f_posy+=geschwindigkeit*timeSinceLastFrame;
		}else if(spielernummer == 2){
			if(Keyboard.isKeyDown(KeyEvent.VK_UP))f_posy-=geschwindigkeit*timeSinceLastFrame;
			if(Keyboard.isKeyDown(KeyEvent.VK_DOWN))f_posy+=geschwindigkeit*timeSinceLastFrame;
		}

		
		if(f_posy < rand.getRand().getHeight())	f_posy = rand.getRand().getHeight();
		if(f_posy + look.getHeight() > feldgrößey - rand.getRand().getHeight() )	f_posy = feldgrößey - rand.getRand().getHeight() - look.getHeight();
		
	//	bounding.x = (int)f_posx;
	//	bounding.y = (int)f_posy;
	}
	

	public BufferedImage getLook(){					//grafik einfügen!
		return look;
	}
	public float getPosx(){
		return f_posx;
	}
	public float getPosy(){
		return f_posy;
	}
	public int getStabgröße(){
		return stabGröße;
	}
	public void setStabGröße(int stabgröße){
		this.stabGröße = stabgröße;
	}
}
