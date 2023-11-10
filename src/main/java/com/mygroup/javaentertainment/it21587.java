/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mygroup.javaentertainment;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author it21587
 */
public class it21587 {
public static void main(String[] args) {
     //Creating objects of classes...Each class does a different job.
     //Class scanner reads from the Keyboard
    Scanner scanner = new Scanner(System.in);
    //Class registerShow is used to register a Show to the system
    registerShow register = new registerShow();
    //Class renewShow is used to renew an existing show with different variables
    renewShow renew = new renewShow(register);
    /*Class Information Search is used to search info of specified show , to let the user rate a show 
    to get a show based on it's id , and to print all shows according to requirements */
    informationSearch infoSearch = new informationSearch();
    //UserManager class is used to manage user sign in , sign-up and sign-out procedures
    UserManager userManager = new UserManager();
    // Creating objects of classes
       System.out.println("Welcome to Java Entertainment!");

        boolean isAdmin = false;
        boolean isUserSignedIn = false;
        // A person that uses the app must be either an admin or a user
        while (true) {
            if (!isAdmin && !isUserSignedIn) {
                System.out.println("Please select an option:");
                System.out.println("1. Admin Login");
                System.out.println("2. User Sign In");
                System.out.println("3. User Sign Up");
                System.out.println("4. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    //In case he uses the Admin Login he must enter the admin username and password
                    case 1:
                        System.out.println("Admin Login");
                        System.out.println("Enter admin username:");
                        String adminUsername = scanner.nextLine();
                        System.out.println("Enter admin password:");
                        String adminPassword = scanner.nextLine();
                       //ADMISSION: The Admin username is admin and the password is admin123
                        if (adminUsername.equals("admin") && adminPassword.equals("admin123")) {
                            isAdmin = true;
                            System.out.println("Admin logged in successfully!");
                        } else {
                            System.out.println("Invalid admin credentials. Please try again.");
                        }
                        break;
                    case 2:
                        //In case he uses option 2 he must sign in with email and password
                        System.out.println("User Sign In");
                        System.out.println("Enter your email:");
                        String signInEmail = scanner.nextLine();
                        System.out.println("Enter your password:");
                        String signInPassword = scanner.nextLine();
                       //userManager.sign in method returns true if the user is signed up in the system
                        if (userManager.signIn(signInEmail, signInPassword)) {
                            isUserSignedIn = true;
                            System.out.println("User signed in successfully!");
                        } else {
                            System.out.println("Invalid email or password. Please try again.");
                        }
                        break;
                    case 3:
                        //In case he uses option 3 he must sign up in the system  with username,  email and password
                        System.out.println("User Sign Up");
                        System.out.println("Enter your username:");
                        String signUpUsername = scanner.nextLine();
                        System.out.println("Enter your email:");
                        String signUpEmail = scanner.nextLine();
                        System.out.println("Enter your password:");
                        String signUpPassword = scanner.nextLine();
                         //Method signUp returns true if the user's email is not registered in the user's list
                        if (userManager.signUp(signUpUsername, signUpEmail, signUpPassword)) {
                            System.out.println("User signed up successfully!");
                        } else {
                            System.out.println("An account with the provided email already exists. Sign-up failed.");
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                //The Menus of Admin and User are different and have different uses for each type of user of the app.
                //A logged in admin has the options to register a show and renew a show
            } else if (isAdmin) {
                System.out.println("Admin Menu");
                System.out.println("Please select an option:");
                System.out.println("1. Register a show");
                System.out.println("2. Renew a show");
                System.out.println("3. Exit");

                int adminChoice = scanner.nextInt();
                scanner.nextLine();

                switch (adminChoice) {
                    case 1:
                        register.registerNewShow();
                        break;
                    case 2:
                        renew.renew();
                        break;
                    case 3:
                        isAdmin = false;
                        System.out.println("Admin logged out successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                //A logged in user has the options to search for a show , leave a review , view his ratings
            } else if (isUserSignedIn) {
                System.out.println("User Menu");
                System.out.println("Please select an option:");
                System.out.println("1. Search for a show");
                System.out.println("2. Leave a review");
                System.out.println("3. View your ratings");
                System.out.println("4. Search Director or Actor");
                System.out.println("5. View the Minimum and Maximum Score of an actor's/Director's Show ");
                System.out.println("6. View your favorite actors/directors");
                System.out.println("7. Sign-out");

                int userChoice = scanner.nextInt();
                scanner.nextLine();

                switch (userChoice) {
                    case 1:
                        System.out.println("Enter search term (title or year):");
                        String searchTerm = scanner.nextLine();
                        List<Show> searchResults = infoSearch.searchShows(searchTerm, register.getShowsList());

                        if (!searchResults.isEmpty()) {
                            System.out.println("Found shows:");
                            for (Show show : searchResults) {
                                System.out.println(show);
                            }
                        } else {
                            System.out.println("No shows found.");
                        }
                        break;
                    case 2:
                        if (userManager.getCurrentUser() == null) {
                            System.out.println("Please sign in to leave a review.");
                        } else {
                            System.out.println("Enter the show ID:");
                            int showId = scanner.nextInt();
                            scanner.nextLine();

                            Show selectedShow = infoSearch.getShowById(showId, register.getShowsList());

                            if (selectedShow == null) {
                                System.out.println("Show not found.");
                            } else {
                                System.out.println("Enter your rating (1-10):");
                                float rating = scanner.nextFloat();
                                scanner.nextLine();
                                //add the evaluation for the specific show [NEEDED TO PREVIEW ALL RATINGS FOR THE SPECIFIC SHOW OF ALL USERS]
                                selectedShow.addEvaluation(userManager.getCurrentUser().getName(), rating);
                                 //add the user's rating to the selected show [NEEDED TO PREVIEW CURRENT USER'S RATINGS FOR  SHOWS]
                                 User currentUser = userManager.getCurrentUser();
                                 currentUser.addRating(selectedShow.getUniqueId(), rating);
                                 infoSearch.addUserRating(selectedShow, rating);
                                System.out.println("Review added successfully!");
                                System.out.println("Press 'R' to see all the reviews for this show (or any other key to continue):");
                                String viewChoice = scanner.nextLine();

                                if (viewChoice.equalsIgnoreCase("R")) {
                                    selectedShow.printAllRatings(selectedShow);
                                }
                            }
                        }
                        break;
                    case 3:
                       if (userManager.getCurrentUser() == null) {
                        System.out.println("Please sign in to view your ratings.");
                    } else {
                           List showsList = register.getShowsList();
                           userManager.getCurrentUser().viewAllRatings(showsList);
                        }
                    break;
                    case 4:
                         if (userManager.getCurrentUser() == null) {
                            System.out.println("Please sign in to search for a director/actor.");
                        } else {
                             System.out.println("Enter actor/director first name:");
                             String firstName = scanner.nextLine();
                             System.out.println("Enter actor/director last name:");
                             String lastName = scanner.nextLine();

            List<Person> peopleResults = infoSearch.searchPersons(firstName, lastName, register.getShowsList());
            if (!peopleResults.isEmpty()) {
                System.out.println("Search results:");
                for (Person person : peopleResults) {
                    System.out.println("Unique ID: " + person.getId());
                    System.out.println("First Name: " + person.getFirstName());
                    System.out.println("Last Name: " + person.getLastName());
                }
                System.out.println("Show Titles:");
                for (Person person : peopleResults) {
                    for (Show show : Show.searchShowsByPerson(register.getShowsList(), person.getFirstName(), person.getLastName())) {
                        System.out.println(show.getTitle());
                    }
                }
                    System.out.println("----------------------------------");
                   //Implementation of the option for the user to add a favorite actor or director.When a user searches for an actor/director he has the choice to add him/her as his/her favorite.
                    System.out.println("Do you want to choose any actor/director as your favorite? (Y/N)");
                    String favoriteChoice = scanner.nextLine();

                     if (favoriteChoice.equalsIgnoreCase("Y")) {
                            System.out.println("Enter the unique ID of the favorite actor/director:");
                            int favoriteId = scanner.nextInt();
                            scanner.nextLine();

                    Person favoritePerson = infoSearch.getPersonById(favoriteId, peopleResults);
                            if (favoritePerson != null) {
                                    userManager.getCurrentUser().setFavoritePerson(favoritePerson);
                                    System.out.println("Favorite actor/director set successfully!");
                        } else {
                                    System.out.println("Invalid actor/director ID.");
                    }
                }
            } else {
                System.out.println("No results found.");
            }
        }
                    break;
                    case 5:
                        if (userManager.getCurrentUser() == null) {
                        System.out.println("Please sign in to view your ratings.");
                    } else {
                             System.out.println("Enter actor/director first name:");
                             String firstName = scanner.nextLine();
                             System.out.println("Enter actor/director last name:");
                             String lastName = scanner.nextLine();
                             infoSearch.viewMinMaxAverageShow(firstName, lastName, register.getShowsList());
                             }
                             break;
                    case 6:
                        if (userManager.getCurrentUser() == null) {
                            System.out.println("Please sign in to view your favorite actors/directors.");
                        } else {
                        List<Person> favoritePeople = userManager.getCurrentUser().getFavoritePeople();

                         if (favoritePeople.isEmpty()) {
                            System.out.println("You have not chosen any favorite actors/directors yet.");
                         } else {
                            System.out.println("Your Favorite Actors/Directors:");
                            for (Person person : favoritePeople) {
                                   System.out.println("Unique ID: " + person.getId());
                                   System.out.println("First Name: " + person.getFirstName());
                                   System.out.println("Last Name: " + person.getLastName());
                                   System.out.println("----------------------------------");
            }
        }
    }
    break;
                    case 7:
                        isUserSignedIn = false;
                        userManager.signOut();
                        System.out.println("User signed out successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
   
   
}
}
