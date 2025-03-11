START TRANSACTION;

DROP TABLE IF EXISTS monster, player, character, player_character;

CREATE TABLE monster (
	id serial,
	name varchar(50) NOT NULL,
	size varchar(100),
	type varchar(100),
	alignment varchar(50),
	armor_class int,
	hit_points int,
	hit_points_dice varchar(10),
	speed int,
	fly_speed int,
	swim_speed int,
	climb_speed int,
	base_str int,
	mod_str int,
	base_int int,
	mod_int int,
	base_dex int,
	mod_dex int,
	base_cha int,
	mod_cha int,
	base_con int,
	mod_con int,
	base_wis int,
	mod_wis int,
	saving_throw varchar(100),
	skills varchar(255),
	damage_immunities varchar(255),
	damage_Vulnerabilities varchar(255),
	resistances varchar(255),
	condition_immunities varchar(255),
	senses varchar(255),
	languages varchar(255),
	challenge_rating decimal (3,2),
	racial_abilities text,
	actions text,
	legendary_actions text,
	legendary_actions_allowed int,
	description text,
	homebrew boolean,
	
	CONSTRAINT pk_monster_id PRIMARY KEY (id)
);

CREATE TABLE player (
	id serial,
	name varchar(20) NOT NULL,
	
	CONSTRAINT pk_player_id PRIMARY KEY (id)
);

CREATE TABLE character (
	id serial,
	name varchar(50) NOT NULL,
	hit_points int,
	
	CONSTRAINT pk_character_id PRIMARY KEY (id)
);

CREATE TABLE player_character (
	player_id int,
	character_id int,
	
	CONSTRAINT pk_player_character PRIMARY KEY (player_id, character_id)
);

ALTER TABLE player_character
	ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES player (id),
	ADD CONSTRAINT fk_character_id FOREIGN KEY (character_id) REFERENCES character (id)
;

INSERT INTO monster(
	name, size, type, alignment, armor_class, hit_points, hit_points_dice, speed, fly_speed, swim_speed, climb_speed, base_str, mod_str, base_int, mod_int, base_dex, mod_dex, base_cha, mod_cha, base_con, mod_con, base_wis, mod_wis, saving_throw, skills, damage_immunities, damage_vulnerabilities, resistances, condition_immunities, senses, languages, challenge_rating, racial_abilities, actions, legendary_actions, legendary_actions_allowed, description, homebrew)
	VALUES ('Skeleton', 'Medium', 'Undead', 'Lawful Evil', 13, 13, '2d8+4', 30, 0, 0, 0, 10, 0, 6, -2, 14, 2, 5, -3, 15, 2, 8, -1, null, null, 'poison', 'bludgeoning', null, 'exhaustion, poisoned', null, 'understands all languages it knew in life but cant speak', .25, null, 'Shortsword. Melee Weapon attack: +4 to hit, reach 5ft., one target. Hit(1d6+2) piercing damage.', null, null, 'Skeleton animated by dark magic.', false ),
			('Minotaur Skeleton', 'Large', 'Undead', 'lawful evil', 12, 67, '9d10 + 18', 40, 0, 0, 0, 18, 4, 6, -2, 11, 0, 5, -3, 15, 2, 8, -1, null, null, 'poison', 'bludgeoning', null, 'exhaustion, poisoned', 'darkvision 60 ft., passive Perception 9', 'understands Abyssal but cant speak', 2, 'Charge. If the skeleton moves at least 10 feet straight toward a target and then hits it with a gore attack on the same turn , the target takes an extra 9 (2d8) piercing damage, it must succeed on a DC 14 Strenght saving throw or be pushed up to 10 feet away and knocked prone.', 'Greataxe. Melee Weapon Attack: +6 to hit, reach 5 ft., one target. Hit: 17(2d12 + 4) slashing damage.  Gore. Melee Weapon Attack: +6 to hit, reach 5 ft., one target. Hit: 13 (2d8 + 4) piercing damage.', null, null, 'Animated by dark magic.', false),
			('Warhorse Skeleton', 'Large', 'Undead', 'lawful evil', 13, 22, '3d10 + 6', 60, 0, 0, 0, 18, 4, 2, -4, 12, 1, 5, -3, 15, 2, 8, -1, null, null, 'poison', 'bludgeoning', null, 'exhaustion, poisoned', 'darkvision 60 ft., passive Perception 9', null, .5, null, 'Hooves. Melee Weapon Attack: +6 to hit, reach 5 ft., one target. Hit: 11 (2d6 +4) bludgeoning damage.', null, null, 'Skeleton animated by dark magic.', false),
			('Zombie', 'Medium', 'Undead', 'Neutral evil', 8, 22, '3d8 + 9', 20, 0, 0, 0, 13, 1, 3, -4, 6, -2, 5, -3, 16, 3, 6, -2, 'Wis +0', null, 'poison', null, null, 'poisoned', 'darkvision 60 ft., passive Perception 8', 'understands the languages it knew in life but cant speak', .25, 'Undead Fortitude. If damage reduces the zombie to 0 hit points, it must make a Con saving throw with a DC of 5 + the damage taken, unless the damage is radiant or from a critical hit. On a success, the zombie drops to 1 hit point instead.', 'Slam. Melee Weapon Attack: +3 to hit, reach 5 ft., one target. Hit: 4 (1d6 + 1) bludgeoning damage.', null, null, 'Sinister necromantic magic infuses the remains of the dead.', false),
			('Ogre Zombie', 'Large', 'Undead', 'Neutral evil', 8, 85, '9d10 + 36', 30, 0, 0, 0, 19, 4, 3, -4, 6, -2, 5, -3, 18, 4, 6, -2, 'Wis +0', null, 'poison', null, null, 'poisoned', 'darkvision 60 ft., passive Perception 8', 'understands Common and Giant but cant speak.', 2, 'Undead Fortitude. If damage reduces the zombie to 0 hit points, it must make a Con saving throw with a DC of 5 + the damage taken, unless the damage is radiant or from a critical hit. On a success, the zombie drops to 1 hit point instead.', 'Morning Star. Melee Weapon Attack +6 to hit, reach 5 ft., one target. Hit: 13 (2d8 + 4) bludgeoning damage.', null, null, 'Sinister necromantic magic infuses the remains of the dead.', false),
			('Flying Sword', 'Small', 'Construct', 'Unaligned', 17, 17, '5d6', 0, 50, 0, 0, 12, 1, 1, -5, 15, 2, 1, -5, 11, 0, 5, -3, 'Dex +4', null, 'poison, psychic', null, null, 'blinded, charmed, deafened, frightened, paralyzed, petrified, poisoned', 'blindsight 60 ft. (blind beyond this radius), passive Perception 7', null, .25, 'Antimagic Susceptibility. The sword is incapacitated while in the area of an antimagic field. If targeted by dispel magic, the sword must succeed on a Constitution saving throw against the casters spell save DC or fall unconscious for 1 minute. \n False Appearance. While the sword remains motionless and isnt flying, it is indistinguishable from a normal sword.', 'Longsword. Melee Weapon Attack: +3 to iht reach 5 ft., one target. Hit: 5 (1d8 + 1) slashing damage.', null, null, 'A flying sword dances through the air, fighting with the confidence of a warrior that cant be injured. Other weapons known to exist in animated object form.', false),
			('Rug Of Smothering', 'Large', 'Construct', 'Unaligned', 12, 33, '6d10', 10, 0, 0, 0, 17, 3, 1, -5, 14, 2, 1, -5, 10, 0, 3, -4, null, null, 'poison, psychic', null, null, 'blinded, charmed, deafened, frightened, paralyzed, petrified, poisoned', 'blindsight 60 ft. (blind beyond this radius), passive Perception 6', null, 2, 'Antimagic Susceptibility. The sword is incapacitated while in the area of an antimagic field. If targeted by dispel magic, the sword must succeed on a Constitution saving throw against the casters spell save DC or fall unconscious for 1 minute. \n False Appearance. While the sword remains motionless and isnt flying, it is indistinguishable from a normal sword. Damage Transfer. While it is grappling a creature, the rug takesonly half the damage dealt to it, and the creature grappled by the rug takes the other half.', 'Smother. Melee Weapon Attack: +5 to hit, reach 5 ft., one Medium or smaller creature. Hit: The creature is grappled (escape DC 13). Until this grapple ends, the target is restrained, blinded, and at risk of suffocating, and the rug cant smother another target. In addition, at the start of each of the targets turns, the target takes 10 (2d6 + 3) bludgeoning damage.', null, null, null, false);

COMMIT TRANSACTION;