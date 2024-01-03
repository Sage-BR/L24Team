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
package com.l24team.gameserver.handler;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.l24team.gameserver.GameServer;
import com.l24team.gameserver.handler.skillhandlers.BalanceLife;
import com.l24team.gameserver.handler.skillhandlers.BeastFeed;
import com.l24team.gameserver.handler.skillhandlers.Blow;
import com.l24team.gameserver.handler.skillhandlers.Charge;
import com.l24team.gameserver.handler.skillhandlers.ClanGate;
import com.l24team.gameserver.handler.skillhandlers.CombatPointHeal;
import com.l24team.gameserver.handler.skillhandlers.Continuous;
import com.l24team.gameserver.handler.skillhandlers.CpDam;
import com.l24team.gameserver.handler.skillhandlers.Craft;
import com.l24team.gameserver.handler.skillhandlers.DeluxeKey;
import com.l24team.gameserver.handler.skillhandlers.Disablers;
import com.l24team.gameserver.handler.skillhandlers.DrainSoul;
import com.l24team.gameserver.handler.skillhandlers.Fishing;
import com.l24team.gameserver.handler.skillhandlers.FishingSkill;
import com.l24team.gameserver.handler.skillhandlers.GetPlayer;
import com.l24team.gameserver.handler.skillhandlers.Harvest;
import com.l24team.gameserver.handler.skillhandlers.Heal;
import com.l24team.gameserver.handler.skillhandlers.ManaHeal;
import com.l24team.gameserver.handler.skillhandlers.Manadam;
import com.l24team.gameserver.handler.skillhandlers.Mdam;
import com.l24team.gameserver.handler.skillhandlers.Pdam;
import com.l24team.gameserver.handler.skillhandlers.Recall;
import com.l24team.gameserver.handler.skillhandlers.Resurrect;
import com.l24team.gameserver.handler.skillhandlers.SiegeFlag;
import com.l24team.gameserver.handler.skillhandlers.Sow;
import com.l24team.gameserver.handler.skillhandlers.Spoil;
import com.l24team.gameserver.handler.skillhandlers.StrSiegeAssault;
import com.l24team.gameserver.handler.skillhandlers.SummonFriend;
import com.l24team.gameserver.handler.skillhandlers.SummonTreasureKey;
import com.l24team.gameserver.handler.skillhandlers.Sweep;
import com.l24team.gameserver.handler.skillhandlers.TakeCastle;
import com.l24team.gameserver.handler.skillhandlers.Unlock;
import com.l24team.gameserver.handler.skillhandlers.ZakenPlayer;
import com.l24team.gameserver.handler.skillhandlers.ZakenSelf;
import com.l24team.gameserver.model.L2Skill;
import com.l24team.gameserver.model.L2Skill.SkillType;

/**
 * This class ...
 * @version $Revision: 1.1.4.4 $ $Date: 2005/04/03 15:55:06 $
 */
public class SkillHandler
{
	private static final Logger LOGGER = Logger.getLogger(GameServer.class);

	private static SkillHandler _instance;

	private final Map<L2Skill.SkillType, ISkillHandler> _datatable;

	public static SkillHandler getInstance()
	{
		if (_instance == null)
		{
			_instance = new SkillHandler();
		}

		return _instance;
	}

	private SkillHandler()
	{
		_datatable = new TreeMap<>();
		registerSkillHandler(new Blow());
		registerSkillHandler(new Pdam());
		registerSkillHandler(new Mdam());
		registerSkillHandler(new CpDam());
		registerSkillHandler(new Manadam());
		registerSkillHandler(new Heal());
		registerSkillHandler(new CombatPointHeal());
		registerSkillHandler(new ManaHeal());
		registerSkillHandler(new BalanceLife());
		registerSkillHandler(new Charge());
		registerSkillHandler(new ClanGate());
		registerSkillHandler(new Continuous());
		registerSkillHandler(new Resurrect());
		registerSkillHandler(new Spoil());
		registerSkillHandler(new Sweep());
		registerSkillHandler(new StrSiegeAssault());
		registerSkillHandler(new SummonFriend());
		registerSkillHandler(new SummonTreasureKey());
		registerSkillHandler(new Disablers());
		registerSkillHandler(new Recall());
		registerSkillHandler(new SiegeFlag());
		registerSkillHandler(new TakeCastle());
		registerSkillHandler(new Unlock());
		registerSkillHandler(new DrainSoul());
		registerSkillHandler(new Craft());
		registerSkillHandler(new Fishing());
		registerSkillHandler(new FishingSkill());
		registerSkillHandler(new BeastFeed());
		registerSkillHandler(new DeluxeKey());
		registerSkillHandler(new Sow());
		registerSkillHandler(new Harvest());
		registerSkillHandler(new GetPlayer());
		registerSkillHandler(new ZakenPlayer());
		registerSkillHandler(new ZakenSelf());
		LOGGER.info("SkillHandler: Loaded " + _datatable.size() + " handlers.");

	}

	public void registerSkillHandler(final ISkillHandler handler)
	{
		SkillType[] types = handler.getSkillIds();

		for (final SkillType t : types)
		{
			_datatable.put(t, handler);
		}
		types = null;
	}

	public ISkillHandler getSkillHandler(final SkillType skillType)
	{
		return _datatable.get(skillType);
	}

	/**
	 * @return
	 */
	public int size()
	{
		return _datatable.size();
	}
}