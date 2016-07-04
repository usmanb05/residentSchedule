package gui;

import java.util.EventObject;


public class SurveyEvent extends EventObject{
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
	
	public SurveyEvent(Object source, int allergy, int pulmonary, int cardiology, int psychiatry, int dermatology, int endocrine, int ent, int genetics, int gi, int gynecology, int hematology, int id, int neurology, 
			int ophthalmology, int orthopedics, int palliative, int renal, int rheumatology, int sports, int toxicology, int sevenW, int nineW) {
		super(source);
		
		this.allergy = allergy;
		this.pulmonary = pulmonary;
		this.cardiology = cardiology;
		this.psychiatry = psychiatry;
		this.dermatology = dermatology;
		this.endocrine = endocrine;
		this.ent = ent;
		this.genetics = genetics;
		this.gi = gi;
		this.gynecology = gynecology;
		this.hematology = hematology;
		this.id = id;
		this.neurology = neurology;
		this.ophthalmology = ophthalmology;
		this.orthopedics = orthopedics;
		this.palliative = palliative;
		this.renal = renal;
		this.rheumatology = rheumatology;
		this.sports = sports;
		this.toxicology = toxicology;
		this.sevenW = sevenW;
		this.nineW = nineW;
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

	public void setOphthalmology(int ophthalmology) {
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
	
}
