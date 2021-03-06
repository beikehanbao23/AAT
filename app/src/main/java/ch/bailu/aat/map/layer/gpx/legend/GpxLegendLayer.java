package ch.bailu.aat.map.layer.gpx.legend;

import android.content.SharedPreferences;
import android.graphics.Color;

import org.mapsforge.core.model.Point;

import ch.bailu.aat.map.MapContext;
import ch.bailu.aat.map.layer.gpx.GpxLayer;

public class GpxLegendLayer extends GpxLayer {
    private final LegendWalker walker;


    public GpxLegendLayer(LegendWalker w) {
        super(Color.DKGRAY);
        walker=w;
    }

    @Override
    public void drawInside(MapContext mcontext) {
        walker.init(mcontext);
        walker.walkTrack(getGpxList());
    }

    @Override
    public boolean onTap(Point tapXY) {
        return false;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    @Override
    public void onAttached() {

    }

    @Override
    public void onDetached() {

    }
}
