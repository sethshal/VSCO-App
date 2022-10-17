package shalini.mvvm.vsco.viewmodel;

import androidx.databinding.BaseObservable;

import shalini.mvvm.vsco.model.Hit;


/**
 * The viewmodel that is tied to each hit record.
 * Very basic class to display each hit row
 *
 */
public class VSCOItemViewModel extends BaseObservable {

    private Hit hit;

    public VSCOItemViewModel(Hit hit) {
        this.hit = hit;
    }

    public void setHit(Hit hit) {
        this.hit = hit;
        notifyChange();
    }

    /**
     * Getters that display the individual hit details
     * @return
     */
    public String getThumbnail() {
        return hit.getPreviewURL();
    }

    public String getProfileImage() {
        return hit.getUserImageURL();
    }

    public String getName(){
        return hit.getUser();
    }


}
