package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Monster;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcMonsterDao implements MonsterDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMonsterDao(DataSource dataSource) {
        this.jdbcTemplate =  new JdbcTemplate(dataSource);
    }

    @Override
    public List<String> getAllMonsters() {

        List<String> monsters = new ArrayList<>();

        String sqlNames = "SELECT name FROM monster ORDER BY name ASC;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sqlNames);
            while(results.next()){
                monsters.add(results.getString("name"));
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }

        return monsters;
    }

    @Override
    public Monster getRandomMonster() {
        return null;
    }

    @Override
    public List<Monster> getMonstersByName(String monsterName) {

        List<Monster> monsterList = new ArrayList<>();
        String sqlMonsterByName = "Select * FROM monster WHERE name ILIKE ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sqlMonsterByName, "%" + monsterName + "%");
            while(results.next()){
                monsterList.add(mapRowToMonster(results));
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server", e);
        }

        return monsterList;
    }

    @Override
    public Monster createMonster(Monster monster) {
        return null;
    }

    private Monster mapRowToMonster(SqlRowSet rowSet){
        Monster monster = new Monster();

        monster.setMonsterId(rowSet.getInt("id"));
        monster.setMonsterName(rowSet.getString("name"));
        monster.setSize(rowSet.getString("size"));
        monster.setType(rowSet.getString("type"));
        monster.setAlignment(rowSet.getString("alignment"));
        monster.setArmorClass(rowSet.getInt("armor_class"));
        monster.setHitPoints(rowSet.getInt("hit_points"));
        monster.setHitPointDice(rowSet.getString("hit_points_dice"));
        monster.setSpeed(rowSet.getInt("speed"));
        monster.setFlySpeed(rowSet.getInt("fly_speed"));
        monster.setSwimSpeed(rowSet.getInt("swim_speed"));
        monster.setClimbSpeed(rowSet.getInt("climb_speed"));
        monster.setBaseStrength(rowSet.getInt("base_str"));
        monster.setModStrength(rowSet.getInt("mod_str"));
        monster.setBaseIntelligence(rowSet.getInt("base_int"));
        monster.setModIntelligence(rowSet.getInt("mod_int"));
        monster.setBaseDexterity(rowSet.getInt("base_dex"));
        monster.setModDexterity(rowSet.getInt("mod_dex"));
        monster.setBaseCharisma(rowSet.getInt("base_cha"));
        monster.setModCharisma(rowSet.getInt("mod_cha"));
        monster.setBaseConstitution(rowSet.getInt("base_con"));
        monster.setModConstitution(rowSet.getInt("mod_con"));
        monster.setBaseWisdom(rowSet.getInt("base_wis"));
        monster.setModWisdom(rowSet.getInt("mod_wis"));
        monster.setSavingThrow(rowSet.getString("saving_throw"));
        monster.setSkill(rowSet.getString("skills"));
        monster.setDamageImmunity(rowSet.getString("damage_immunities"));
        monster.setDamageVulnerability(rowSet.getString("damage_Vulnerabilities"));
        monster.setResistance(rowSet.getString("resistances"));
        monster.setConditionImmunity(rowSet.getString("condition_immunities"));
        monster.setSense(rowSet.getString("senses"));
        monster.setLanguages(rowSet.getString("languages"));
        monster.setChallengeRating(rowSet.getDouble("challenge_rating"));
        monster.setRacialAbility(rowSet.getString("racial_abilities"));
        monster.setActions(rowSet.getString("actions"));
        monster.setLegendaryActions(rowSet.getString("legendary_actions"));
        monster.setLegendaryActionsAllowed(rowSet.getInt("legendary_actions_allowed"));
        monster.setDescription(rowSet.getString("description"));
        monster.setHomebrew(rowSet.getBoolean("homebrew"));

        return monster;

    }
}
