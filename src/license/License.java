package license;

public abstract class License {

    private String licenseNo;
    private String expDate;
    private boolean competentLicense;
    private LicenseType licenseType;


    public License(String licenseNo, String expDate, boolean competentLicense, LicenseType licenseType){
        this.licenseNo = licenseNo;
        this.expDate = expDate;
        this.competentLicense = competentLicense;
        this.licenseType = licenseType;
    }

    public String getLicenseNo(){
        return licenseNo;
    }
    
    public String getExpDate(){
        return expDate;
    }
    
    public boolean isCompetentLicense(){
        return competentLicense;
    }
    
    public LicenseType getLicenseType(){
        return licenseType;
    }

    public void setLicenseNo(String licenseNo){
        this.licenseNo = licenseNo;
    }

    public void setExpDate(String expDate){
        this.expDate = expDate;
    }
    
    public void isCompetentLicense(boolean competentLicense){
        this.competentLicense = competentLicense; 
    }
    
    public void setLicenseType(LicenseType licenseType){
        this.licenseType = licenseType;
    }

    public abstract void displayDetails();
}
