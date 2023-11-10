/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.javaentertainment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author it21587
 */
public class User {
    private final String name;               //Name of the user
    private final String email;               //Email of the user
    private final String password;        //password of the user
    private final Map< Integer, Float> ratings;
    private Person favoritePerson; //// New field for favorite actor/director
    private final List<Person> favoritePeople;
    informationSearch infoSearch = new informationSearch();
    
    //Constructor of the class User
    public User(String name, String email , String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.favoritePeople = new ArrayList<>();
        this.ratings = new HashMap<>();
    }
    //Getters
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }

    public void addRating(int showId , float rating){
        ratings.put(showId, rating);
    }
     // Getter and setter for favoritePerson
    public Person getFavoritePerson() {
        return favoritePerson;
    }

    public void setFavoritePerson(Person favoritePerson) {
        this.favoritePerson = favoritePerson;
        //populating the favoritePeople list of the user.
        addFavoritePerson(favoritePerson);
    }

    List<Person> getFavoritePeople() {
       return favoritePeople;
    }
    public void addFavoritePerson(Person person) {
        favoritePeople.add(person);
    }

    public void removeFavoritePerson(Person person) {
        favoritePeople.remove(person);
    }
    //Method to view all the user's Ratings
    public void viewAllRatings(List<Show> showsList) {
    System.out.println("Your Ratings:");
    for (Map.Entry<Integer, Float> entry : ratings.entrySet()) {
        int showId = entry.getKey();
        float rating = entry.getValue();

        Show show = infoSearch.getShowById(showId, showsList);
        if (show != null) {
            System.out.println("Show: " + show.getTitle() + ", Rating: " + rating);
        }
    }
    }
}

