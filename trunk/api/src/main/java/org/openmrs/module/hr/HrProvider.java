package org.openmrs.module.hr;

import java.util.HashSet;
import java.util.Set;



public class HrProvider  implements java.io.Serializable {


    // Fields    

     private int providerId;
     private Set hrStaffs = new HashSet(0);


    // Constructors

    /** default constructor */
    public HrProvider() {
    }

	/** minimal constructor */
    public HrProvider(int providerId) {
        this.providerId = providerId;
    }
    
    /** full constructor */
    public HrProvider(int providerId, Set hrStaffs) {
        this.providerId = providerId;
        this.hrStaffs = hrStaffs;
    }
    

   
    // Property accessors

    public int getProviderId() {
        return this.providerId;
    }
    
    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public Set getHrStaffs() {
        return this.hrStaffs;
    }
    
    public void setHrStaffs(Set hrStaffs) {
        this.hrStaffs = hrStaffs;
    }
   








}
