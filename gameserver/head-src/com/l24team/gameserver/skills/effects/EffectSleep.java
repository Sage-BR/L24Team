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
package com.l24team.gameserver.skills.effects;

import com.l24team.gameserver.model.L2Effect;
import com.l24team.gameserver.skills.Env;

/**
 * @author mkizub
 */
final class EffectSleep extends L2Effect
{

	public EffectSleep(final Env env, final EffectTemplate template)
	{
		super(env, template);
	}

	@Override
	public EffectType getEffectType()
	{
		return EffectType.SLEEP;
	}

	/** Notify started */
	@Override
	public void onStart()
	{
		getEffected().startSleeping();
	}

	/** Notify exited */
	@Override
	public void onExit()
	{
		getEffected().stopSleeping(this);
	}

	@Override
	public boolean onActionTime()
	{
		getEffected().stopSleeping(this);
		// just stop this effect
		return false;
	}
}
