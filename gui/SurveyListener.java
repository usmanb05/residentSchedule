package gui;

import java.util.EventListener;

public interface SurveyListener extends EventListener {
	public void surveyEventOccurred(String[] data);
}
