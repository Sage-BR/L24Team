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
package com.l24team.gameserver.powerpak;

/**
 * l24team
 */
import org.apache.log4j.Logger;

import com.l24team.Config;
import com.l24team.gameserver.communitybbs.CommunityBoard;
import com.l24team.gameserver.datatables.BufferSkillsTable;
import com.l24team.gameserver.datatables.CharSchemesTable;
import com.l24team.gameserver.handler.AutoVoteRewardHandler;
import com.l24team.gameserver.handler.VoicedCommandHandler;
import com.l24team.gameserver.handler.custom.CustomBypassHandler;
import com.l24team.gameserver.handler.voicedcommandhandlers.Repair;
import com.l24team.gameserver.model.actor.instance.L2PcInstance;
import com.l24team.gameserver.powerpak.Buffer.BuffHandler;
import com.l24team.gameserver.powerpak.Buffer.BuffTable;
import com.l24team.gameserver.powerpak.RaidInfo.RaidInfoHandler;
import com.l24team.gameserver.powerpak.engrave.EngraveManager;
import com.l24team.gameserver.powerpak.globalGK.GKHandler;
import com.l24team.gameserver.powerpak.gmshop.GMShop;

public class PowerPak
{
	private final Logger LOGGER = Logger.getLogger(PowerPak.class);
	private static PowerPak _instance = null;

	public static PowerPak getInstance()
	{
		if (_instance == null)
		{
			_instance = new PowerPak();
		}
		return _instance;
	}

	private PowerPak()
	{
		if (Config.POWERPAK_ENABLED)
		{
			PowerPakConfig.load();
			if (PowerPakConfig.BUFFER_ENABLED)
			{
				LOGGER.info("Buffer is Enabled.");
				BuffTable.getInstance();
				if (((PowerPakConfig.BUFFER_COMMAND != null) && (PowerPakConfig.BUFFER_COMMAND.length() > 0)) || PowerPakConfig.BUFFER_USEBBS)
				{

					final BuffHandler handler = new BuffHandler();
					if (PowerPakConfig.BUFFER_USECOMMAND && (PowerPakConfig.BUFFER_COMMAND != null) && (PowerPakConfig.BUFFER_COMMAND.length() > 0))
					{
						VoicedCommandHandler.getInstance().registerVoicedCommandHandler(handler);
					}

					if (PowerPakConfig.BUFFER_USEBBS)
					{
						CommunityBoard.getInstance().registerBBSHandler(handler);
					}
					CustomBypassHandler.getInstance().registerCustomBypassHandler(handler);

				}

				BufferSkillsTable.getInstance();
				CharSchemesTable.getInstance();
			}

			if (PowerPakConfig.GLOBALGK_ENABDLED)
			{
				final GKHandler handler = new GKHandler();
				if ((PowerPakConfig.GLOBALGK_COMMAND != null) && (PowerPakConfig.GLOBALGK_COMMAND.length() > 0))
				{
					VoicedCommandHandler.getInstance().registerVoicedCommandHandler(handler);
				}

				if (PowerPakConfig.GLOBALGK_USEBBS)
				{
					CommunityBoard.getInstance().registerBBSHandler(handler);
				}
				CustomBypassHandler.getInstance().registerCustomBypassHandler(handler);
				LOGGER.info("Global Gatekeeper is Enabled.");
			}

			if (PowerPakConfig.GMSHOP_ENABLED)
			{
				final GMShop gs = new GMShop();
				CustomBypassHandler.getInstance().registerCustomBypassHandler(gs);
				if ((PowerPakConfig.GLOBALGK_COMMAND != null) && (PowerPakConfig.GLOBALGK_COMMAND.length() > 0))
				{
					VoicedCommandHandler.getInstance().registerVoicedCommandHandler(gs);
				}

				if (PowerPakConfig.GMSHOP_USEBBS)
				{
					CommunityBoard.getInstance().registerBBSHandler(gs);
				}
				LOGGER.info("GM Shop is Enabled.");
			}

			if (PowerPakConfig.ENGRAVER_ENABLED)
			{
				EngraveManager.getInstance();
				LOGGER.info("Engrave System is Enabled.");
			}

			final RaidInfoHandler handler = new RaidInfoHandler();
			CustomBypassHandler.getInstance().registerCustomBypassHandler(handler);
			LOGGER.info("Raid Info is Enabled.");

			if (PowerPakConfig.CHAR_REPAIR)
			{
				final Repair repair_handler = new Repair();
				VoicedCommandHandler.getInstance().registerVoicedCommandHandler(repair_handler);
				CustomBypassHandler.getInstance().registerCustomBypassHandler(repair_handler);
				LOGGER.info("Char Repair is Enabled.");
			}

			// Vote Reward System
			if (PowerPakConfig.AUTOVOTEREWARD_ENABLED)
			{
				AutoVoteRewardHandler.getInstance();
			}
		}
	}

	public void chatHandler(final L2PcInstance sayer, final int chatType, final String message)
	{
	}
}