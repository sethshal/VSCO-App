package shalini.mvvm.vsco.view;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import shalini.mvvm.vsco.R;

import shalini.mvvm.vsco.databinding.ItemVscoBinding;
import shalini.mvvm.vsco.model.Hit;
import shalini.mvvm.vsco.viewmodel.VSCOItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This class ties the data and the views together.
 */
public class VSCOAdapter extends RecyclerView.Adapter<VSCOAdapter.HitAdapterViewHolder> {

    // The list of pictures returned and need to be displayed
    private List<Hit> hitList;

    /**
     * Default constructor initializes the list
     */
    VSCOAdapter() {
        this.hitList =new ArrayList<>();
    }


    /**
     * This internal class binds the view to the recycler view. The specific hit item is inflated here
     * */

    @NonNull
    @Override
    public HitAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemVscoBinding itemVscoBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_vsco, parent, false);
        return new HitAdapterViewHolder(itemVscoBinding);

    }

    /**
     * The specific hit record is associated to the viewholder here
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(HitAdapterViewHolder holder, int position) {
        holder.bindHit(hitList.get(position));
    }

    @Override
    public int getItemCount() {
        return hitList.size();
    }

    /**
     * CLear the hit list if there was something there earlier and the set the new list.
     * The notifydatasetChanged is essential to redraw the UI
     * @param hitList
     */
    public void setHitList(List<Hit> hitList) {
        this.hitList.clear();
        this.hitList.addAll(hitList);
        notifyDataSetChanged();
    }

    /**
     * Create an inner viewHolder class specific to the ListAdapter
     */
    static class HitAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemVscoBinding binding;

        HitAdapterViewHolder(ItemVscoBinding binding) {
            super(binding.itemHit);
            this.binding = binding;
        }

        void bindHit(Hit hit) {
            if (binding.getVscoViewModel() == null) {
                binding.setVscoViewModel(new VSCOItemViewModel(hit));
            } else {
                binding.getVscoViewModel().setHit(hit);
            }
        }
    }
}
