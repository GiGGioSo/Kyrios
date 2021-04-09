package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable{
	
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
	
	
	// STATIC METHODS OF SERIALIZATION AND DESERIALIZATION
	public static User loadUserInfo(String username, String path) {
		User user = null;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			user = (User) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Deserialization of the User '" + user.name + "' completed! Data imported!");
		} catch(Exception e) {
			if(e instanceof FileNotFoundException) {
				user = new User(username);
				System.out.println("User info not found, creating User '" + user.name + "' created!");
				User.writeUserInfo(user, path);
			} else {
				System.out.println("Error loading file from path: " + path);
				e.printStackTrace();
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
			System.out.println("Serialization of the User '" + user.name + "' completed! Data saved!");
		} catch(Exception e) {
			System.out.println("Error saving file to path: " + path);
			e.printStackTrace();
		}
	}

}
