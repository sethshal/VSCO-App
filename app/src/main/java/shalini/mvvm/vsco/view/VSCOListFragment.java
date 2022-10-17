package shalini.mvvm.vsco.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import dagger.hilt.android.AndroidEntryPoint;
import shalini.mvvm.vsco.R;
import shalini.mvvm.vsco.databinding.VscoListFragmentBinding;
import shalini.mvvm.vsco.viewmodel.VSCOViewModel;

@AndroidEntryPoint
public class VSCOListFragment extends Fragment {

    public static VSCOListFragment newInstance() {
        return new VSCOListFragment();
    }

    private VSCOViewModel viewModel;

    private VscoListFragmentBinding binding;

    private VSCOAdapter vscoAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.vsco_list_fragment,container,false);
        return binding.getRoot();
    }

    /**
     * All the binding initializations happen here
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataBinding();
        setupToolbar();
        setupListHitsView(binding.recyclerHits);
        setUpSearch();
        setupRefresh();
        viewModel.refreshHits("");
        observeViewModel();
    }

    /**
     * The toolbar is specifically used to display the search bar
     */
    private void setupToolbar(){
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * The searchView is used to conduct the search in the application
     */
    private void setUpSearch() {
        binding.searchImage.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Override onQueryTextSubmit method which is call when submit query is searched
            @Override
            public boolean onQueryTextSubmit(String queryString) {
                viewModel.fetchHitList(queryString);
                // If the list contains the search query than filter the adapter
                // using the filter method with the query as its argument
                return false;
            }

            // This method is overridden to filter the adapter according
            // to a search query when the user is typing search
            @Override
            public boolean onQueryTextChange(String queryString) {
                viewModel.fetchHitList(queryString);
                return false;
            }
        });
    }

    /**
     * This is the UI element that refreshes the view. The swipetorefresh android UI
     * element is used to force a refresh and hit the rest endpoint. There is a handler
     * here that dismisses the refresh UI 3 seconds after called so as to keep the UI clean
     */
    private void setupRefresh() {
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String query = binding.searchImage.getQuery() != null ? binding.searchImage.getQuery().toString() : "";
                viewModel.refreshHits(query);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        binding.refreshLayout.setRefreshing(false);
                    }
                }, 3000); // Delay in millis

            }
        });
    }

    /**
     *  The UI is bound here. And the view model class initialized
     */
    private void initDataBinding() {
        viewModel = new ViewModelProvider(this).get(VSCOViewModel.class);
        binding.setMainViewModel(viewModel);
    }

    /**
     * The adapter is tied to the recyclerview here. RecyclerView is a preferred UI renderer for long lists
     * as it is a lot more efficient and reuses cells rather than redrawing them
     * @param recyclerHits
     */
    private void setupListHitsView(RecyclerView recyclerHits) {
        vscoAdapter = new VSCOAdapter();
        recyclerHits.setAdapter(vscoAdapter);
        recyclerHits.setHasFixedSize(true);
    }


    /**
     * Any changes that happen are observed in this method and the UI rendered accordingly.
     */
    private void observeViewModel() {
        // This looks for changes to the hit object. I would have revisited this and optimized the hiding and
        // displaying of variables to make it more reusable
        viewModel.hitRecycler.observe(getActivity(), hitRecycler -> {
            showHits(hitRecycler);
        });
        viewModel.hitsLabel.observe(getActivity(), hitsLabel ->{
            displayErrorLabel(hitsLabel);
        });
        viewModel.hitsProgress.observe(getActivity(), hitsProgress ->{
            trackProgress(hitsProgress);
        });
    }

    /**
     * If the data is being retrieved the the progress bar is displayed. It is hidden once retrieved
     * @param progressBar
     */
    private void trackProgress(Boolean progressBar) {
        if(progressBar != null) {
            binding.progressBar.setVisibility(progressBar ? View.VISIBLE : View.GONE);
            if(progressBar) {
                binding.labelStatus.setVisibility(View.GONE);
                binding.recyclerHits.setVisibility(View.GONE);
            }
        }
    }

    /**
     * This is used to decide if the error label will be hidden or not
     * @param hitsLabel
     */
    private void displayErrorLabel(Boolean hitsLabel) {
        if(hitsLabel != null) {
            binding.labelStatus.setVisibility(hitsLabel ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * This method refreshes the adapter object with the list of hits. It hides elements not needed
     * @param hitsRecycler
     */
    private void showHits(Boolean hitsRecycler) {
        if (hitsRecycler != null && hitsRecycler) {
            VSCOAdapter vscoAdapter = (VSCOAdapter) binding.recyclerHits.getAdapter();
            if (vscoAdapter != null) {
                binding.recyclerHits.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.GONE);
                binding.labelStatus.setVisibility(View.GONE);
                vscoAdapter.setHitList(viewModel.getHitList());
            }
        }
    }

}