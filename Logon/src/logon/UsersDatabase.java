package logon;

import static logon.Environment.*;
import java.util.HashMap;

public class UsersDatabase {

	private HashMap<String, String> users = new HashMap<>();

	public UsersDatabase() {
		users.clear();
	}

	public void defaultUsers(Environment env) {
		users.clear();
		if (env.equals(Produkcyjne)) {
			addUserWithPassword("adam.nowak", "nowakp");
			addUserWithPassword("ewa.kot", "kotp");
			addUserWithPassword("jan.kowalski", "kowalskip");
		} else if (env.equals(Deweloperskie)) {
			addUserWithPassword("devel.level", "levelp");
			addUserWithPassword("anna.panna", "pannap");
			addUserWithPassword("mariusz.byk", "bykp");
		} else if (env.equals(Testowe)) {
			addUserWithPassword("tester.nowy", "nowyp");
			addUserWithPassword("olek.jasny", "jasnypp");
			addUserWithPassword("iwona.mila", "mialp");
		}
	}

	public boolean checkPassword(String user, String password) {
		if (users.get(user).equals(password))
			return true;
		return false;
	}

	private void addUserWithPassword(String user, String password) {
		this.users.put(user, password);
	}

	public HashMap<String, String> getUsers() {
		return users;
	}

}