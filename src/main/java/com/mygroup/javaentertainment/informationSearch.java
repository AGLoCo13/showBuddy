/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.javaentertainment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author it21587
 */
public class informationSearch {
    public informationSearch(){
    }
    //Method to print all shows 
    public void printAllShows(List<Show> shows){
        for ( Show show : shows){
            System.out.println(show );
        }
            
    }

    // Method for searching shows by title or year
    public List<Show> searchShows(String searchKey, List<Show> shows) {
        List<Show> results = new ArrayList<>();

        for (Show show : shows) {
            if (show.getTitle().equalsIgnoreCase(searchKey) || Integer.toString(show.getYear()).equals(searchKey)) {
                results.add(show);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("Results:");
            for (Show show : results) {
                System.out.println("Unique ID: " + show.getUniqueId());
                System.out.println("Title: " + show.getTitle());
                System.out.println("Year of First Release: " + show.getYear());
                System.out.println("Genre: " + show.getType());
                System.out.println("Director: " + show.getDirector().getLastName());
                System.out.println("Average User Rating: " + show.getAverageUserRating());
                
                if (show instanceof Miniseries){
                    Miniseries series = (Miniseries) show;
                    System.out.println("Year of Last Screening: " + series.getLastAiredYear());
                    System.out.println("Number of Seasons: " + series.getNumSeasons());
                    
                }
                System.out.println("----------------------------------");
            }
        }
        return results;
    }
    //Method to return director/actors based on the requirements of the 4th question. 
    public List<Person> searchPersons(String firstName, String lastName, List<Show> shows) {
    List<Person> results = new ArrayList<>();

    for (Show show : shows) {
        Person director = show.getDirector();
        if (director != null && director.getFirstName().equalsIgnoreCase(firstName) && director.getLastName().equalsIgnoreCase(lastName)) {
            results.add(director);
        }
        //Search for actors 
        Set<Person> actors = show.getActors();
        for (Person actor : actors){
            if (actor.getFirstName().equalsIgnoreCase(firstName) && actor.getLastName().equalsIgnoreCase(lastName)){
             results.add(actor);
        }
        }
        
    }

    if (results.isEmpty()) {
        System.out.println("No results found.");
    } else {
        System.out.println("Results:");
        for (Person person : results) {
            System.out.println("Unique ID: " + person.getId());
            System.out.println("First Name: " + person.getFirstName());
            System.out.println("Last Name: " + person.getLastName());
            System.out.println("Show Titles:");
            for (Show show : Show.searchShowsByPerson(shows, person.getFirstName(), person.getLastName())) {
                System.out.println(show.getTitle());
            }
            System.out.println("----------------------------------");
        }
    }
    return results;
}
    //Method to view the min and max average value of a show when a user gives an actor or a director
    public List<Person> viewMinMaxAverageShow (String firstName,String lastName, List<Show> shows){
        List<Person> results = new ArrayList<>();
        for (Show show : shows) {
        Person director = show.getDirector();
        if (director != null && director.getFirstName().equalsIgnoreCase(firstName) && director.getLastName().equalsIgnoreCase(lastName)) {
            results.add(director);
        }

        // Search for actors
        Set<Person> actors = show.getActors();
        for (Person actor : actors) {
            if (actor.getFirstName().equalsIgnoreCase(firstName) && actor.getLastName().equalsIgnoreCase(lastName)) {
                results.add(actor);
            }
        }
    }

    if (results.isEmpty()) {
        System.out.println("No results found.");
    } else {
        System.out.println("Results:");
        for (Person person : results) {
            System.out.println("Unique ID: " + person.getId());
            System.out.println("First Name: " + person.getFirstName());
            System.out.println("Last Name: " + person.getLastName());
            System.out.println("Show Titles:");
            for (Show show : Show.searchShowsByPerson(shows, person.getFirstName(), person.getLastName())) {
                System.out.println(show.getTitle());
            }
            System.out.println("Highest Average Score: " + getHighestAverageScore(shows, person));
            System.out.println("Lowest Average Score: " + getLowestAverageScore(shows, person));
            System.out.println("----------------------------------");
        }
    }
    return results;
}
  // Method to get the show with the highest average score for a person
private String getHighestAverageScore(List<Show> shows, Person person) {
    float highestScore = Float.MIN_VALUE;
    String showTitle = "";

    for (Show show : shows) {
        if (show.getDirector() != null && show.getDirector().equals(person)) {
            float averageScore = show.getAvgUsrRating();
            if (averageScore > highestScore) {
                highestScore = averageScore;
                showTitle = show.getTitle();
            }
        }
        if (show.getActors().contains(person)) {
            float averageScore = show.getAvgUsrRating();
            if (averageScore > highestScore) {
                highestScore = averageScore;
                showTitle = show.getTitle();
            }
        }
    }

    return showTitle + " (" + highestScore + ")";
}

// Method to get the show with the lowest average score for a person
private String getLowestAverageScore(List<Show> shows, Person person) {
    float lowestScore = Float.MAX_VALUE;
    String showTitle = "";

    for (Show show : shows) {
        if (show.getDirector() != null && show.getDirector().equals(person)) {
            float averageScore = show.getAvgUsrRating();
            if (averageScore < lowestScore) {
                lowestScore = averageScore;
                showTitle = show.getTitle();
            }
        }
        if (show.getActors().contains(person)) {
            float averageScore = show.getAvgUsrRating();
            if (averageScore < lowestScore) {
                lowestScore = averageScore;
                showTitle = show.getTitle();
            }
        }
    }

    return showTitle + " (" + lowestScore + ")";
}
    

    /* Method for user rating. This method takes as arguments a List of shows , the correspondinf showid , username of the user and the rate 
    given by the user */
    public void rateShow(List<Show> shows, int showId, String username, float rating) {
         for (Show show : shows) {
            if (show.getUniqueId() == showId) {
                show.setUserRating(rating);
                System.out.println("Rating saved successfully.");
                return;
            }
        }
        System.out.println("Invalid show ID.");
    }
   //Method to add user rating for a show
    public void addUserRating(Show show, float rating) {
        show.addUserRating(rating);
    }
//getter of a show that takes as arguments the show id and a list of shows
    Show getShowById(int showId, List<Show> shows) {
        for (Show show : shows) {
        if (show.getUniqueId() == showId) {
            return show;
        }
    }
    return null; // Show with the given ID was not found
    }

    Person getPersonById(int id, List<Person> peopleList) {
        for (Person person : peopleList) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

}

    // Other existing methods


