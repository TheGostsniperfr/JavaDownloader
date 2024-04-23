package fr.thegostsniperfr.distribution;

public enum OsType {
    WINDOWS("windows"),
    MACOS("macos"),
    LINUX("linux"),
    LINUX_MUSL("linux-musl"),
    LINUX_GLIBC("linux-glibc"),
    QNX("qnx"),
    SOLARIS("solaris"),
    AIX("aix");

    private final String osType;

    OsType(String osType) {
        this.osType = osType;
    }

    public String getType() {
        return osType;
    }
}
