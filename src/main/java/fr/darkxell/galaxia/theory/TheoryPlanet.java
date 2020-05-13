package fr.darkxell.galaxia.theory;

public class TheoryPlanet {

	public String theoryID;
	public String name;
	public String collapse;
	public String ring;
	public String ennemy;

	public String drop_weak;
	public String drop_med;
	public String drop_strong;
	public String drop_boss;
	public String drop_amarna_1;
	public String drop_amarna_2;
	public String drop_amarna_3;
	public String drop_soris_1;
	public String drop_soris_2;
	public String drop_soris_3;
	public String drop_giza_1;
	public String drop_giza_2;
	public String drop_giza_3;
	
	public String[] recap;

	public TheoryPlanet(String theoryID, String name, String collapse, String ring, String ennemy, String weak,
			String med, String strong, String boss, String amarna1, String amarna2, String amarna3, String soris1,
			String soris2, String soris3, String giza1, String giza2, String giza3) {
		this.theoryID = theoryID;
		this.name = name;
		this.collapse = collapse;
		this.ring = ring;
		this.ennemy = ennemy;

		this.drop_weak = weak;
		this.drop_med = med;
		this.drop_strong = strong;
		this.drop_boss = boss;
		this.drop_amarna_1 = amarna1;
		this.drop_amarna_2 = amarna2;
		this.drop_amarna_3 = amarna3;
		this.drop_soris_1 = soris1;
		this.drop_soris_2 = soris2;
		this.drop_soris_3 = soris3;
		this.drop_giza_1 = giza1;
		this.drop_giza_2 = giza2;
		this.drop_giza_3 = giza3;
	}

	public TheoryPlanet(String theoryID, String name, String collapse, String ennemy) {
		this.theoryID = theoryID;
		this.name = name;
		this.collapse = collapse;
		this.ennemy = ennemy;
	}

}
