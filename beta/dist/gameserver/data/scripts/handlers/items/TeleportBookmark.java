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

import lineage2.gameserver.model.Player;
import lineage2.gameserver.model.items.ItemInstance;
import lineage2.gameserver.network.serverpackets.SystemMessage;
import lineage2.gameserver.network.serverpackets.components.SystemMessageId;

/**
 * @author Mobius
 * @version $Revision: 1.0 $
 */
public final class TeleportBookmark extends SimpleItemHandler
{
	private static final int[] ITEM_IDS = new int[]
	{
		13015
	};
	
	/**
	 * Method getItemIds.
	 * @return int[]
	 * @see lineage2.gameserver.model.interfaces.IItemHandler#getItemIds()
	 */
	@Override
	public int[] getItemIds()
	{
		return ITEM_IDS;
	}
	
	/**
	 * Method useItemImpl.
	 * @param player Player
	 * @param item ItemInstance
	 * @param ctrl boolean
	 * @return boolean
	 */
	@Override
	protected boolean useItemImpl(Player player, ItemInstance item, boolean ctrl)
	{
		if ((player == null) || (item == null))
		{
			return false;
		}
		
		if (player.bookmarks.getCapacity() >= 30)
		{
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOUR_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_REACHED_ITS_MAXIMUM_LIMIT));
			return false;
		}
		
		player.getInventory().destroyItem(item, 1);
		player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_DISAPPEARED).addItemName(item.getId()));
		player.bookmarks.setCapacity(player.bookmarks.getCapacity() + 3);
		player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.THE_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_BEEN_INCREASED));
		return true;
	}
}
