package gui;

import java.util.EventListener;

import model.Ranking;

public interface SurveyListener extends EventListener {
	public void surveyEventOccurred(Ranking ranks);
}
