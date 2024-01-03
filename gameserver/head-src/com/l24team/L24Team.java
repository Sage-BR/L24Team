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
package com.l24team;

import org.apache.log4j.Logger;

public class L24Team
{
	private static final Logger LOGGER = Logger.getLogger(L24Team.class);

	/**
	 * L2-4Team Info
	 */
	public static void info()

	{
		LOGGER.info("                                                       ");
		LOGGER.info("          ###  #####  #####     ##     ##   ##         ");
		LOGGER.info("         ####  #####  ##       ####    ### ###         ");
		LOGGER.info("        ## ##	 ###   #####   ##  ##   #######         ");
		LOGGER.info("       ######   ###   ##     ########  ## # ##         ");
		LOGGER.info("          ###	 ###   #####  ###  ###  ##   ##         ");
		LOGGER.info("             	   		        Frozen Forever!         ");
	}
}