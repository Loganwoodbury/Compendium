package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Monster;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
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
    public List<Monster> getMonsterByType(String type){

        List<Monster> monsterByType = new ArrayList<>();
        String sqlMonsterByType = "SELECT * FROM monster WHERE type ILIKE ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sqlMonsterByType, "%" + type + "%");
            while(results.next()){
                monsterByType.add(mapRowToMonster(results));
            }
        }catch(BadSqlGrammarException bsg){
            throw new DaoException("Unable to process request", bsg);
        }catch(CannotGetJdbcConnectionException connEx){
            throw new DaoException("Unable to communicate with server", connEx);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }catch(Exception ex){
            throw new DaoException("Please contact admin", ex);
        }
        return monsterByType;
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
        Monster newMonster = null;

        String newMonsterSql = "INSERT INTO monster(\n" +
                "\tname, size, type, alignment, armor_class, hit_points, hit_points_dice, speed, fly_speed, swim_speed, climb_speed, base_str, mod_str," +
                " base_int, mod_int, base_dex, mod_dex, base_cha, mod_cha, base_con, mod_con, base_wis, mod_wis, saving_throw, skills, damage_immunities, " +
                "damage_vulnerabilities, resistances, condition_immunities, senses, languages, challenge_rating, racial_abilities, actions, legendary_actions, " +
                "legendary_actions_allowed, description, homebrew)\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING *;";

        try{
            SqlRowSet insertedRow = jdbcTemplate.queryForRowSet(newMonsterSql, monster.getMonsterName(), monster.getSize(), monster.getType(),
                    monster.getAlignment(), monster.getArmorClass(), monster.getHitPoints(), monster.getHitPointDice(), monster.getSpeed(),
                    monster.getFlySpeed(), monster.getSwimSpeed(), monster.getClimbSpeed(), monster.getBaseStrength(), monster.getModStrength(),
                    monster.getBaseIntelligence(), monster.getModIntelligence(), monster.getBaseDexterity(), monster.getModDexterity(), monster.getBaseCharisma(), monster.getModCharisma(),
                    monster.getBaseConstitution(), monster.getModConstitution(), monster.getBaseWisdom(), monster.getModWisdom(), monster.getSavingThrow(), monster.getSkill(), monster.getDamageImmunity(),
                    monster.getDamageVulnerability(), monster.getResistance(), monster.getConditionImmunity(), monster.getSense(), monster.getLanguages(), monster.getChallengeRating(),
                    monster.getRacialAbility(), monster.getActions(), monster.getLegendaryActions(), monster.getLegendaryActionsAllowed(), monster.getDescription(), monster.isHomebrew());

            if(insertedRow.next()){
                newMonster = mapRowToMonster(insertedRow);
            }
        }catch(BadSqlGrammarException bsg){
            throw new DaoException("Unable to process request", bsg);
        }catch(CannotGetJdbcConnectionException connEx){
            throw new DaoException("Unable to communicate with server", connEx);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new DaoException("Data integrity violation", e);
        }catch(Exception ex){
            throw new DaoException("Please contact admin", ex);
        }

        return newMonster;
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
