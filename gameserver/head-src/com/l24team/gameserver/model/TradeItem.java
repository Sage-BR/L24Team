/*
 * L24Team Project - www.4teambr.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.l24team.gameserver.model;

/**
 * This class ...
 * @version $Revision: 1.2.4.1 $ $Date: 2005/03/27 15:29:32 $
 */
public final class TradeItem
{
	private int _objectId;
	private int _itemId;
	private int _price;
	private int _storePrice;
	private int _count;
	private int _enchantLevel;

	public TradeItem()
	{
		// null
	}

	public void setObjectId(final int id)
	{
		_objectId = id;
	}

	public int getObjectId()
	{
		return _objectId;
	}

	public void setItemId(final int id)
	{
		_itemId = id;
	}

	public int getItemId()
	{
		return _itemId;
	}

	public void setOwnersPrice(final int price)
	{
		_price = price;
	}

	public int getOwnersPrice()
	{
		return _price;
	}

	public void setstorePrice(final int price)
	{
		_storePrice = price;
	}

	public int getStorePrice()
	{
		return _storePrice;
	}

	public void setCount(final int count)
	{
		_count = count;
	}

	public int getCount()
	{
		return _count;
	}

	public void setEnchantLevel(final int enchant)
	{
		_enchantLevel = enchant;
	}

	public int getEnchantLevel()
	{
		return _enchantLevel;
	}

}
