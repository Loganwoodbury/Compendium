package com.techelevator;

import com.techelevator.dao.JdbcMonsterDao;
import com.techelevator.dao.MonsterDao;
import com.techelevator.enums.BestiaryMenu;
import com.techelevator.enums.MainMenu;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Monster;
import com.techelevator.utilities.Console;
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
        Console.bannerize("DnD Compendium");
        mainMenu();
    }

    private void mainMenu(){
        boolean running = true;
        while(running){
            Console.mainMenu();
            MainMenu menuChoice;
            int option = promptForInt("Please make a selection: ");
            menuChoice = MainMenu.values()[option - 1];

            switch (menuChoice){

                case BESTIARY :
                    bestiaryMenu();
                    break;

                case CHARACTER_LOOKUP:
                    characterMenu();
                    break;

                case SPELL_LIST:
                    spellMenu();
                    break;

                case EXIT:
                    running = false;
                    break;
            }
        }
    }

    private void bestiaryMenu(){
        boolean running = true;
        while(running){
            Console.bannerize("Bestiary");
            Console.bestiaryMenu();
            BestiaryMenu menuChoice;
            int option = promptForInt("Please make a selection: ");
            menuChoice = BestiaryMenu.values()[option - 1];

            switch (menuChoice){

                case SEARCH_ALL:
                    displayMonsterNames();
                    break;

                case SEARCH_NAME:
                    displayMonsterByName();
                    break;

                case SEARCH_TYPE:
                    displayMonsterByType();
                    break;

                case RANDOM:
                    displayRandomMonster();
                    break;

                case ADD_NEW:
                    addNewMonster();
                    break;

                case UPDATE:
                    updateMonster();
                    break;

                case DELETE:
                    deleteMonster();
                    break;

                case MAIN_MENU:
                    running = false;
                    break;
            }
        }
    }

    private void characterMenu(){
        System.out.println("not yet implemented");
    }

    private void spellMenu(){
        System.out.println("not yet implemented");
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

        System.out.println();
    }

    private void displayMonsterByName(){
        String inputName = promptForString("Please enter a monster to search: ");

        try{
            for(Monster monster : monsterDao.getMonstersByName(inputName)){
                System.out.println(monster.toString());
                System.out.println("****************************************************");
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }

    private void displayMonsterByType(){
        String type = promptForString("Please enter a type of monster: ");

        try{
            Console.bannerize("Monsters by type " + type);
            for(Monster monster : monsterDao.getMonsterByType(type)){
                System.out.println(monster.toString());
                System.out.println();
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }

    private void displayRandomMonster(){
        System.out.println("not yet implemented");
    }

    private void addNewMonster(){
        System.out.println("Please enter in your monsters details");
        Monster newMonster = promptForNewMonsterData();

        try{
            newMonster = monsterDao.createMonster(newMonster);
            System.out.println("\n Added monster to the database.");
            System.out.println(newMonster.toString());
        }catch(DaoException e){
            displayError("Failed to add monster to database:" + e.getMessage());
        }
    }

    private void updateMonster(){
        System.out.println("not yet implemented");
    }

    private void deleteMonster(){
        System.out.println("not yet implemneted");
    }

    private Monster promptForNewMonsterData(){
        Monster monster = new Monster();

        String name = "";
        while(name.isBlank()){
            name = promptForString("Please enter a name: ");
        }
        monster.setMonsterName(name);

        String size = promptForString("Size: ");
        monster.setSize(size);

        String type = promptForString("Type: ");
        monster.setType(type);

        String alignment = promptForString("Alignment: ");
        monster.setAlignment(alignment);

        int armorClass = promptForInt("Armor Class: ");
        monster.setArmorClass(armorClass);

        int hitPoints = promptForInt("Hit Points: ");
        monster.setHitPoints(hitPoints);

        String hitDice = promptForString("Hit points Dice used: ");
        monster.setHitPointDice(hitDice);

        int speed = promptForInt("Walk Speed: ");
        monster.setSpeed(speed);

        int flySpeed = promptForInt("Fly Speed: ");
        monster.setFlySpeed(flySpeed);

        int swimSpeed = promptForInt("Swim Speed: ");
        monster.setSwimSpeed(swimSpeed);

        int climbSpeed = promptForInt("Climb Speed: ");
        monster.setClimbSpeed(climbSpeed);

        int strength = promptForInt("Strength Score: ");
        monster.setBaseStrength(strength);

        int strengthMod = promptForInt("Strength Mod: ");
        monster.setModStrength(strengthMod);

        int intelligence = promptForInt("Intelligence Score: ");
        monster.setBaseIntelligence(intelligence);

        int intelligenceMod = promptForInt("Intelligence Mod: ");
        monster.setModIntelligence(intelligenceMod);

        int dexterity = promptForInt("Dexterity Score: ");
        monster.setBaseDexterity(dexterity);

        int  modDex = promptForInt("Dexterity Mod: ");
        monster.setModDexterity(modDex);

        int charisma = promptForInt("Charisma Score: ");
        monster.setBaseCharisma(charisma);

        int modCharisma = promptForInt("Charisma Mod: ");
        monster.setModCharisma(modCharisma);

        int constitution = promptForInt("Constitution Score: ");
        monster.setBaseConstitution(constitution);

        int modConstit = promptForInt("Constitution Mod: ");
        monster.setModConstitution(modConstit);

        int wisdom = promptForInt("Wisdom Score: ");
        monster.setBaseWisdom(wisdom);

        int modWisdom = promptForInt("Wisdom Mod: ");
        monster.setModWisdom(modWisdom);

        String savingThrow = promptForString("Saving Throws: ");
        monster.setSavingThrow(savingThrow);

        String skill = promptForString("Skills: ");
        monster.setSkill(skill);

        String damageImmunity = promptForString("Damage Immunity: ");
        monster.setDamageImmunity(damageImmunity);

        String damageVuln = promptForString("Damage Vulnerability: ");
        monster.setDamageVulnerability(damageVuln);

        String resistance = promptForString("Resistances: ");
        monster.setResistance(resistance);

        String conditionImmunity = promptForString("Condition Immunity: ");
        monster.setConditionImmunity(conditionImmunity);

        String sense = promptForString("Senses: ");
        monster.setSense(sense);

        String language = promptForString("Languages: ");
        monster.setLanguages(language);

        double challengeRating = promptForDouble("Challenge Rating: ");
        monster.setChallengeRating(challengeRating);

        String racialAbilities = promptForString("Abilities: ");
        monster.setRacialAbility(racialAbilities);

        String action = promptForString("Actions: ");
        monster.setActions(action);

        String legendaryActions = promptForString("Legendary Actions: ");
        monster.setLegendaryActions(legendaryActions);

        int legendaryAllowed = promptForInt("Legendary Actions Allowed: ");
        monster.setLegendaryActionsAllowed(legendaryAllowed);

        String description = promptForString("Description: ");
        monster.setDescription(description);

        String isHomebrew = promptForString("Is it homebrew?  True or False: ");
        if(isHomebrew.equalsIgnoreCase("True")){
            monster.setHomebrew(true);
        }else{
            monster.setHomebrew(false);
        }

        return monster;
    }



    private String promptForString(String prompt) {
        System.out.print(prompt);
        String response =  userInput.nextLine();

        if(response.isBlank()){
            response = null;
        }
        return response;
    }

    private int promptForInt(String prompt) {
        return (int) promptForDouble(prompt);
    }

    private double promptForDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = userInput.nextLine();
            try {
                return Double.parseDouble(response);
            } catch (NumberFormatException e) {
                if (response.isBlank()) {
                    return -1; //Assumes negative numbers are never valid entries.
                } else {
                    displayError("Numbers only, please.");
                }
            }
        }
    }

    private void displayError(String message) {
        System.out.println();
        System.out.println("***" + message + "***");
    }

}
