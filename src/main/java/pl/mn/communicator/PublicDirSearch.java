/*
 * Copyright (c) 2003-2005 JGGApi Development Team. All Rights Reserved. This program is free software; you can
 * redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version. This program is distributed in
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pl.mn.communicator;

/**
 * This is an abstract class that is common for a query to Gadu-Gadu's public directory and reply from it.
 * <p>
 * Created on 2004-12-17
 * 
 * @author <a href="mailto:mati@sz.home.pl">Mateusz Szczap</a>
 * @version $Id: PublicDirSearch.java,v 1.1 2005/11/05 23:34:52 winnetou25 Exp $
 */
public abstract class PublicDirSearch {

	protected Integer m_uin = null;
	protected String m_firstName = null;
	protected String m_nickName = null;
	protected String m_birthYear = null;
	protected String m_city = null;
	protected String m_familyName = null;
	protected String m_familyCity = null;

	public Integer getUin() {
		return m_uin;
	}

	public void setUin(final Integer uin) {
		m_uin = uin;
	}

	public String getFirstName() {
		return m_firstName;
	}

	public void setFirstName(final String firstName) {
		m_firstName = firstName;
	}

	public String getBirthYear() {
		return m_birthYear;
	}

	public void setBirthYear(final String birthYear) {
		m_birthYear = birthYear;
	}

	public String getNickName() {
		return m_nickName;
	}

	public void setNickName(final String nickName) {
		m_nickName = nickName;
	}

	public String getCity() {
		return m_city;
	}

	public void setCity(final String city) {
		m_city = city;
	}

	public String getFamilyName() {
		return m_familyName;
	}

	public void setFamilyName(final String familyName) {
		m_familyName = familyName;
	}

	public void setFamilyCity(final String familyCity) {
		m_familyCity = familyCity;
	}

	public String getFamilyCity() {
		return m_familyCity;
	}

}
