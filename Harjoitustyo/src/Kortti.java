
public class Kortti {
	int arvo;
	String nimi;
	int valearvo;
	
	public Kortti(String nimi, int arvo, int valearvo) {
		this.nimi=nimi;
		this.arvo=arvo;
		this.valearvo=valearvo;
	}
	
	public int annaArvo() {
		return arvo;
	}
	//Ei voi toimia tällä tavalla
	public String annaNimi() {
		return nimi;
	}
	public void teeJotain() {
		int x=0;
		x++;
		x=x+3;
	}
	public void teeJotainmuuta() {
		int x=1;
		x++;
		x=x/2;
	}
	
	//lisämuutos
	

}
