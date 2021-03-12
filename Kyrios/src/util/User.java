package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class User {
	
	private String name;
	
	public User() {
		this("");
	}

	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static User loadUserInfo(String username, String path) {
		User user = null;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			user = (User) in.readObject();
			in.close();
			fileIn.close();
		} catch(Exception e) {
			if(e instanceof FileNotFoundException) {
				user = new User(username);
				System.out.println("User info not found, creating new User...");
			} else {
				System.out.println("Error loading file from path: " + path);
			}
		}
		return user;
	}
	
	public static void writeUserInfo(User user, String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(user);
			out.close();
			fileOut.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
