package uk.ac.shef.oak.com4510.view.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.databinding.VisitRowBinding;
import uk.ac.shef.oak.com4510.model.models.Visit;

/**
 *
 */
public class VisitsAdapter extends RecyclerView.Adapter<VisitsAdapter.ViewHolder> {
    private List<Visit> visitsItemList;
    private OnVisitClickListener listener;
Context context;

    /**
     *
     * @param list
     */
    public VisitsAdapter(List<Visit> list){
        this.visitsItemList=list;

    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VisitRowBinding visitRowBinding;
        context=parent.getContext();
        visitRowBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.visit_row,parent,false);

        return new ViewHolder(visitRowBinding) ;
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.visitRowBinding.title.setText(visitsItemList.get(position).getTitle());
        holder.visitRowBinding.desc.setText(visitsItemList.get(position).getDesscription());
        holder.visitRowBinding.date.setText(visitsItemList.get(position).getDate());

        holder.visitRowBinding.temp.setText(visitsItemList.get(position).getTemp()+"");
        holder.visitRowBinding.barometer.setText(visitsItemList.get(position).getPressure()+"");
        listener= (OnVisitClickListener) context;
        holder.visitRowBinding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUpdateClick(visitsItemList.get(position));
            }
        });
        holder.visitRowBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(visitsItemList.get(position));
            }
        });
    }

    /**
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return visitsItemList.size();
    }

    public interface OnVisitClickListener{
        void onUpdateClick(Visit visit);
        void onDeleteClick(Visit visit);
    }

    /**
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{

        VisitRowBinding visitRowBinding;

        public ViewHolder(@NonNull VisitRowBinding itemView) {
            super(itemView.getRoot());
            this.visitRowBinding=itemView;
        }
    }
    }

