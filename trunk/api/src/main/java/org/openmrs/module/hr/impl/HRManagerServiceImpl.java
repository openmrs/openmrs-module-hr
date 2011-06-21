package org.openmrs.module.hr.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.hr.HRManagerService;
import org.openmrs.module.hr.db.HRDAO;

public class HRManagerServiceImpl implements HRManagerService{
	private Log log = LogFactory.getLog(getClass());
	private HRDAO dao;
	public void setDao(HRDAO dao)
	{
		this.dao = dao;
	}
}
