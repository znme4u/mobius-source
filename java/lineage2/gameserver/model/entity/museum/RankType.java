/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lineage2.gameserver.model.entity.museum;

/**
 * @author ALF
 * @date 21.08.2012
 */
public enum RankType
{
	/*
	 * General
	 */
	ACQUIRED_XP(0),
	ACQUIRED_ADENA(1),
	PLAY_DURATION(2),
	BATTLE_DURATION(3),
	TIME_IN_PARTY(4),
	TIME_IN_FULLPARTY(5),
	
	PRIVATE_STORE_SALES(11),
	QUEST_CLEAR(12),
	
	SS_CONSUMED(13),
	SS_CONSUMED_D(13, 0),
	SS_CONSUMED_C(13, 1),
	SS_CONSUMED_B(13, 2),
	SS_CONSUMED_A(13, 3),
	SS_CONSUMED_S(13, 4),
	SS_CONSUMED_R(13, 5),
	
	SPS_CONSUMED(14),
	SPS_CONSUMED_D(14, 0),
	SPS_CONSUMED_C(14, 1),
	SPS_CONSUMED_B(14, 2),
	SPS_CONSUMED_A(14, 3),
	SPS_CONSUMED_S(14, 4),
	SPS_CONSUMED_R(14, 5),
	
	RESURRECTED_CHAR_COUNT(18),
	RESURRECTED_BY_OTHER_COUNT(19),
	NUMBER_OF_DEATH(20),
	
	WEAPON_ENCHANT_MAX(21),
	WEAPON_ENCHANT_MAX_D(21, 1),
	WEAPON_ENCHANT_MAX_C(21, 2),
	WEAPON_ENCHANT_MAX_B(21, 3),
	WEAPON_ENCHANT_MAX_A(21, 4),
	WEAPON_ENCHANT_MAX_S(21, 5),
	WEAPON_ENCHANT_MAX_S80(21, 7),
	WEAPON_ENCHANT_MAX_R(21, 8),
	WEAPON_ENCHANT_MAX_R95(21, 9),
	WEAPON_ENCHANT_MAX_R99(21, 10),
	
	WEAPON_ENCHANT_TRY(22),
	WEAPON_ENCHANT_TRY_D(22, 1),
	WEAPON_ENCHANT_TRY_C(22, 2),
	WEAPON_ENCHANT_TRY_B(22, 3),
	WEAPON_ENCHANT_TRY_A(22, 4),
	WEAPON_ENCHANT_TRY_S(22, 5),
	WEAPON_ENCHANT_TRY_S80(22, 6),
	WEAPON_ENCHANT_TRY_R(22, 8),
	WEAPON_ENCHANT_TRY_R95(22, 9),
	WEAPON_ENCHANT_TRY_R99(22, 10),
	
	ARMOR_ENCHANT_MAX(23),
	ARMOR_ENCHANT_MAX_D(23, 1),
	ARMOR_ENCHANT_MAX_C(23, 2),
	ARMOR_ENCHANT_MAX_B(23, 3),
	ARMOR_ENCHANT_MAX_A(23, 4),
	ARMOR_ENCHANT_MAX_S(23, 5),
	ARMOR_ENCHANT_MAX_S80(23, 6),
	ARMOR_ENCHANT_MAX_R(23, 8),
	ARMOR_ENCHANT_MAX_R95(23, 9),
	ARMOR_ENCHANT_MAX_R99(23, 10),
	
	ARMOR_ENCHANT_TRY(24),
	ARMOR_ENCHANT_TRY_D(24, 1),
	ARMOR_ENCHANT_TRY_C(24, 2),
	ARMOR_ENCHANT_TRY_B(24, 3),
	ARMOR_ENCHANT_TRY_A(24, 4),
	ARMOR_ENCHANT_TRY_S(24, 5),
	ARMOR_ENCHANT_TRY_S80(24, 6),
	ARMOR_ENCHANT_TRY_R(24, 8),
	ARMOR_ENCHANT_TRY_R95(24, 9),
	ARMOR_ENCHANT_TRY_R99(24, 10),
	
	/*
	 * Hunting Field
	 */
	NUMBER_OF_MONSTER_KILLINGS(1000),
	MOSTER_KILL_XP(1001),
	NUMBER_OF_DEATHS_BY_MONSTERS(1002),
	DAMAGE_TO_MONSTERS_MAX(1003),
	DAMAGE_TO_MONSTERS(1004),
	DAMAGE_FROM_MONSTERS(1005),
	
	/*
	 * Raid
	 */
	
	EPIC_BOSS_KILLS(1006),
	EPIC_BOSS_KILLS_25774(1006, 1025774),
	EPIC_BOSS_KILLS_25785(1006, 1025785),
	EPIC_BOSS_KILLS_29195(1006, 1029195),
	EPIC_BOSS_KILLS_25779(1006, 1025779),
	EPIC_BOSS_KILLS_25866(1006, 1025866),
	EPIC_BOSS_KILLS_29194(1006, 1029194),
	EPIC_BOSS_KILLS_29218(1006, 1029218),
	EPIC_BOSS_KILLS_29213(1006, 1029213),
	EPIC_BOSS_KILLS_29196(1006, 1029196),
	EPIC_BOSS_KILLS_25867(1006, 1025867),
	EPIC_BOSS_KILLS_29212(1006, 1029212),
	EPIC_BOSS_KILLS_29197(1006, 1029197),
	
	/*
	 * PvP
	 */
	PK_COUNT(2004),
	PVP_COUNT(2005),
	
	KILLED_BY_PK_COUNT(2001),
	KILLED_IN_PVP_COUNT(2002),
	
	DAMAGE_TO_PC_MAX(2006),
	DAMAGE_TO_PC(2007),
	DAMAGE_FROM_PC(2008),
	
	/*
	 * Clan
	 */
	MEMBERS_COUNT(3000),
	INVITED_COUNT(3001),
	LEAVED_COUNT(3002),
	REPUTATION_COUNT(3003),
	ADENA_COUNT_IN_WH(3004),
	ALL_CLAN_PVP_COUNT(3005),
	CLAN_WAR_WIN_COUNT(3006);
	
	private final int _id;
	private final int _subId;
	
	public static final RankType[] VALUES = values();
	
	private RankType(int id)
	{
		_id = id;
		_subId = 0;
	}
	
	private RankType(int id, int subId)
	{
		_id = id;
		_subId = subId;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public int getSubId()
	{
		return _subId;
	}
	
	public static final RankType getRankType(int Id)
	{
		return getRankType(Id, 0);
	}
	
	public static final RankType getRankType(int Id, int subId)
	{
		for (RankType rt : VALUES)
		{
			if ((rt.getId() == Id) && (rt.getSubId() == subId))
			{
				return rt;
			}
		}
		
		return null;
	}
}
