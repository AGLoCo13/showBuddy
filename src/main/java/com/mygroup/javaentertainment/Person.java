/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygroup.javaentertainment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author it21587
 */
public class Person {
    private final int id;
    private final String firstName; 
    private final String lastName;
    private final String birthDate;
    private final String birthCountry;
    private final String website;
//Constructor of the Person class . Person Refers to an Actor or a Director
    public Person(int id, String firstName ,String lastName, String birthDate, String birthCountry, String website) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.birthCountry = birthCountry;
        this.website = website;
    }
//Getters Methods
    public int getId() {
        return id;
    }
    
    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public String getWebsite() {
        return website;
    }
    // Method to search for a person based on their first and last name
    public static List<Person> searchPerson(List<Person> persons, String firstName, String lastName) {
        List<Person> searchResults = new ArrayList<>();
        for (Person person : persons) {
            if (person.getLastName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
                searchResults.add(person);
            }
        }
        return searchResults;
    }
}