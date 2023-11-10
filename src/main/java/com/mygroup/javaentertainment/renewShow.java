/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.javaentertainment;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author it21587
 */
public class renewShow {
   private final registerShow registerShow;
   private  Map<String, Show> originalMap;
   private  Map<String, Miniseries> miniseriesMap = new HashMap<>();
    private final Map<String, Person> existingActors;
    private int nextPersonId;
    public renewShow(registerShow registerShow) {
        this.registerShow = registerShow;
        this.originalMap = new HashMap<>();
        this.miniseriesMap = new HashMap<>();
       this.existingActors = new HashMap<>();
       this.nextPersonId = 1;
       if (registerShow != null){
            this.originalMap = registerShow.getShowsMap();
       
        for (Map.Entry<String, Show> entry : originalMap.entrySet()){
        String key = entry.getKey();
        Show show = entry.getValue();
        if (show instanceof Miniseries){
            Miniseries miniseries = (Miniseries) show;
            miniseriesMap.put(key, miniseries);
        }
        }
       
    }
    }
    public void renew(){
         Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the title or unique identification code of the series:");
        String searchKey = scanner.nextLine();
        
        Show show = registerShow.getShow(searchKey);
        
   if (show != null && show instanceof Miniseries){
        
         Miniseries serie = (Miniseries) show;
            System.out.println("Series found: " + serie.getTitle());

            System.out.println("Enter the new number of seasons:");
            int numSeasons = Integer.parseInt(scanner.nextLine());
            serie.setNumSeasons(numSeasons);

            System.out.println("Enter the new number of episodes per season:");
            int numEpisodes = Integer.parseInt(scanner.nextLine());
            serie.setNumEpisodes(numEpisodes);

            System.out.println("Enter the new year of the last show:");
            int lastShowYear = Integer.parseInt(scanner.nextLine());
            serie.setLastShowYear(lastShowYear);

            System.out.println("Do you want to add actors to the series? (yes/no)");
            String addActorsChoice = scanner.nextLine();
            if (addActorsChoice.equalsIgnoreCase("yes")) {
                addActorsToShow(serie);
            }
            // Update the series in the registerShow class
        registerShow.updateShow(searchKey, serie);
            System.out.println("Series successfully renewed!");
        } else{
            System.out.println("Series not found. Please check the title or unique identification code.");
        }
    }

  void addActorsToShow(Miniseries series) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of actors to add:");
        int numActors = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numActors; i++) {
            System.out.println("Enter the first name of actor "+ (i + 1) + ":");
            String actorFirstName = scanner.nextLine();
            System.out.println("Enter the last name of actor " + (i + 1) + ":");
            String actorLastName = scanner.nextLine();

            Person actor;
            if (existingActors.containsKey(actorLastName)) {
                actor = existingActors.get(actorLastName);
                System.out.println("Actor already exists. Enter additional information if available.");
            } else {
                System.out.println("Enter the actor's date of birth:");
                String actorBirthDate = scanner.nextLine();

                System.out.println("Enter the actor's birth country:");
                String actorBirthCountry = scanner.nextLine();

                System.out.println("Enter the actor's website:");
                String actorWebsite = scanner.nextLine();

                actor = new Person(nextPersonId,actorFirstName, actorLastName, actorBirthDate, actorBirthCountry, actorWebsite);
                existingActors.put(actorLastName, actor);
                nextPersonId++;
            }

            series.addActor(actor);
        }
    }
}

