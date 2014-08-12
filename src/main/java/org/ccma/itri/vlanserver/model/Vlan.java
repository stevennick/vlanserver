package org.ccma.itri.vlanserver.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vlan implements Comparable<Vlan>  {
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
