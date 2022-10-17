package shalini.mvvm.vsco.data;

import com.google.gson.annotations.SerializedName;

import shalini.mvvm.vsco.model.Hit;

import java.util.List;

public class VSCOResponse {

    @SerializedName("hits")
    private List<Hit> hitList;

    public List<Hit> getHitList() {
        return hitList;
    }
}
