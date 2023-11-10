/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.javaentertainment;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author it21587
 */
public class UserManager {
     private final Map<String, User> users;
    private User currentUser;

    public UserManager() {
        this.users = new HashMap<>();
    }
//Implemented the signIn method that returns true if and only if the users list contains the email and password given to the machine
    public boolean signIn(String email, String password) {
        if (users.containsKey(email)) {
            User user = users.get(email);
            if (user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Sign-in successful. Welcome, " + user.getName() + "!");
                return true;
            }
        }
        //Else prompts the user of a sign in failure and returns false
        System.out.println("Invalid credentials. Sign-in failed.");
        return false;
    }
/*Implemented the signUp method in which the user can sign up to the system if and only if the users' list doesn't contain the email he gave.*/  
    public boolean signUp(String name, String email, String password) {
        if (users.containsKey(email)) {
            System.out.println("Account with the provided email already exists. Sign-up failed.");
            return false;
        } else {
            //Creation of a user object with the given name , email , password.
            User user = new User(name, email, password);
            users.put(email, user);
            System.out.println("Sign-up successful. Welcome, " + user.getName() + "!");
            return true;
        }
    }
//method that gives the option to a signed in user to sign out of the system,
    public void signOut() {
        if (currentUser != null) {
            System.out.println("Signed out. Goodbye, " + currentUser.getName() + "!");
            currentUser = null;
        } else {
            System.out.println("No user signed in.");
        }
    }
    
//This method returns an User object referring to the currentUser
    public User getCurrentUser() {
        return currentUser;
    }
}


