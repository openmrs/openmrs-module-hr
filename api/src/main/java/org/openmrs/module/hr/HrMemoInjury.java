package org.openmrs.module.hr;

public class HrMemoInjury extends HrMemoBase<HrStaff> {

    private String type;

    private HrStaff hrStaff;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HrStaff getHrStaff() {
        return hrStaff;
    }

    public void setHrStaff(HrStaff hrStaff) {
        this.hrStaff = hrStaff;
    }
}
