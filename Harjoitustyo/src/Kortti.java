
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
	public String annaNimi() {
		return nimi;
	}
	

}
