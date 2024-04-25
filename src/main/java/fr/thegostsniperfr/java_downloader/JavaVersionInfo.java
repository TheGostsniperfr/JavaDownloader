package fr.thegostsniperfr.java_downloader;

import fr.thegostsniperfr.java_downloader.distribution.JavaType;
import fr.thegostsniperfr.java_toolbox.distribution.ArchType;
import fr.thegostsniperfr.java_toolbox.distribution.OsType;

public class JavaVersionInfo {
    private final String javaVersion;
    private final JavaType javaType;
    private final OsType osType;
    private final ArchType archType;
    private final boolean withJavaFx;

    public JavaVersionInfo(String javaVersion, JavaType javaType, OsType osType, ArchType archType, boolean withJavaFx) {
        this.javaVersion = javaVersion;
        this.javaType = javaType;
        this.osType = osType;
        this.archType = archType;
        this.withJavaFx = withJavaFx;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public JavaType getJavaType() {
        return javaType;
    }

    public OsType getOsType() {
        return osType;
    }

    public ArchType getArchType() {
        return archType;
    }

    public boolean isWithJavaFx() {
        return withJavaFx;
    }

    public String getArchiveType() {
        return this.getOsType() == OsType.WINDOWS ? "zip" : "tar.gz";
    }

    public String getArchiveName() {
        return "java_" + this.getJavaType().getType()+ "_" + this.getJavaVersion() + "_archive." + this.getArchiveType();
    }
}
