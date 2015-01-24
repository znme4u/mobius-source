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
package handlers.items;

import lineage2.gameserver.data.xml.holder.ManorDataHolder;
import lineage2.gameserver.instancemanager.MapRegionManager;
import lineage2.gameserver.model.Playable;
import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.Skill;
import lineage2.gameserver.model.instances.ChestInstance;
import lineage2.gameserver.model.instances.MinionInstance;
import lineage2.gameserver.model.instances.MonsterInstance;
import lineage2.gameserver.model.instances.RaidBossInstance;
import lineage2.gameserver.model.items.ItemInstance;
import lineage2.gameserver.network.serverpackets.SystemMessage;
import lineage2.gameserver.network.serverpackets.components.SystemMessageId;
import lineage2.gameserver.tables.SkillTable;
import lineage2.gameserver.templates.mapregion.DomainArea;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public final class Seed extends ScriptItemHandler
{
	private static int[] _itemIds = {};
	
	/**
	 * Constructor for Seed.
	 */
	public Seed()
	{
		_itemIds = new int[ManorDataHolder.getInstance().getAllSeeds().size()];
		int id = 0;
		
		for (Integer s : ManorDataHolder.getInstance().getAllSeeds().keySet())
		{
			_itemIds[id++] = s.shortValue();
		}
	}
	
	/**
	 * Method useItem.
	 * @param playable Playable
	 * @param item ItemInstance
	 * @param ctrl boolean
	 * @return boolean
	 * @see lineage2.gameserver.model.interfaces.IItemHandler#useItem(Playable, ItemInstance, boolean)
	 */
	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean ctrl)
	{
		if ((playable == null) || !playable.isPlayer())
		{
			return false;
		}
		
		final Player player = (Player) playable;
		
		if (playable.getTarget() == null)
		{
			player.sendActionFailed();
			return false;
		}
		
		if (!player.getTarget().isMonster() || (player.getTarget() instanceof RaidBossInstance) || ((player.getTarget() instanceof MinionInstance) && (((MinionInstance) player.getTarget()).getLeader() instanceof RaidBossInstance)) || (player.getTarget() instanceof ChestInstance) || ((((MonsterInstance) playable.getTarget()).getChampion() > 0) && !item.isAltSeed()))
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.THE_TARGET_IS_UNAVAILABLE_FOR_SEEDING));
			return false;
		}
		
		final MonsterInstance target = (MonsterInstance) playable.getTarget();
		
		if (target == null)
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.INVALID_TARGET));
			return false;
		}
		
		if (target.isDead())
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.INVALID_TARGET));
			return false;
		}
		
		if (target.isSeeded())
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.THE_SEED_HAS_BEEN_SOWN));
			return false;
		}
		
		final int seedId = item.getId();
		
		if ((seedId == 0) || (player.getInventory().getItemByItemId(item.getId()) == null))
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.INCORRECT_ITEM_COUNT));
			return false;
		}
		
		final DomainArea domain = MapRegionManager.getInstance().getRegionData(DomainArea.class, player);
		final int castleId = domain == null ? 0 : domain.getId();
		
		if (ManorDataHolder.getInstance().getCastleIdForSeed(seedId) != castleId)
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.THIS_SEED_MAY_NOT_BE_SOWN_HERE));
			return false;
		}
		
		final Skill skill = SkillTable.getInstance().getInfo(2097, 1);
		
		if (skill == null)
		{
			player.sendActionFailed();
			return false;
		}
		
		if (skill.checkCondition(player, target, false, false, true))
		{
			player.setUseSeed(seedId);
			player.getAI().Cast(skill, target);
		}
		
		return true;
	}
	
	/**
	 * Method getItemIds.
	 * @return int[]
	 * @see lineage2.gameserver.model.interfaces.IItemHandler#getItemIds()
	 */
	@Override
	public final int[] getItemIds()
	{
		return _itemIds;
	}
}
