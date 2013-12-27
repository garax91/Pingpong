import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Ball {
	private BufferedImage look;
	float posx;
	float posy;
	float speedx;
	float speedy;
	float speed;
	float reset_posx;
	float reset_posy;
	float tempo;
	float timeSinceLastFrame;
	private Rand rand;
	private int spielfeldBreite;
	private int spielfeldHöhe;
	private Spieler spielerLinks;
	private Spieler spielerRechts;
	private Stein[][] stein;	
	private Leben lebenRechts;
	private Leben lebenLinks;
	
	
	public Ball(int ballNummer, float posx, float posy, float speedx, float speedy, Rand rand, Spieler spielerLinks, Spieler spielerRechts, Stein[][] stein, Leben lebenRechts, Leben lebenLinks, int spielfeldBreite, int spielfeldHöhe){
		try {
			if(ballNummer == 1){
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testball_rot.png"));
			}else if(ballNummer == 2){
				look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilder/testball_blau.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.posx = posx;
		this.posy = posy;
		this.speedx = speedx;
		this.speedy = speedy;
		this.reset_posx = posx;
		this.reset_posy = posy;
		this.rand = rand;
		this.spielfeldBreite = spielfeldBreite;
		this.spielfeldHöhe = spielfeldHöhe;
		this.spielerLinks = spielerLinks;
		this.spielerRechts = spielerRechts;
		this.lebenLinks = lebenLinks;
		this.lebenRechts = lebenRechts;
		
		for(int x = 0; x < stein.length; x++){
			this.stein = new Stein[stein.length][stein[x].length];			//setzt das lokale array(stein) auf die gleich größe wie das übergebene Stein-array
		}
		for(int i = 0; i<stein.length; i++){
			for(int j = 0; j< stein[i].length; j++){
				this.stein[i][j] = new Stein();
				this.stein[i][j] = stein[i][j]; 					//kopiert das übergebene array auf das lokale array
			}
		}	
	}
	
	public void update(float timeSinceLastFrame){
		this.timeSinceLastFrame = timeSinceLastFrame;
		posx+=speedx*timeSinceLastFrame;
		posy+=speedy*timeSinceLastFrame;
		
		//Abprallen von den Wänden
		if(posx < 0){
			lebenLinks.lebenVerlieren();
			posx = reset_posx;
			posy = reset_posy;
		}
		if(posx > spielfeldBreite){
			lebenRechts.lebenVerlieren();
			posx = reset_posx;
			posy = reset_posy;
		}
		if(posy < rand.getRand().getHeight()){
			posy = rand.getRand().getHeight();
			speedy*=-1;
		}
		if(posy > spielfeldHöhe-rand.getRand().getHeight()-look.getHeight()){	
			posy = spielfeldHöhe-rand.getRand().getHeight()-look.getHeight();
			speedy*=-1;
		}
		
		

		//abprall vom Spieler 3 Zonen//Kollision!!!		//je nachdem wo der ball den stab berührt wird er anders abgeprallt
		int abweichung = 10;
		
		if((posx < spielerLinks.f_posx + spielerLinks.getLook().getWidth() && posy+(look.getHeight()/2) > spielerLinks.f_posy && posy+(look.getHeight()/2) < spielerLinks.f_posy+spielerLinks.getLook().getHeight())){
			posx = spielerLinks.f_posx+spielerLinks.getLook().getWidth();
			speed = (float) Math.sqrt(Math.pow(speedx, 2) + Math.pow(speedy, 2));
			if(posy+look.getHeight()/2 > spielerLinks.f_posy && posy+look.getHeight()/2 < spielerLinks.f_posy+spielerLinks.getLook().getHeight()/3){
				if(speedy + abweichung < speed){
					speedy -= abweichung;
					speedx=(float) Math.sqrt((Math.pow(speed, 2))-(Math.pow(speedy, 2)));
				}
			}else if(posy+look.getHeight()/2 >= spielerLinks.f_posy+ spielerLinks.getLook().getHeight()/3 && posy+look.getHeight()/2 <= spielerLinks.f_posy+ 2*spielerLinks.getLook().getHeight()/3){
					speedx*=-1;
			}else if(posy+look.getHeight()/2 > spielerLinks.f_posy+ 2*spielerLinks.getLook().getHeight()/3 && posy+look.getHeight()/2 < spielerLinks.f_posy+spielerLinks.getLook().getHeight()){
				if(speedy + abweichung < speed){
					speedy += abweichung;
					speedx=(float) Math.sqrt((Math.pow(speed, 2))-(Math.pow(speedy, 2)));
				}
			}
			//speedx*=-1;
		}
	/*********************/
		if((posx + look.getWidth() > spielerRechts.f_posx && posy+(look.getHeight()/2) > spielerRechts.f_posy && posy+(look.getHeight()/2) < spielerRechts.f_posy+spielerRechts.getLook().getHeight())){
			posx = spielerRechts.f_posx-look.getWidth();
			speed = (float) Math.sqrt(Math.pow(speedx, 2) + Math.pow(speedy, 2));
			if(posy+look.getHeight()/2 > spielerRechts.f_posy && posy+look.getHeight()/2 < spielerRechts.f_posy+spielerRechts.getLook().getHeight()/3){
				if(speedy + abweichung < speed){
					speedy -= abweichung;
					speedx=(float) Math.sqrt((Math.pow(speed, 2))-(Math.pow(speedy, 2)));
				}
			}else if(posy+look.getHeight()/2 >= spielerRechts.f_posy+ spielerRechts.getLook().getHeight()/3 && posy+look.getHeight()/2 <= spielerRechts.f_posy+ 2*spielerRechts.getLook().getHeight()/3){
					//speedx*=-1;
			}else if(posy+look.getHeight()/2 > spielerRechts.f_posy+ 2*spielerRechts.getLook().getHeight()/3 && posy+look.getHeight()/2 < spielerRechts.f_posy+spielerRechts.getLook().getHeight()){
				if(speedy + abweichung < speed){
					speedy += abweichung;
					speedx=(float) Math.sqrt((Math.pow(speed, 2))-(Math.pow(speedy, 2)));
				}
			}
			speedx*=-1;
		}



/*		
		
		//abprall vom spieler 1 Zone//Kollision!! //ohne winkelveränderung
		if((posx < spielerLinks.f_posx + spielerLinks.getLook().getWidth() && posy+(look.getHeight()/2) > spielerLinks.f_posy && posy+(look.getHeight()/2) < spielerLinks.f_posy+spielerLinks.getLook().getHeight())){
			posx = spielerLinks.f_posx+spielerLinks.getLook().getWidth();
			speedx*=-1;
		}
		if((posx + look.getWidth() > spielerRechts.f_posx && posy+(look.getHeight()/2) > spielerRechts.f_posy && posy+(look.getHeight()/2) < spielerRechts.f_posy+spielerRechts.getLook().getHeight())){
			posx = spielerRechts.f_posx-look.getWidth();
			speedx*=-1;
		}
*/		
	

		
/*		//Tempo / Speedx / Speedy /Posx / Posy:
		tempo=(float) Math.sqrt(Math.pow(speedx, 2) + Math.pow(speedy, 2));
		System.out.println("tempo: "+tempo+ " / speedx: " + speedx +" / speedy: " +speedy+ " / posx: "+ posx+" / posy: "+ posy);
*/		
		
		
		
		//abprall von steinen! //Kollision!!
		

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
	public float getSpeedx(){
		return speedx;
	}
	public float getSpeedy(){
		return speedy;
	}
	public float getTimeSinceLastFrame(){
		return timeSinceLastFrame;
	}
	public void setSpeedx(float speedx){
		this.speedx = speedx;
	}
	public void setSpeedy(float speedy){
		this.speedy = speedy;
	}
	public void setPosx(float posx){
		this.posx = posx;
	}
	public void setPosy(float posy){
		this.posy = posy;
	}
}
