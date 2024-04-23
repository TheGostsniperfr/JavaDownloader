package fr.thegostsniperfr.distribution;

public enum ArchType {
    X86("x86"),
    X64("x64"),
    AMD64("amd64"),
    ARM("arm"),
    AARCH64("aarch64"),
    AARCH32("aarch32"),
    AARCH32SF("aarch32sf"),
    AARCH32HF("aarch32hf"),
    PPC("ppc"),
    PPC64("ppc64"),
    PPC32("ppc32"),
    PPC32HF("ppc32hf"),
    PPC32SPE("ppc32spe"),
    SPARC("sparc"),
    SPARC64("sparc64"),
    SPARC32("sparc32"),
    SPARCV9("sparcv9"),
    SPARCV9_64("sparcv9-64"),
    SPARCV9_32("sparcv9-32");

    private final String archType;

    ArchType(String archType) {
        this.archType = archType;
    }

    public String getType() {
        return archType;
    }
}
