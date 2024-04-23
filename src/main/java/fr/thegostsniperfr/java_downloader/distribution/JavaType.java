package fr.thegostsniperfr.java_downloader.distribution;

public enum JavaType {
    JRE("jre"),
    JDK("jdk");

    private final String javaType;

    JavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getType() {
        return javaType;
    }
}
