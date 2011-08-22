
package org.openmrs.module.hr;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Attributable;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsClassLoader;
import org.openmrs.util.OpenmrsUtil;



public class HrStaffAttribute extends BaseOpenmrsData implements java.io.Serializable,Comparable<HrStaffAttribute> {


	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(HrStaffAttribute.class);
	private int staffAttributeId;
     private HrStaffAttributeType hrStaffAttributeType;
     private HrStaff hrStaff;
     private String value;
     private String uuid;

  
    public HrStaffAttribute() {
    }

	
    public HrStaffAttribute(int staffAttributeId) {
        this.staffAttributeId = staffAttributeId;
    }
    
    
    public HrStaffAttribute(HrStaffAttributeType hrStaffAttributeType,String value) {
        this.hrStaffAttributeType = hrStaffAttributeType;
        this.value = value;
    }
    public boolean equals(Object obj) {
		if (obj instanceof HrStaffAttribute) {
			HrStaffAttribute attr = (HrStaffAttribute) obj;
			if (attr.getId() != 0 && getId() != 0)
				return attr.getId().equals(getId());
			
		}
		return this == obj;
	}
	
	public int hashCode() {
		if (this.getId() == null)
			return super.hashCode();
		int hash = 5;
		hash += 29 * hash + this.getId().hashCode();
		return hash;
	}
	public int compareTo(HrStaffAttribute other) {
		int retValue = 0;
		retValue = isVoided().compareTo(other.isVoided());
		if (retValue == 0)
			retValue = OpenmrsUtil.compareWithNullAsLatest(getDateCreated(), other.getDateCreated());
		if (retValue == 0)
			retValue = getHrStaffAttributeType().getId().compareTo(
			    other.getHrStaffAttributeType().getId());
		if (retValue == 0)
			retValue = OpenmrsUtil.compareWithNullAsGreatest(getValue(), other.getValue());
		if (retValue == 0)
			retValue = OpenmrsUtil.compareWithNullAsGreatest(getId(), other.getId());
		
		return retValue;
	}

    public int getStaffAttributeId() {
        return this.staffAttributeId;
    }
    
    public void setStaffAttributeId(int staffAttributeId) {
        this.staffAttributeId = staffAttributeId;
    }

    public HrStaffAttributeType getHrStaffAttributeType() {
        return this.hrStaffAttributeType;
    }
    
    public void setHrStaffAttributeType(HrStaffAttributeType hrStaffAttributeType) {
        this.hrStaffAttributeType = hrStaffAttributeType;
    }

    public HrStaff getHrStaff() {
        return this.hrStaff;
    }
    
    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public Integer getId() {
		return getStaffAttributeId();
	}

	public void setId(Integer id) {
		setStaffAttributeId(id);
	}
	public String toString() {
		Object o = getHydratedObject();
		if (o instanceof Attributable)
			return ((Attributable) o).getDisplayString();
		else if (o != null)
			return o.toString();
		
		return this.value;
	}
	
	@SuppressWarnings("unchecked")
	public Object getHydratedObject() {
		try {
			Class c = OpenmrsClassLoader.getInstance().loadClass(getHrStaffAttributeType().getFormat());
			try {
				Object o = c.newInstance();
				if (o instanceof Attributable) {
					Attributable attr = (Attributable) o;
					return attr.hydrate(getValue());
				}
			}
			catch (InstantiationException e) {
				// try to hydrate the object with the String constructor
				log.trace("Unable to call no-arg constructor for class: " + c.getName());
				Object o = c.getConstructor(String.class).newInstance(getValue());
				return o;
			}
		}
		catch (Throwable t) {
			log.warn("Unable to hydrate value: " + getValue() + " for type: " + getHrStaffAttributeType(), t);
		}
		
		log.debug("Returning value: '" + getValue() + "'");
		return getValue();
	}
	public void voidAttribute(String reason) {
		setVoided(true);
		setVoidedBy(Context.getAuthenticatedUser());
		setVoidReason(reason);
		setDateVoided(new Date());
	}
}
