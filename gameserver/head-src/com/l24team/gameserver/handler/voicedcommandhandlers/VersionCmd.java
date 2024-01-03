/*
 * L24Team Project - www.4teambr.com
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.l24team.gameserver.handler.voicedcommandhandlers;

import com.l24team.Config;
import com.l24team.gameserver.handler.IVoicedCommandHandler;
import com.l24team.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author l24team
 */
public class VersionCmd implements IVoicedCommandHandler
{
	private static String[] _voicedCommands =
	{
		"version"
	};

	@Override
	public boolean useVoicedCommand(final String command, final L2PcInstance activeChar, final String target)
	{
		if (command.equalsIgnoreCase("version"))
		{
			activeChar.sendMessage("l24team core revision:        " + Config.SERVER_VERSION);
			activeChar.sendMessage("l24team datapack revision:    " + Config.DATAPACK_VERSION);
		}
		return true;
	}

	@Override
	public String[] getVoicedCommandList()
	{
		return _voicedCommands;
	}
}
