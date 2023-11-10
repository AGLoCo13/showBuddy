/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.javaentertainment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author it21587
 */
public class registerShow {
    private final Map<String, Person> people;
    private final Map< String, Show> shows;
    private int nextPersonId;
    private int nextShowId;
     public registerShow() {
        this.people = new HashMap<>();
        this.shows = new HashMap<>();
        this.nextPersonId = 1;
        this.nextShowId = 1;
    }
 //Method that is used to register a Show 
public void registerNewShow(){
    Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the title of the show: ");
        String title = scanner.nextLine();

        System.out.print("Enter the year of 1st screening: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the type of the show (Film , Series , Miniseries ): ");
        String typeString = scanner.nextLine();
        
        System.out.println("Enter the genre of the show: ");
        String genreString = scanner.nextLine();
        Set<String> genres = new HashSet<>(Arrays.asList(genreString.split(",")));
        
                
        System.out.print("Enter the country of production: ");
        String country = scanner.nextLine();
        
        System.out.println("Enter the director's first name: ");
        String directorFirstName = scanner.nextLine();

        System.out.print("Enter the director's last name: ");
        String directorLastName = scanner.nextLine();
        
        

        Person director;
        if (people.containsKey(directorLastName)) {
            director = people.get(directorLastName);
            System.out.println("Director already exists in the system.");
        } else {
            System.out.print("Enter the director's date of birth (yyyy-mm-dd): ");
            String directorBirthdateString = scanner.nextLine();
            System.out.print("Enter the director's country of birth: ");
            String directorBirthCountry = scanner.nextLine();
            director = new Person(nextPersonId,directorFirstName, directorLastName, directorBirthdateString, directorBirthCountry, null);
            people.put(directorLastName, director);
            nextPersonId++;
            System.out.println("Director registered with ID: " + director.getId());
        }
        
        //The actors must be between 1 and 10 .
        System.out.print("Enter the number of actors in the show (1-10): ");
         int numActors = getIntInputInRange(scanner, 1, 10);

        Set<Person> actors = new HashSet<>();
        for (int i = 1; i <= numActors; i++) {
            
            System.out.println("Enter the actor's first name:");
            String actorFirstName = scanner.nextLine();
            
            System.out.print("Enter the actor's last name: ");
            String actorLastName = scanner.nextLine();

            Person actor;
            if (people.containsKey(actorLastName)) {
                actor = people.get(actorLastName);
                System.out.println("Actor already exists in the system.");
            } else {
                System.out.print("Enter the actor's date of birth (yyyy-mm-dd): ");
                String actorBirthdateString = scanner.nextLine();
                System.out.print("Enter the actor's country of birth: ");
                String actorBirthCountry = scanner.nextLine();
                actor = new Person(nextPersonId,actorFirstName, actorLastName, actorBirthdateString, actorBirthCountry, null);
                people.put(actorLastName, actor);
                nextPersonId++;
                System.out.println("Actor registered with ID: " + actor.getId());
            }

            actors.add(actor);
        }
        //User must type "miniseries" or "series" when asked the "type" of the show to the program in order to dereference a Film from a serie.
        Show show;
        if (typeString.equalsIgnoreCase("series")) {
            System.out.print("Enter the number of seasons in the series: ");
            int numSeasons = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter the number of episodes in each season: ");
            int numEpisodes = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter the year the miniseries last aired (yyyy): ");
            int lastAiredYear = scanner.nextInt();
            scanner.nextLine();
            //creation of a new miniseries and parsing it to a show object
            show = new Miniseries(nextShowId, title, year, typeString,genres,  country, director, actors, numSeasons, numEpisodes, lastAiredYear);
        } else if (typeString.equalsIgnoreCase("Miniseries")){
            System.out.print("Enter the number of seasons in the miniseries: ");
             int numSeasons = scanner.nextInt();
              scanner.nextLine();
              
             System.out.print("Enter the number of episodes in each season: ");
             int numEpisodes = scanner.nextInt();
             scanner.nextLine();

              System.out.print("Enter the last aired year of the miniseries: ");
              int lastAiredYear = scanner.nextInt();
              scanner.nextLine();

        // Create a new Miniseries object
        show = new Miniseries(nextShowId, title, year, typeString, genres, country, director, actors, numSeasons, numEpisodes, lastAiredYear);
        }else{    
        //if type doesn't contain miniseries then it's a film.
            show = new Show(nextShowId, title, year, typeString, genres, country, director, actors);
        }

        shows.put(String.valueOf(show.getUniqueId()), show);
        nextShowId++;

        System.out.println("Show registered with ID: " + show.getUniqueId());
        System.out.println("Title: " + show.getTitle());
        System.out.println("Type: " + show.getType());
        System.out.println("Genre: " + show.getGenres());
        System.out.println("Director's last name: " + show.getDirector().getLastName());
    }

     public List<Show>  getShowsList(){
         return new ArrayList<>(shows.values());
     }
     public List<Show> getShowsList(String username) {
    List<Show> showsList = new ArrayList<>();
    for (Show show : shows.values()) {
        Show showWithRating = new Show(show.getTitle(), show.getUserRating(username));
        showsList.add(showWithRating);
    }
    return showsList;
}
     public Map<String, Show> getShowsMap() {
        return new HashMap<>(shows);
    }

    public Show getShow(String key) {
        return shows.get(key);
    }

    public void addShow(Show show) {
        shows.put(String.valueOf(show.getUniqueId()), show);
    }

    public void updateShow(String key, Show updatedShow) {
        shows.put(key, updatedShow);
    }
    private int getIntInputInRange(Scanner scanner, int min, int max) {
    int input;
    do {
        System.out.print("Enter a number between " + min + " and " + max + ": ");
        input = scanner.nextInt();
        scanner.nextLine();
    } while (input < min || input > max);
    return input;
}
     
}