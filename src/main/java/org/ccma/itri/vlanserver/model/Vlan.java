/*******************************************************************************
 * Copyright (c) 2010, 2014 Information and Communications Research Laboratories
 * Industrial Technology Research Institute (ITRI) and others.
 * All rights reserved. 
 *
 * This program and the accompanying materials are made available under the terms of the
 * ITRI Copyright License v1 which accompanies this distribution, and is available at
 * http://www.itri.org.tw/eng/econtent/copyright/copyright01.aspx
 *
 * Authors:
 *     (c) 2010, 2014 Yi-Fu Ciou <stevennick@gmail.com>
 *     And all corresponding contributors.
 *******************************************************************************/
package org.ccma.itri.vlanserver.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vlan implements Comparable<Vlan> {
	private String name;
	/**
	 * Used to stroe Vlan ID
	 */
	private int vlanId;
	/**
	 * Index for Vlan object
	 */
	private Long id;
	private String switchIp;

	public Vlan() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVlanId() {
		return vlanId;
	}

	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}

	public String getSwitchIp() {
		return switchIp;
	}

	public void setSwitchIp(String switchIp) {
		this.switchIp = switchIp;
	}

	@Override
	public int compareTo(Vlan o) {
		return (int) (this.getId() - o.getId());
	}

}
