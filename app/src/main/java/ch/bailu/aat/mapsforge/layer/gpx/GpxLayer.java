package ch.bailu.aat.mapsforge.layer.gpx;

import android.content.SharedPreferences;

import org.mapsforge.core.graphics.Canvas;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.Point;

import ch.bailu.aat.dispatcher.OnContentUpdatedInterface;
import ch.bailu.aat.gpx.GpxInformation;
import ch.bailu.aat.gpx.GpxList;
import ch.bailu.aat.mapsforge.layer.MapsForgeLayer;
import ch.bailu.aat.views.map.AbsOsmView;

public abstract class GpxLayer extends MapsForgeLayer implements OnContentUpdatedInterface {
    private final int color;

    private GpxList gpxList=GpxList.NULL_ROUTE;


    public GpxLayer(int c) {
        color = c;
    }


    @Override
    public void onContentUpdated(int iid, GpxInformation info) {
        setTrack(info.getGpxList());
    }

    private void setTrack(GpxList gpx) {
        if (gpx == null) gpxList = GpxList.NULL_ROUTE;
        else gpxList = gpx;
    }

    public GpxList getGpxList() {
        return gpxList;
    }

    public int getColor() {
        return color;
    }

    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b) {}


}
