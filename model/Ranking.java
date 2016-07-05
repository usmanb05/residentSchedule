package model;

public class Ranking {
	
	private String[] fieldNames = {"allergy", "pulmonary", "cardiology", "psychiatry", "dermatology", "endocrine", "ent", "genetics", "gi", "gynecology", "hematology", "idisease", "neurology", "ophthalmology", "orthopedics", "palliative", "renal", "rheumatology", "sports", "toxicology", "sevenW", "nineW"};

	
	private int allergy;
	private int pulmonary;
	private int cardiology;
	private int psychiatry;
	private int dermatology;
	private int endocrine;
	private int ent;
	private int genetics;
	private int gi;
	private int gynecology;
	private int hematology;
	private int id;
	private int neurology;
	private int ophthalmology;
	private int orthopedics;
	private int palliative;
	private int renal;
	private int rheumatology;
	private int sports;
	private int toxicology;
	private int sevenW;
	private int nineW;
	
	
	public Ranking(int[] names) {
			
			this.allergy = names[0];
			this.pulmonary = names[1];
			this.cardiology = names[2];
			this.psychiatry = names[3];
			this.dermatology = names[4];
			this.endocrine = names[5];
			this.ent = names[6];
			this.genetics = names[7];
			this.gi = names[8];
			this.gynecology = names[9];
			this.hematology = names[10];
			this.id = names[11];
			this.neurology = names[12];
			this.ophthalmology = names[13];
			this.orthopedics = names[14];
			this.palliative = names[15];
			this.renal = names[16];
			this.rheumatology = names[17];
			this.sports = names[18];
			this.toxicology = names[19];
			this.sevenW = names[20];
			this.nineW = names[21];
	}
	
	public int getAllergy() {
		return allergy;
	}

	public void setAllergy(int allergy) {
		this.allergy = allergy;
	}

	public int getPulmonary() {
		return pulmonary;
	}

	public void setPulmonary(int pulmonary) {
		this.pulmonary = pulmonary;
	}

	public int getCardiology() {
		return cardiology;
	}

	public void setCardiology(int cardiology) {
		this.cardiology = cardiology;
	}

	public int getPsychiatry() {
		return psychiatry;
	}

	public void setPsychiatry(int psychiatry) {
		this.psychiatry = psychiatry;
	}

	public int getDermatology() {
		return dermatology;
	}

	public void setDermatology(int dermatology) {
		this.dermatology = dermatology;
	}

	public int getEndocrine() {
		return endocrine;
	}

	public void setEndocrine(int endocrine) {
		this.endocrine = endocrine;
	}

	public int getEnt() {
		return ent;
	}

	public void setEnt(int ent) {
		this.ent = ent;
	}

	public int getGenetics() {
		return genetics;
	}

	public void setGenetics(int genetics) {
		this.genetics = genetics;
	}

	public int getGi() {
		return gi;
	}

	public void setGi(int gi) {
		this.gi = gi;
	}

	public int getGynecology() {
		return gynecology;
	}

	public void setGynecology(int gynecology) {
		this.gynecology = gynecology;
	}

	public int getHematology() {
		return hematology;
	}

	public void setHematology(int hematology) {
		this.hematology = hematology;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNeurology() {
		return neurology;
	}

	public void setNeurology(int neurology) {
		this.neurology = neurology;
	}

	public int getOphthalmology() {
		return ophthalmology;
	}

	public void setOpthalmology(int ophthalmology) {
		this.ophthalmology = ophthalmology;
	}

	public int getOrthopedics() {
		return orthopedics;
	}

	public void setOrthopedics(int orthopedics) {
		this.orthopedics = orthopedics;
	}

	public int getPalliative() {
		return palliative;
	}

	public void setPalliative(int palliative) {
		this.palliative = palliative;
	}

	public int getRenal() {
		return renal;
	}

	public void setRenal(int renal) {
		this.renal = renal;
	}

	public int getRheumatology() {
		return rheumatology;
	}

	public void setRheumatology(int rheumatology) {
		this.rheumatology = rheumatology;
	}

	public int getSports() {
		return sports;
	}

	public void setSports(int sports) {
		this.sports = sports;
	}

	public int getToxicology() {
		return toxicology;
	}

	public void setToxicology(int toxicology) {
		this.toxicology = toxicology;
	}

	public int getSevenW() {
		return sevenW;
	}

	public void setSevenW(int sevenW) {
		this.sevenW = sevenW;
	}

	public int getNineW() {
		return nineW;
	}

	public void setNineW(int nineW) {
		this.nineW = nineW;
	}
	
	public String toString() {
		return allergy + " " + pulmonary + " " + cardiology + " " + psychiatry + " " + dermatology + " " + endocrine + " " + ent + " " + genetics + " " + gi + " " + gynecology + " " + hematology + " " + id + " " + neurology + " " + ophthalmology + " " + orthopedics + " " + palliative + " " + renal + " " + rheumatology + " " + sports + " " + toxicology + " " + sevenW + " " + nineW; 
	}
	

}
