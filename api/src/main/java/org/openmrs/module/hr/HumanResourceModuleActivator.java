/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.hr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Role;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.Activator;
import org.openmrs.util.OpenmrsConstants;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class HumanResourceModuleActivator implements Activator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * @see org.openmrs.module.Activator#startup()
	 */
	public void startup() {
		Set<Role> inheritedRoleSet=new HashSet<Role>();
		inheritedRoleSet.add(Context.getUserService().getRole(OpenmrsConstants.AUTHENTICATED_ROLE));
		UserService us = Context.getUserService();
		Role humanResourceManager = us.getRole("Human Resource Manager");
		// Create role if it doesn't exist
		if (humanResourceManager == null){
			humanResourceManager=new Role("Human Resource Manager", "HR Role");
			humanResourceManager.setInheritedRoles(inheritedRoleSet);
			us.saveRole(humanResourceManager); 
		}
		Role humanResourceClerk=us.getRole("Human Resource Clerk");
		// Create role if it doesn't exist
		if (humanResourceClerk == null ){
		 humanResourceClerk=new Role("Human Resource Clerk", "HR Role");
		 humanResourceClerk.setInheritedRoles(inheritedRoleSet);
		 us.saveRole(humanResourceClerk);
		 } 
		log.info("Starting Human Resource Module");
	}
	
	/**
	 * @see org.openmrs.module.Activator#shutdown()
	 */
	public void shutdown() {
		UserService us = Context.getUserService();
		Role humanResourceManager = us.getRole("Human Resource Manager");
		Role humanResourceClerk=us.getRole("Human Resource Clerk");
		if(humanResourceManager!=null){
			us.purgeRole(humanResourceManager);
		}
		if(humanResourceClerk!=null){
			us.purgeRole(humanResourceClerk);
		}
		log.info("Shutting down Human Resource Module");
	}
	
}

