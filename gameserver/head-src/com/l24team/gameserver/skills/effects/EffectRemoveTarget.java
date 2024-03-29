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
package com.l24team.gameserver.skills.effects;

import com.l24team.gameserver.ai.CtrlIntention;
import com.l24team.gameserver.model.L2Effect;
import com.l24team.gameserver.model.actor.instance.L2GrandBossInstance;
import com.l24team.gameserver.model.actor.instance.L2RaidBossInstance;
import com.l24team.gameserver.skills.Env;

/**
 * @author programmos, l24team
 */
public class EffectRemoveTarget extends L2Effect
{
	public EffectRemoveTarget(final Env env, final EffectTemplate template)
	{
		super(env, template);
	}

	@Override
	public EffectType getEffectType()
	{
		return EffectType.REMOVE_TARGET;
	}

	@Override
	public boolean onActionTime()
	{
		// nothing
		return false;
	}

	/**
	 * @see com.l24team.gameserver.model.L2Effect#onExit()
	 */

	@Override
	public void onExit()
	{
		try
		{
			// nothing
			super.onExit();
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see com.l24team.gameserver.model.L2Effect#onStart()
	 */
	@Override
	public void onStart()
	{
		// RaidBoss and GrandBoss are immune to RemoveTarget effect
		if ((getEffected() instanceof L2RaidBossInstance) || (getEffected() instanceof L2GrandBossInstance))
		{
			return;
		}

		try
		{
			getEffected().setTarget(null);
			getEffected().abortAttack();
			getEffected().abortCast();
			getEffected().getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, getEffector());
			super.onStart();
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}
}