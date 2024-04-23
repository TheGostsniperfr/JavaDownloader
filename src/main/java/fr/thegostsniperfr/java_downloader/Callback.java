package fr.thegostsniperfr.java_downloader;

public interface Callback {

    void onStep(Step step);

    enum Step {
        FETCHING,
        DOWNLOADING,
        EXTRACTING,
        DONE;
    }
}
