/*******************************************************************************
 * Copyright (c) 2010, 2015 Information and Communications Research Laboratories
 * Industrial Technology Research Institute (ITRI) and others.
 * All rights reserved. 
 *
 * This program and the accompanying materials are made available under the terms of the
 * ITRI Copyright License v1 which accompanies this distribution, and is available at
 * http://www.itri.org.tw/eng/econtent/copyright/copyright01.aspx
 *
 * Authors:
 *     (c) 2010, 2015 Yi-Fu Ciou <stevennick@gmail.com>
 *     And all corresponding contributors.
 *******************************************************************************/
package org.ccma.itri.vlanserver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.ccma.itri.vlanserver.worker.ProcessWorker;

public class Starter extends HttpServlet {

	public Starter() {

	}

	@Override
	public void init() throws ServletException {
		// Do required initialization
		// Do project init here...
		ProcessWorker worker = ProcessWorker.getInstance();
		System.out.println("Please notice that ProcessWorker object id shown here, than you can access");
		System.out.println("the same object later by access /rest/vlan/xxx/demo through web browser");
		System.out.println(worker);
	}
}
