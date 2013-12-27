import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpecialEffekt {

	private  BufferedImage look;
	private  float posx;
	private  float posy;
	private  float speedx = 200;
	private Spieler spielerLinks;
	private Spieler spielerRechts;
	private Leben lebenLinks;
	private Leben lebenRechts;
	private  int modeNummer;
	private int ballNummer;
	private boolean exestiert = true;
	
	SpecialEffekt(int ballNummer, float posx, float posy, Spieler spielerLinks, Spieler spielerRechts, Leben lebenRechts, Leben lebenLinks, int modeNummer){
		try {
			if(modeNummer == 0){
				this.speedx*=1.1;
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testHerz.png"));
			}else if(modeNummer == 1){
				this.speedx*=1.3;
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testEffekt_großerStab.png"));
			}else if(modeNummer == 2){
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testEffekt_kleinerStab.png"));
			}else if(modeNummer == 3){
				this.speedx*=1.6;
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testHerz_gestrichen.png"));
			}else{
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testEffekt.png"));
			}
		} catch (IOException e) {e.printStackTrace();}
		this.posx = posx;
		this.posy = posy;
		this.spielerLinks = spielerLinks;
		this.spielerRechts = spielerRechts;
		this.modeNummer = modeNummer;
		this.ballNummer = ballNummer;
		this.lebenLinks = lebenLinks;
		this.lebenRechts = lebenRechts;
		
		if(ballNummer == 1){
			this.speedx*=-1;
		}
	}
	
	public void update(float timeSinceLastFrame){
		posx+=speedx*timeSinceLastFrame;
		
		//Effekt trifft Spieler//Kollision!! 
		if(exestiert && ((posx < spielerLinks.f_posx + spielerLinks.getLook().getWidth() && posy+(look.getHeight()/2) > spielerLinks.f_posy && posy+(look.getHeight()/2) < spielerLinks.f_posy+spielerLinks.getLook().getHeight())
			|| posx + look.getWidth() > spielerRechts.f_posx && posy+(look.getHeight()/2) > spielerRechts.f_posy && posy+(look.getHeight()/2) < spielerRechts.f_posy+spielerRechts.getLook().getHeight())){

			
			new IngameMeldung(modeNummer);
			
			exestiert = false;
			
			if(modeNummer==0){
				if(ballNummer == 1){
					lebenLinks.lebenBekommen();
				}else if(ballNummer == 2){
					lebenRechts.lebenBekommen();
				}
			}else if(modeNummer==1){
				if(ballNummer == 1){
					spielerLinks.setStabGröße(spielerLinks.getStabgröße()+1);
				}else if(ballNummer == 2){
					spielerRechts.setStabGröße(spielerRechts.getStabgröße()+1);
				}
				if(spielerLinks.getStabgröße() > 2) spielerLinks.setStabGröße(2);
				if(spielerRechts.getStabgröße() > 2) spielerRechts.setStabGröße(2);
			}else if(modeNummer==2){
				if(ballNummer == 1){
					spielerRechts.setStabGröße(spielerRechts.getStabgröße()-1);
				}else if(ballNummer == 2){
					spielerLinks.setStabGröße(spielerLinks.getStabgröße()-1);
				}
				if(spielerLinks.getStabgröße() < 0) spielerLinks.setStabGröße(0);
				if(spielerRechts.getStabgröße() < 0) spielerRechts.setStabGröße(0);;
			}else if(modeNummer==3){
				if(ballNummer == 1){
					lebenRechts.lebenVerlieren();
				}else if(ballNummer == 2){
					lebenLinks.lebenVerlieren();
				}
			}
		}
	}
	
	public BufferedImage getLook(){
		return look;
	}
	public float getPosx(){
		return posx;
	}
	public float getPosy(){
		return posy;
	}
}
