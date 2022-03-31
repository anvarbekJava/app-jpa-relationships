package uz.pdp.appjparelationships.main;

public enum SvetaforEnum {
    RED("Stop"),
    YELLOW("Wait"),
    GREEN("Go");
    private String nameEn;

    SvetaforEnum(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameEn() {
        return nameEn;
    }
}
