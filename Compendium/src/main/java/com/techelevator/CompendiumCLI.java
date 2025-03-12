package com.techelevator;

import com.techelevator.dao.JdbcMonsterDao;
import com.techelevator.dao.MonsterDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Monster;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.Scanner;

public class CompendiumCLI {

    private final Scanner userInput = new Scanner(System.in);
    private final MonsterDao monsterDao;

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/DndCompendium");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        CompendiumCLI application = new CompendiumCLI(dataSource);
        application.run();
    }

    public CompendiumCLI(DataSource dataSource){
        monsterDao = new JdbcMonsterDao(dataSource);
    }

    private void run(){
        displayBanner();
        boolean running = true;
        String userChoice = userInput.nextLine();
        int menuSelection = Integer.parseInt(userChoice);
        while(running){
            if(menuSelection == 1){
                displayMonsterNames();
                break;
            }else if(menuSelection == 2){
                displayMonsterByName();
            } else{
                running = false;
            }
        }
    }

    private void displayBanner() {
        System.out.println("-----------------------------------------");
        System.out.println("|           Monster Compendium          |");
        System.out.println("-----------------------------------------");
    }

    private void displayMonsterNames(){
        System.out.println("Monsters in Compendium:");
        System.out.println();

        try {
            for (String monster : monsterDao.getAllMonsters()) {
                System.out.println(monster);
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }

    private void displayMonsterByName(){
        System.out.print("Please enter a monster to search: ");
        String inputName = userInput.nextLine();

        try{
            for(Monster monster : monsterDao.getMonstersByName(inputName)){
                System.out.println("\n---------------------------------------");
                System.out.println(monster.toString());
                System.out.println("---------------------------------------\n");
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }



}
