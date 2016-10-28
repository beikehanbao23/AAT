package ch.bailu.aat.activities;


import android.app.Notification;
import android.content.ComponentName;
import android.os.Bundle;

import ch.bailu.aat.helpers.AppLog;
import ch.bailu.aat.services.ServiceContext;
import ch.bailu.aat.services.ServiceLink;
import ch.bailu.aat.services.background.BackgroundService;
import ch.bailu.aat.services.cache.CacheService;
import ch.bailu.aat.services.dem.ElevationService;
import ch.bailu.aat.services.directory.DirectoryService;
import ch.bailu.aat.services.icons.IconMapService;
import ch.bailu.aat.services.tileremover.TileRemoverService;
import ch.bailu.aat.services.tracker.TrackerService;

public abstract class AbsServiceLink extends AbsActivity {


    private ServiceLink serviceLink=null;

    private enum State {
        destroyed,
        created,
        resumed,
        serviceUp
    }

    private State state=State.destroyed;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = State.created;
        
        serviceLink = new ServiceLink(this) {



            @Override
            public void onServiceUp() {
                if (state == State.resumed) {
                    onResumeWithService();
                    state = State.serviceUp;
                }
            }
            
        };
    }



    
    @Override
    public void onResume() {
        super.onResume();
        state = State.resumed;
        serviceLink.up();
    }

    
    @Override
    public void onPause() {
        if (state == State.serviceUp) {
            onPauseWithService();
        }
        serviceLink.down();

        state = State.created;
        super.onPause();
    }
    
    
    public void onResumeWithService() {}
    public void onPauseWithService() {}


    @Override
    public void onDestroy() {
        serviceLink.close();
        serviceLink=null;
        state = State.destroyed;
        super.onDestroy();
    }    


    public ServiceContext getServiceContext() {
        return serviceLink;
    }
}
