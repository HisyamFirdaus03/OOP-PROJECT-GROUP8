package license;

public class MotorcycleLicense extends License {
    
    public MotorcycleLicense(String licenseNo, String expDate, boolean competentLicense, LicenseType licenseType){
        super(licenseNo, expDate, competentLicense, licenseType);
    }

    public void displayDetails(){
        System.out.println();
        System.out.println("Motorcycle License Information");
        System.out.printf("%-14s: %s%n", "License No", getLicenseNo());
        System.out.printf("%-14s: %s%n", "Expire Date", getExpDate());
        System.out.printf("%-14s: %s%n", "CDL License", String.valueOf(isCompetentLicense()));
        System.out.printf("%-14s: %s%n", "License Type", getLicenseType().toString());
        System.out.println();
    }
}