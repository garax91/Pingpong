
public class Siegabfrage {
	
	float spielzeit;
	String sieger;
	
	public Siegabfrage(){

	}
	
	public void update(float timeSinceLastFrame, Leben lebenLinks, Leben lebenRechts){
		spielzeit += timeSinceLastFrame;
		
		if(lebenLinks.getLeben()<=0 || lebenRechts.getLeben()<=0){
			if(lebenLinks.getLeben()<=0){
				sieger = "rechts";
			}else if(lebenRechts.getLeben()<=0){
				sieger = "links";
			}
			MeldungGewonnen meldung = new MeldungGewonnen(spielzeit, sieger);
			meldung.setVisible(true);
	/*		try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {e.printStackTrace();}	
		*/	System.exit(0);
		}
	}


}
