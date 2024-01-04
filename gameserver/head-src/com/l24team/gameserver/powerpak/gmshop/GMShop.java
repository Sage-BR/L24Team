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
package com.l24team.gameserver.powerpak.gmshop;

import com.l24team.Config;
import com.l24team.gameserver.cache.HtmCache;
import com.l24team.gameserver.handler.IBBSHandler;
import com.l24team.gameserver.handler.ICustomByPassHandler;
import com.l24team.gameserver.handler.IVoicedCommandHandler;
import com.l24team.gameserver.model.L2Character;
import com.l24team.gameserver.model.actor.instance.L2NpcInstance;
import com.l24team.gameserver.model.actor.instance.L2PcInstance;
import com.l24team.gameserver.model.entity.event.CTF;
import com.l24team.gameserver.model.entity.event.DM;
import com.l24team.gameserver.model.entity.event.TvT;
import com.l24team.gameserver.model.entity.olympiad.Olympiad;
import com.l24team.gameserver.model.multisell.L2Multisell;
import com.l24team.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l24team.gameserver.powerpak.PowerPakConfig;
import com.l24team.gameserver.taskmanager.AttackStanceTaskManager;

/**
 * @author l24team
 */
public class GMShop implements IVoicedCommandHandler, ICustomByPassHandler, IBBSHandler
{

	@Override
	public String[] getVoicedCommandList()
	{

		return new String[]
		{
			PowerPakConfig.GMSHOP_COMMAND
		};
	}

	private boolean checkAllowed(final L2PcInstance activeChar)
	{
		String msg = null;
		if (activeChar.isSitting())
		{
			msg = "GMShop is not available when you sit";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("ALL"))
		{
			msg = "GMShop is not available in this area";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("CURSED") && activeChar.isCursedWeaponEquiped())
		{
			msg = "GMShop is not available with the cursed sword";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("ATTACK") && AttackStanceTaskManager.getInstance().getAttackStanceTask(activeChar))
		{
			msg = "GMShop is not available during the battle";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("DUNGEON") && activeChar.isIn7sDungeon())
		{
			msg = "GMShop is not available in the catacombs and necropolis";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("RB") && activeChar.isInsideZone(L2Character.ZONE_NOSUMMONFRIEND))
		{
			msg = "GMShop is not available in this area";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("PVP") && activeChar.isInsideZone(L2Character.ZONE_PVP))
		{
			msg = "GMShop is not available in this area";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("PEACE") && activeChar.isInsideZone(L2Character.ZONE_PEACE))
		{
			msg = "GMShop is not available in this area";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("SIEGE") && activeChar.isInsideZone(L2Character.ZONE_SIEGE))
		{
			msg = "GMShop is not available in this area";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("OLYMPIAD") && (activeChar.isInOlympiadMode() || activeChar.isInsideZone(L2Character.ZONE_OLY) || Olympiad.getInstance().isRegistered(activeChar) || Olympiad.getInstance().isRegisteredInComp(activeChar)))
		{
			msg = "GMShop is not available at Olympiad";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("EVENT") && (activeChar._inEvent))
		{
			msg = "GMShop is not available at the opening event";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("TVT") && activeChar._inEventTvT && TvT.is_started())
		{
			msg = "GMShop is not available in TVT";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("CTF") && activeChar._inEventCTF && CTF.is_started())
		{
			msg = "GMShop is not available in CTF";
		}
		else if (PowerPakConfig.GMSHOP_EXCLUDE_ON.contains("DM") && activeChar._inEventDM && DM.is_started())
		{
			msg = "GMShop is not available in DM";
		}

		if (msg != null)
		{
			activeChar.sendMessage(msg);
		}

		return msg == null;
	}

	@Override
	public boolean useVoicedCommand(final String command, final L2PcInstance activeChar, final String params)
	{
		if ((activeChar == null) || !checkAllowed(activeChar))
		{
			return false;
		}

		if (command.compareTo(PowerPakConfig.GMSHOP_COMMAND) == 0)
		{
			String index = "";
			if ((params != null) && (params.length() != 0))
			{
				if (!params.equals("0"))
				{
					index = "-" + params;
				}
			}
			final String text = HtmCache.getInstance().getHtm("data/html/gmshop/gmshop" + index + ".htm");
			final NpcHtmlMessage htm = new NpcHtmlMessage(activeChar.getLastQuestNpcObject());
			htm.setHtml(text);
			activeChar.sendPacket(htm);
		}

		return false;
	}

	private static String[] _CMD =
	{
		"gmshop"
	};

	@Override
	public String[] getByPassCommands()
	{
		return _CMD;
	}

	@Override
	public void handleCommand(final String command, final L2PcInstance player, final String parameters)
	{
		if ((player == null) || (parameters == null) || (parameters.length() == 0) || !checkAllowed(player))
		{
			return;
		}

		if (!PowerPakConfig.GMSHOP_USEBBS && !PowerPakConfig.GMSHOP_USECOMMAND)
		{

			L2NpcInstance gmshopnpc = null;

			if (player.getTarget() != null)
			{
				if (player.getTarget() instanceof L2NpcInstance)
				{
					gmshopnpc = (L2NpcInstance) player.getTarget();
					if (gmshopnpc.getTemplate().getNpcId() != PowerPakConfig.GMSHOP_NPC)
					{
						gmshopnpc = null;
					}
				}
			}

			// Possible fix to Buffer - 1
			// Possible fix to Buffer - 2
			if ((gmshopnpc == null) || !player.isInsideRadius(gmshopnpc, L2NpcInstance.INTERACTION_DISTANCE, false, false))
			{
				return;
			}

		} // else (voice and bbs)

		if (parameters.startsWith("multisell"))
		{
			try
			{
				L2Multisell.getInstance().SeparateAndSend(Integer.parseInt(parameters.substring(9).trim()), player, false, 0);
			}
			catch (final Exception e)
			{
				if (Config.ENABLE_ALL_EXCEPTIONS)
				{
					e.printStackTrace();
				}

				player.sendMessage("This list does not exist");
			}
		}
		else if (parameters.startsWith("Chat"))
		{
			useVoicedCommand("", player, parameters.substring(4).trim());
		}

	}

	@Override
	public String[] getBBSCommands()
	{
		return _CMD;
	}
}
