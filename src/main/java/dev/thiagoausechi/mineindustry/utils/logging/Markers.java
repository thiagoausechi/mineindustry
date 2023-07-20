package dev.thiagoausechi.mineindustry.utils.logging;

import dev.thiagoausechi.mineindustry.References;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Markers {
    public static final Marker ROOT = marker();

    public static final Marker LOADING = marker("LOADING");

    private static Marker marker() {
        return MarkerManager.getMarker(References.NAME);
    }

    public static Marker marker(String name) {
        return MarkerManager.getMarker(References.NAME + "-" + name).addParents(ROOT);
    }

    public static Marker marker(String name, Marker parent) {
        return MarkerManager.getMarker(References.NAME + "-" + name).addParents(parent);
    }
}
