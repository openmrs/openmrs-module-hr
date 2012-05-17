package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;



public class HrIscoCodes  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codeIsco;
     private String title;
     private String definition;
     private String tasksInclude;
     private String includedOccupations;
     private String excludedOccupations;
     private String notes;
     private Set<HrJobTitle> hrJobTitles = new HashSet<HrJobTitle>(0);


    // Constructors

    /** default constructor */
    public HrIscoCodes() {
    }

	/** minimal constructor */
    public HrIscoCodes(String codeIsco, String title) {
        this.codeIsco = codeIsco;
        this.title = title;
    }
    
    /** full constructor */
    public HrIscoCodes(String codeIsco, String title, String definition, String tasksInclude, String includedOccupations, String excludedOccupations, String notes, Set<HrJobTitle> hrJobTitles) {
        this.codeIsco = codeIsco;
        this.title = title;
        this.definition = definition;
        this.tasksInclude = tasksInclude;
        this.includedOccupations = includedOccupations;
        this.excludedOccupations = excludedOccupations;
        this.notes = notes;
        this.hrJobTitles = hrJobTitles;
    }
    

   
    // Property accessors

    public String getCodeIsco() {
        return this.codeIsco;
    }
    
    public void setCodeIsco(String codeIsco) {
        this.codeIsco = codeIsco;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefinition() {
        return this.definition;
    }
    
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getTasksInclude() {
        return this.tasksInclude;
    }
    
    public void setTasksInclude(String tasksInclude) {
        this.tasksInclude = tasksInclude;
    }
    public String getIncludedOccupations() {
		return includedOccupations;
	}

	public void setIncludedOccupations(String includedOccupations) {
		this.includedOccupations = includedOccupations;
	}
  
	public String getExcludedOccupations() {
        return this.excludedOccupations;
    }
    
   	public void setExcludedOccupations(String excludedOccupations) {
        this.excludedOccupations = excludedOccupations;
    }

    public String getNotes() {
        return this.notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<HrJobTitle> getHrJobTitles() {
        return this.hrJobTitles;
    }
    
    public void setHrJobTitles(Set<HrJobTitle> hrJobTitles) {
        this.hrJobTitles = hrJobTitles;
    }
   








}
