/*******************************************************************************
 * Copyright (c) 2010, 2014 Cloud Computing Center for Mobile Applications, 
 * Industrial Technology Research Institute (ITRI-CCMA) and others.
 * All rights reserved. 
 *
 * This program and the accompanying materials are made available under the terms of the
 * ITRI Copyright License v1 which accompanies this distribution, and is available at
 * http://www.itri.org.tw/eng/econtent/copyright/copyright01.aspx
 *
 * Authors:
 *     (c) 2011-2014 Yi-Fu Ciou <stevennick@gmail.com>
 *     And all corresponding contributors.
 *******************************************************************************/
package org.ccma.itri.vlanserver.worker;

/**
 * Demo ProcessWorker singleton pattern for object reuse.
 * 
 * <br/> For more information, refer "singleton pattern" in design patterns.
 * @author Yi-Fu Ciou
 *
 */
public class ProcessWorker {

	
	private static ProcessWorker singleton;
	
	/**
	 * The ProcessWorker constructor, prevent access outside by declare as private function.
	 */
	private ProcessWorker() {
		// Do object initialize process here
	}
	
	/**
	 * Use this to initialize this class.
	 */
	private synchronized static void createInstance() {
		if(singleton == null) {
			singleton = new ProcessWorker();
		}
	}
	
	/**
	 * Get ProcessWorker instance, will create object at first creation, this instance will keep alive under web application life cycle for reuse.
	 * @return
	 */
	public static ProcessWorker getInstance() {
		if(singleton == null) {
			createInstance();
		}
		return singleton;
	}
	
	// TODO: Do wherever you wants...
}
