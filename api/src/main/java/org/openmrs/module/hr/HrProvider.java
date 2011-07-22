package org.openmrs.module.hr;




public class HrProvider  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int providerId;


    // Constructors

    /** default constructor */
    public HrProvider() {
    }

    
    /** full constructor */
    public HrProvider(int providerId) {
        this.providerId = providerId;
    }
    

   
    // Property accessors

    public int getProviderId() {
        return this.providerId;
    }
    
    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
   








}
