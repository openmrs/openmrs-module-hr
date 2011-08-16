package org.openmrs.module.hr;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsUtil;
import org.springframework.util.StringUtils;



public class HrStaff extends BaseOpenmrsData implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int staffId;
     private Concept staffStatus;
     private Date initialHireDate;
     private String uuid;
     private Set<HrAssignment> hrAssignments;
     private Set<HrStaffNote> hrStaffNotes;
     private Set<HrStaffAttribute> hrStaffAttributes = new TreeSet<HrStaffAttribute>();
     private Set<HrEducation> hrEducations;
     private Set<HrEvaluation> hrEvaluations;
     private Set<HrStaffCert> hrStaffCerts;
     private Set<HrPostHistory> hrPostHistories;


    // Constructors

    /** default constructor */
    public HrStaff() {
    }

	/** minimal constructor */
    public HrStaff(int staffId, Date initialHireDate) {
        this.staffId = staffId;
        this.initialHireDate = initialHireDate;
    }
    
    /** full constructor */
    public HrStaff(int staffId, Concept staffStatus, Date initialHireDate,String uuid, Set<HrAssignment> hrAssignments,Set<HrStaffNote> hrStaffNotes, Set<HrStaffAttribute> hrStaffAttributes, Set<HrEducation> hrEducations, Set<HrEvaluation> hrEvaluations, Set<HrStaffCert> hrStaffCerts, Set<HrPostHistory> hrPostHistories) {
        this.staffId = staffId;
        this.staffStatus = staffStatus;
        this.initialHireDate = initialHireDate;
        this.uuid = uuid;
        this.hrAssignments = hrAssignments;
        this.hrStaffNotes = hrStaffNotes;
        this.hrStaffAttributes = hrStaffAttributes;
        this.hrEducations = hrEducations;
        this.hrEvaluations = hrEvaluations;
        this.hrStaffCerts = hrStaffCerts;
        this.hrPostHistories = hrPostHistories;
    }

    public int getStaffId() {
        return this.staffId;
    }
    
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
    
    public Concept getStaffStatus() {
        return this.staffStatus;
    }
    
    public void setStaffStatus(Concept staffStatus) {
        this.staffStatus = staffStatus;
    }

    public Date getInitialHireDate() {
        return this.initialHireDate;
    }
    
    public void setInitialHireDate(Date initialHireDate) {
        this.initialHireDate = initialHireDate;
    }

    
    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<HrAssignment> getHrAssignments() {
        return this.hrAssignments;
    }
    
    public void setHrAssignments(Set<HrAssignment> hrAssignments) {
        this.hrAssignments = hrAssignments;
    }

     public Set<HrStaffNote> getHrStaffNotes() {
        return this.hrStaffNotes;
    }
    
    public void setHrStaffNotes(Set<HrStaffNote> hrStaffNotes) {
        this.hrStaffNotes = hrStaffNotes;
    }

    public Set<HrStaffAttribute> getHrStaffAttributes() {
        return this.hrStaffAttributes;
    }
    
    public void setHrStaffAttributes(Set<HrStaffAttribute> hrStaffAttributes) {
        this.hrStaffAttributes = hrStaffAttributes;
    }

    public Set<HrEducation> getHrEducations() {
        return this.hrEducations;
    }
    
    public void setHrEducations(Set<HrEducation> hrEducations) {
        this.hrEducations = hrEducations;
    }

    public Set<HrEvaluation> getHrEvaluations() {
        return this.hrEvaluations;
    }
    
    public void setHrEvaluations(Set<HrEvaluation> hrEvaluations) {
        this.hrEvaluations = hrEvaluations;
    }

    public Set<HrStaffCert> getHrStaffCerts() {
        return this.hrStaffCerts;
    }
    
    public void setHrStaffCerts(Set<HrStaffCert> hrStaffCerts) {
        this.hrStaffCerts = hrStaffCerts;
    }

    public Set<HrPostHistory> getHrPostHistories() {
        return this.hrPostHistories;
    }
    
    public void setHrPostHistories(Set<HrPostHistory> hrPostHistories) {
        this.hrPostHistories = hrPostHistories;
    }

	public Integer getId() {
		return getStaffId();
	}

	public void setId(Integer id) {
		setStaffId(id);
	}

	public String getGender() {
		return Context.getPersonService().getPerson(staffId).getGender();
		
	}
	
	public void setGender(String gender) {
		Context.getPersonService().getPerson(staffId).setGender(gender);
	}

	public Date getBirthdate() {
		return Context.getPersonService().getPerson(staffId).getBirthdate();
	}

	public void setBirthdate(Date birthdate) {
		Context.getPersonService().getPerson(staffId).setBirthdate(birthdate);
	}

	public Boolean isBirthdateEstimated() {
		return Context.getPersonService().getPerson(staffId).getBirthdateEstimated();
	}

	public Boolean getBirthdateEstimated() {
		return isBirthdateEstimated();
	}
	

	public void setBirthdateEstimated(Boolean birthdateEstimated) {
		Context.getPersonService().getPerson(staffId).setBirthdateEstimated(birthdateEstimated);
	}
	

	public Boolean isDead() {
		return Context.getPersonService().getPerson(staffId).getDead();
	}

	public Boolean getDead() {
		return isDead();
	}
	

	public void setDead(Boolean dead) {
		Context.getPersonService().getPerson(staffId).setDead(dead);
	}

	public Date getDeathDate() {
		return Context.getPersonService().getPerson(staffId).getDeathDate();
	}

	public void setDeathDate(Date deathDate) {
		Context.getPersonService().getPerson(staffId).getDeathDate();
	}
	

	public Concept getCauseOfDeath() {
		return Context.getPersonService().getPerson(staffId).getCauseOfDeath();
	}

	public void setCauseOfDeath(Concept causeOfDeath) {
		Context.getPersonService().getPerson(staffId).setCauseOfDeath(causeOfDeath);
	}
	

	public Set<PersonAddress> getAddresses() {
		Set<PersonAddress> addresses=Context.getPersonService().getPerson(staffId).getAddresses();
		if (addresses == null)
			addresses = new TreeSet<PersonAddress>();
		return addresses;
	}
	
	
	public void setAddresses(Set<PersonAddress> addresses) {
		Context.getPersonService().getPerson(staffId).setAddresses(addresses);
	}
	

	public Set<PersonName> getNames() {
		Set<PersonName> names=Context.getPersonService().getPerson(staffId).getNames();
		if (names == null)
			names = new TreeSet<PersonName>();
		return names;
	}
	
	public void setNames(Set<PersonName> names) {
		Context.getPersonService().getPerson(staffId).setNames(names);
	}
	public PersonName getPersonName() {
		// normally the DAO layer returns these in the correct order, i.e. preferred and non-voided first, but it's possible that someone
		// has fetched a Person, changed their names around, and then calls this method, so we have to be careful.
		if (getNames() != null && getNames().size() > 0) {
			for (PersonName name : getNames()) {
				if (name.isPreferred() && !name.isVoided())
					return name;
			}
			for (PersonName name : getNames()) {
				if (!name.isVoided())
					return name;
			}
			return null;
		}
		return null;
	}

	public Set<HrStaffAttribute> getAttributes() {
		if (hrStaffAttributes == null)
			hrStaffAttributes = new TreeSet<HrStaffAttribute>();
		return this.hrStaffAttributes;
	}
	
	public List<HrStaffAttribute> getActiveAttributes() {
		List<HrStaffAttribute> attrs = new Vector<HrStaffAttribute>();
		for (HrStaffAttribute attr : getAttributes()) {
			if (!attr.isVoided())
				attrs.add(attr);
		}
		return attrs;
	}
	
	
	public void setAttributes(Set<HrStaffAttribute> hrStaffAttributes) {
		this.hrStaffAttributes = hrStaffAttributes;
	}
	
	public void addAttribute(HrStaffAttribute newAttribute) {
		newAttribute.setHrStaff(this);
		boolean newIsNull = !StringUtils.hasText(newAttribute.getValue());
		
		for (HrStaffAttribute currentAttribute : getActiveAttributes()) {
			if (currentAttribute.equals(newAttribute))
				return; // if we have the same HrStaffAttributeId, don't add the new attribute
			else if (currentAttribute.getHrStaffAttributeType().equals(newAttribute.getHrStaffAttributeType())) {
				if (currentAttribute.getValue() != null && currentAttribute.getValue().equals(newAttribute.getValue()))
					// this staff already has this attribute
					return;
				
				// if the to-be-added attribute isn't already voided itself
				// and if we have the same type, different value
				if (newAttribute.isVoided() == false || newIsNull) {
					if (currentAttribute.getCreator() != null)
						currentAttribute.voidAttribute("New value: " + newAttribute.getValue());
					else
						// remove the attribute if it was just temporary (didn't have a creator
						// attached to it yet)
						removeAttribute(currentAttribute);
				}
			}
		}
		if (!OpenmrsUtil.collectionContains(hrStaffAttributes, newAttribute) && !newIsNull)
			hrStaffAttributes.add(newAttribute);
	}
	public void removeAttribute(HrStaffAttribute attribute) {
		if (hrStaffAttributes != null)
			hrStaffAttributes.remove(attribute);
			
	}
	public HrStaffAttribute getAttribute(HrStaffAttributeType sat) {
		if (sat != null)
			for (HrStaffAttribute attribute : getAttributes()) {
				if (sat.equals(attribute.getHrStaffAttributeType()) && !attribute.isVoided()) {
					return attribute;
				}
			}
		return null;
	}
	public HrStaffAttribute getAttribute(String attributeName) {
		if (attributeName != null)
			for (HrStaffAttribute attribute : getAttributes()) {
				HrStaffAttributeType type = attribute.getHrStaffAttributeType();
				if (type != null && attributeName.equals(type.getName()) && !attribute.isVoided()) {
					return attribute;
				}
			}
		
		return null;
	}
	public HrStaffAttribute getAttribute(Integer attributeTypeId) {
		for (HrStaffAttribute attribute : getActiveAttributes()) {
			if (attributeTypeId.equals(attribute.getHrStaffAttributeType().getStaffAttributeTypeId()) && !attribute.isVoided()) {
				return attribute;
			}
		}
		return null;
	}
	public List<HrStaffAttribute> getAttributes(String attributeName) {
		List<HrStaffAttribute> ret = new Vector<HrStaffAttribute>();
		
		for (HrStaffAttribute attribute : getActiveAttributes()) {
			HrStaffAttributeType type = attribute.getHrStaffAttributeType();
			if (type != null && attributeName.equals(type.getName()) && !attribute.isVoided()) {
				ret.add(attribute);
			}
		}
		
		return ret;
	}
	public List<HrStaffAttribute> getAttributes(Integer attributeTypeId) {
		List<HrStaffAttribute> ret = new Vector<HrStaffAttribute>();
		
		for (HrStaffAttribute attribute : getActiveAttributes()) {
			if (attributeTypeId.equals(attribute.getHrStaffAttributeType().getStaffAttributeTypeId()) && !attribute.isVoided()) {
				ret.add(attribute);
			}
		}
		
		return ret;
	}
	public List<HrStaffAttribute> getAttributes(HrStaffAttributeType hrStaffAttributeType) {
		List<HrStaffAttribute> ret = new Vector<HrStaffAttribute>();
		for (HrStaffAttribute attribute : getAttributes()) {
			if (hrStaffAttributeType.equals(attribute.getHrStaffAttributeType()) && !attribute.isVoided()) {
				ret.add(attribute);
			}
		}
		return ret;
	}
	
}
