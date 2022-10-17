package shalini.mvvm.vsco.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import shalini.mvvm.vsco.data.VSCOResponse;
import shalini.mvvm.vsco.data.VSCOService;
import shalini.mvvm.vsco.model.Hit;

/**
 * The viewmodel that is used by VSCO activity. The recycler view is tied to it
 *
 */
@HiltViewModel
public class VSCOViewModel extends ViewModel {

    // The Live Data that is bound to the view. This is observed by the VSCOListFragment to display data
    public MutableLiveData <Boolean> hitsLabel = new MutableLiveData<>();
    public MutableLiveData <Boolean> hitsProgress = new MutableLiveData<>();
    public MutableLiveData <Boolean> hitRecycler = new MutableLiveData<>();

    private List<Hit> hitArrayList = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    // For dependency injection the VSCO service cannot be declared as private
    VSCOService vscoService;

    /**
     * Constructor. The VSCOservice is injected into the viewmodel. There is no need to
     * pass context
     * @param vscoService
     */
    @Inject
    public VSCOViewModel(VSCOService vscoService){
        this.vscoService = vscoService;
    }

    /**
     * Initialize the views and make the call to the rest endpoint
     *
     */
    public void refreshHits(String queryString) {
        initializeViews();
        fetchHitList(queryString);
    }

    /**
     * This just sets up hiding and displaying of initial views
     */
    public void initializeViews() {
        hitsProgress.setValue(true);
        hitsLabel.setValue(false);
        hitRecycler.setValue(false);
    }

    /**
     * This method can return make the rest API call and the rx observer is setup.
     * By default the querystring is empty
     * @param queryString
     */

    public void fetchHitList(String queryString) {
        Disposable disposable = vscoService.fetchHits(true, queryString)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VSCOResponse>() {
                    @Override
                    public void accept(VSCOResponse vscoResponse) {
                        if(vscoResponse != null && vscoResponse.getHitList() != null) {
                            changeHitsDataSet(vscoResponse.getHitList());
                            // This would display the list of pictures
                            if(vscoResponse.getHitList().size() > 0) {
                                hitsLabel.setValue(false);
                                hitsProgress.setValue(false);
                                hitRecycler.setValue(true);
                            }else{
                                // If no pictures found a label displaying an error is displayed
                                hitRecycler.setValue(false);
                                hitsProgress.setValue(false);
                                hitsLabel.setValue(true);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        //In case there is an error that happens when the rest endpoint called.
                        // A default error message is displayed
                        hitsLabel.setValue(true);
                        hitRecycler.setValue(false);
                        hitsProgress.setValue(false);
                        //TODO: Need to define a customException class or use some sort of Logging.Is is dangerous to show stacktrace
                        throwable.printStackTrace();
                    }
                });

        compositeDisposable.add(disposable);
    }

    /**
     * Update employList once data is fetched
     * @param hits
     */
    private void changeHitsDataSet(List<Hit> hits) {
        hitArrayList.clear();
        hitArrayList.addAll(hits);
    }

    /**
     * Default getter for hit list
     */
    public List<Hit> getHitList() {
        return hitArrayList;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();

    }
}
