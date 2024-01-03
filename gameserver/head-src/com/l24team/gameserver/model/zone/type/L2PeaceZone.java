/* L24Team Project - www.4teambr.com
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
package com.l24team.gameserver.model.zone.type;

import com.l24team.gameserver.model.L2Character;
import com.l24team.gameserver.model.zone.L2ZoneType;

/**
 * A peaceful zone
 * @author durgus
 */
public class L2PeaceZone extends L2ZoneType
{
	public L2PeaceZone(final int id)
	{
		super(id);
	}

	@Override
	protected void onEnter(final L2Character character)
	{
		character.setInsideZone(L2Character.ZONE_PEACE, true);
	}

	@Override
	protected void onExit(final L2Character character)
	{
		character.setInsideZone(L2Character.ZONE_PEACE, false);
	}

	@Override
	protected void onDieInside(final L2Character character)
	{
	}

	@Override
	protected void onReviveInside(final L2Character character)
	{
	}

}
