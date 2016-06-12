package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {

private List<Resident> people;
	
	public Database () {
		people = new LinkedList<Resident>();
	}
	
	public void removePerson(int index) {
		people.remove(index);
	}
	
	public void addPerson(Resident person) {
		people.add(person);
	}
	
	public List<Resident> getPeople() {
		return Collections.unmodifiableList(people);
	}
}
