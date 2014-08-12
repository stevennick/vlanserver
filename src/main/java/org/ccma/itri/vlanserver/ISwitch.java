package org.ccma.itri.vlanserver;

import java.util.List;

import org.ccma.itri.vlanserver.model.Vlan;

public interface ISwitch {

	/**
	 * Create a new vlan instance
	 * 
	 * @param name
	 * @param id
	 * @param switchIp
	 * @return Vlan object with vlan information
	 * @throws Exception
	 */
	public Vlan createNewVlan(String name, int id, String switchIp) throws Exception;

	/**
	 * Create a new vlan instance
	 * 
	 * @see #createNewVlan(String, int, String)
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public Vlan createNewVlan(Vlan source) throws Exception;

	/**
	 * Get Vlan list from this switch
	 * 
	 * @param switchIp
	 * @return
	 * @throws Exception
	 */
	public List<Vlan> getAllVLan(String switchIp) throws Exception;

	/**
	 * Get Vlan by ID;
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Vlan getVlanById(Long id) throws Exception;
	
	/**
	 * Update Vlan attributes by ID
	 * @param newVlan new attributes with vlan ID
	 * @return updated Vlan Object
	 * @throws Exception
	 */
	public Vlan updateVlanById(Vlan newVlan) throws Exception;

	/**
	 * Delete Vlan by ID
	 * @param parseLong
	 * @return
	 * @throws Exception
	 */
	public boolean deleteVlanById(Long id) throws Exception;
}
