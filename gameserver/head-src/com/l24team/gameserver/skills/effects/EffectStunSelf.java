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
 * [URL]http://www.gnu.org/copyleft/gpl.html[/URL]
 */
package com.l24team.gameserver.skills.effects;

import com.l24team.gameserver.model.L2Effect;
import com.l24team.gameserver.skills.Env;

public class EffectStunSelf extends L2Effect
{
	public EffectStunSelf(final Env env, final EffectTemplate template)
	{
		super(env, template);
	}

	@Override
	public EffectType getEffectType()
	{
		return EffectType.STUN_SELF;
	}

	@Override
	public void onStart()
	{
		if (getEffector() != null)
		{
			getEffector().startStunning();
		}
	}

	@Override
	public void onExit()
	{
		if (getEffector() != null)
		{
			getEffector().stopStunning(this);
		}
	}

	@Override
	public boolean onActionTime()
	{
		// just stop this effect
		return false;
	}
}
