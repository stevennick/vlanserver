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
package org.ccma.itri.vlanserver.coreswitch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.ccma.itri.vlanserver.ISwitch;
import org.ccma.itri.vlanserver.model.Vlan;

public class CiscoSwitch implements ISwitch {

	private static SortedMap<Long, Vlan> vlanTable;

	public CiscoSwitch() {
		if (vlanTable == null) {
			initializeTable();
		}
	}

	/**
	 * Initialize fake vlan table.
	 */
	private void initializeTable() {
		vlanTable = new TreeMap<Long, Vlan>();

		for (int index = 0; index < 10; index++) {
			Vlan vlan = new Vlan();
			vlan.setId(Integer.valueOf(index + 1).longValue());
			vlan.setName("MyVlan-" + (index + 1));
			vlan.setVlanId(index);
			vlan.setSwitchIp("192.168.1." + (index + 1));

			vlanTable.put(vlan.getId(), vlan);
		}
	}

	@Override
	public Vlan createNewVlan(String name, int id, String switchIp) throws Exception {
		// TODO call library or implement your switch operations.
		Vlan vlan = new Vlan();
		vlan.setName(name);
		vlan.setVlanId(1000 + id);
		vlan.setSwitchIp(switchIp);
		vlan.setId(Integer.valueOf(vlanTable.size() + 1).longValue());
		vlanTable.put(vlan.getId(), vlan);
		return vlan;
	}

	@Override
	public Vlan createNewVlan(Vlan source) throws Exception {
		// TODO call library or implement your switch operations.
		source.setId(Integer.valueOf(vlanTable.size() + 1).longValue());
		vlanTable.put(source.getId(), source);
		return source;
	}

	@Override
	public List<Vlan> getAllVLan(String switchIp) throws Exception {
		// TODO implement your switch operations.
		Collection<Vlan> vlanContent = vlanTable.values();
		List<Vlan> vlanList = new ArrayList<Vlan>();
		vlanList.addAll(vlanContent);
		return vlanList;
	}

	@Override
	public Vlan getVlanById(Long id) throws Exception {
		// TODO implement your switch operations.
		return vlanTable.get(id);
	}

	@Override
	public boolean deleteVlanById(Long id) throws Exception {
		// TODO implement your switch operations.
		return vlanTable.remove(id) != null;
	}

	@Override
	public Vlan updateVlanById(Vlan newVlan) throws Exception {
		// TODO implement your switch operstions.
		Vlan original = vlanTable.get(newVlan.getId());
		if (original == null) {
			return null;
		} else {
			original = (Vlan) this.copyAttributesPOJO(original, newVlan);
			return vlanTable.put(original.getId(), original);
		}
	}

	/**
	 * Copy non null attributes from update object to original object. Use
	 * getter/setter to modify object content.
	 * 
	 * @param original
	 *            The original object that will updated
	 * @param update
	 *            The source object with same object type and contains updated
	 *            values
	 * @return Modified original object
	 * @throws Exception
	 */
	private Object copyAttributesPOJO(Object original, Object update) throws Exception {
		Method[] mList = update.getClass().getMethods();
		for (Method method : mList) {
			if (method.getName().startsWith("get")) {
				if (method.getName().endsWith("getClass"))
					continue;
				Object[] nullParameter = {};
				Object result = method.invoke(update, nullParameter);
				if (result != null) {
					// Update original object by invoke setter
					Method setter = original.getClass().getMethod("set" + method.getName().substring(3),
							method.getReturnType());
					Object[] parameter = { result };
					setter.invoke(original, parameter);
				}
			}
		}
		return original;
	}

}
