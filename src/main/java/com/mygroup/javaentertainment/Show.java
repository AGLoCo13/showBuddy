/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.javaentertainment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author it21587
 */
public class Show {
    private final List<Float> userRatings;  //List that will contain all the Ratings for a user
    private final int uniqueId;                     //uniqueId refers to the uniqueId that a show has
    private final String title;                        //title , the title of the show or serie
    private final int year;                            //first year of screening
    private final String type;
    private final Set<String> genres;           //A show can be of multiple genres , so i created a set named genres.
    private final String country;                 //Country of production
    private final Person director;              //Object Person that refers to the director
    private final Set<Person> actors;       //Set of Person objects that refers to actors. A film can have multiple actors so the Set is just what we need to be able to add them.
    private float showRating;                    //A float number that represents the Rating of the show
    private final List<Show> shows;                 //A List of Show Objects in which the shows registered will be stored
    private final Map<String, Float> evaluations;  // Map to store evaluations (username -> rating)
    
    private String currentUser; //Store the current User
   //Constructor of the  Show class.
    public Show(int uniqueId, String title, int year, String type,Set<String> genres,  String country, Person director, Set<Person> actors) {
        this.uniqueId = uniqueId;
        this.title = title;
        this.year = year;
        this.type = type;
        this.genres = genres;
        this.country = country;
        this.director = director;
        this.actors = actors;
        this.userRatings = new ArrayList<>();
        this.evaluations = new HashMap<>();
        this.shows = new ArrayList<>();
    }
    // Constructor of the Show class with title and user rating parameters
    Show(String title, float userRating) {
         // Initialize the necessary fields   
        // Initialize the remaining fields with appropriate default values
        this.uniqueId = 0;
        this.title = title;
        this.year = 0;
        this.type = null;
        this.genres = null;
        this.country = null;
        this.director = null;
        this.actors = null;
        this.userRatings = new ArrayList<>();
        this.shows = null;
        this.evaluations = null;
        this.userRatings.add(userRating);
    }
    //method to put a rating to a list of evaluations for each user
    public void addEvaluation(String username , float rating) {
        evaluations.put(username , rating);
        if (username.equals(currentUser)){
            userRatings.add(rating); // Store the rating for the current user
        }
    }
    //Method that returns a Map of all evaluations ex. User 1 - rating 
    public Map<String, Float> getEvaluations() {
        return evaluations;
    }
    //Method that return all evaluations using a specific username
    public float getEvaluationByUsername(String username){
        return evaluations.getOrDefault(username , 0.0f);
    }
     //Method that gets all the evaluations for a show
    public Map<String , Float> getAllEvaluations(){
        return evaluations;
    }
    public void setCurrentUser(String currentUser){
        this.currentUser =currentUser;
    }
    //Method that adds the user Rating. 
     public void addUserRating(float rating){
        userRatings.add(rating);
    }
     //This method prints all ratings for a specific show
     public void printAllRatings(Show show) {
    System.out.println("All Ratings:");
    for (Map.Entry<String, Float> entry : evaluations.entrySet()) {
        String username = entry.getKey();
        float rating = entry.getValue();
        System.out.println("Username: " + username + ", Rating: " + rating);
    }
}
     public void printAllRatings(String username){
         System.out.println("Ratings for User: " + username);
         for (Map.Entry<String , Float> entry : evaluations.entrySet()){
             String user = entry.getKey();
             float rating = entry.getValue();
             if (user.equals(username)){
             System.out.println("Show: " +  username+ ", Rating: " + rating );
         }
         }
     }
 //This method calculates the Average user rating    
    public float calculateAverageUserRating(){
        float averageRating;
        if(userRatings.isEmpty()){
            averageRating = 0.0f;
            return averageRating;
        }
        float sum = 0.0f;
        for (float rating : userRatings){
            sum += rating;
        }
        averageRating = sum /userRatings.size();
        return averageRating;
    }
   public List<Float> getUserRatings() {
    return userRatings;
}
   //returns true if a show has tatings
public boolean hasRatings(Show show) {
    return !show.getUserRatings().isEmpty();
}

//Getters of the Show instances.
    public int getUniqueId() {
        return uniqueId;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public String getGenre() {
        return String.join(", ", genres);
    }
    public String getType(){
        return type;
    }

    public String getCountry() {
        return country;
    }

    public Person getDirector() {
        return director;
    }

    public Set<Person> getActors() {
        return actors;
    }
    
    public List<Show> getShows() {
        return shows;
    }
//Getters and setters for one Rating of  a Show.
public float getRating() {
    return showRating;
}
 void setUserRating(float newShowRating){
     this.showRating = newShowRating;
}
 public float getUserRating(String username) {
        return evaluations.getOrDefault(username, -1.0f);
    }


 
 /*Getter of the average userRating that uses the method calculateAverageUserRating which is implemented here and returns 
 the result to String  in order for the program to be able to show the Average rating for each show to the user*/
 float getAvgUsrRating(){
     float averageRating = calculateAverageUserRating();
     return averageRating;
 }
    String getAverageUserRating() {
       float averageRating = calculateAverageUserRating();
       return String.format("%.2f", averageRating);
    }
     // Method to search for shows based on an actor/director's first and last name
    public static List<Show> searchShowsByPerson(List<Show> shows, String firstName, String lastName) {
        List<Show> searchResults = new ArrayList<>();
        for (Show show : shows) {
            if (show.getDirector().getFirstName().equalsIgnoreCase(firstName) && show.getDirector().getLastName().equalsIgnoreCase(lastName)) {
                searchResults.add(show);
            } else {
                for (Person actor : show.getActors()) {
                    if (actor.getFirstName().equalsIgnoreCase(firstName) && actor.getLastName().equalsIgnoreCase(lastName)) {
                        searchResults.add(show);
                        break;
                    }
                }
            }
        }
        return searchResults;
    }

    /* Used override here to override the parameters to String in order to be able to show each variable to the user as Strings, otherwise i had a nullPointerException bug all the time...*/
@Override
 public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Unique ID: ").append(uniqueId).append("\n");
    sb.append("Title: ").append(title).append("\n");
    sb.append("Year of First Release: ").append(year).append("\n");
    sb.append("Genre: ").append(getType()).append("\n");
    sb.append("Director: ").append(director.getLastName()).append("\n");
    sb.append("Average User Rating: ").append(getAverageUserRating()).append("\n");
    sb.append("----------------------------------");
    return sb.toString();
}

    


}
/*Used inheritance here by Creating a class Miniseries that extends the Show class in order to extend the functionality of the show class
  and add more variables like number of seasons . number of episodes , last airedYear , etc.. */
class Miniseries extends Show {
public List<Person> actors;
    public void addActor(Person actor) {
        actors.add(actor);
    }
    
    private int numSeasons;
    private int numEpisodes;
    private  int lastAiredYear;
    
//Constructor of the Miniseries class
    public Miniseries(int uniqueId, String title, int year, String type,Set<String> genre, String country, Person director, Set<Person> actors,
                      int numSeasons, int numEpisodes, int lastAiredYear) {
        super(uniqueId, title, year, type, genre,  country, director, actors);
        this.numSeasons = numSeasons;
        this.numEpisodes = numEpisodes;
        this.lastAiredYear = lastAiredYear;
    }
//Getters of the Miniseries class
    public int getNumSeasons() {
        return numSeasons;
    }

    public int getNumEpisodes() {
        return numEpisodes;
    }

    public int getLastAiredYear() {
        return lastAiredYear;
    }

    void setNumSeasons(int newNumSeasons) {
        this.numSeasons = newNumSeasons;
    }

    void setNumEpisodes(int newNumEpisodes) {
       this.numEpisodes = newNumEpisodes;
    }

    void setLastShowYear(int newLastShowYear) {
        this.lastAiredYear = newLastShowYear;
    }
}



