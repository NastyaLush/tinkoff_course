package edu.project3.output;

public enum OutputType {
    MARKDOWN("md"), ADOC("adoc");
    private final String extension;

    OutputType(String ext) {
        this.extension = ext;
    }

    @Override
    public String toString() {
        return extension;
    }
}
