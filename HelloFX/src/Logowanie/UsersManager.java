package Logowanie;

import java.util.ArrayList;

public class UsersManager {

	private ArrayList<User> arrayUsersProd;
	private ArrayList<User> arrayUsersTest;
	private ArrayList<User> arrayUsersDeve;
	private ArrayList<User> arrayusersNull;

	public UsersManager() {
		arrayUsersProd = new ArrayList<>();
		arrayUsersTest = new ArrayList<>();
		arrayUsersDeve = new ArrayList<>();
		arrayusersNull = new ArrayList<>();
		
		arrayUsersProd.add(new User("adam.nowak"));
		arrayUsersProd.add(new User("ewa.cicha"));
		arrayUsersProd.add(new User("jan.kowalski"));
		
		arrayUsersTest.add(new User("jacek.placek"));
		arrayUsersTest.add(new User("anna.panna"));
		arrayUsersTest.add(new User("mariusz.byk"));
		
		arrayUsersDeve.add(new User("mateusz.pik"));
		arrayUsersDeve.add(new User("olek.karo"));
		arrayUsersDeve.add(new User("marek.trefl"));
		
	}

	public ArrayList<User> getArrayUsersProd() {
		return arrayUsersProd;
	}
	
	public ArrayList<User> getArrayUsersTest() {
		return arrayUsersTest;
	}
	
	public ArrayList<User> getArrayUsersDeve() {
		return arrayUsersDeve;
	}
	
	public ArrayList<User> getArrayusersNull() {
		return arrayusersNull;
	}
	
}
