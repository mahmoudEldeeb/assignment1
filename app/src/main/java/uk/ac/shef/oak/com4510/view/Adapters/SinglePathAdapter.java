package uk.ac.shef.oak.com4510.view.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.shef.oak.com4510.model.Objects.PathItem;
import uk.ac.shef.oak.com4510.R;

public class SinglePathAdapter extends RecyclerView.Adapter<SinglePathAdapter.PathViewHolder> {
    private List<PathItem> pathItemList;
    private OnPathClickListener listener;

    public SinglePathAdapter(List<PathItem> pathItemList){
        this.pathItemList=pathItemList;

    }
    public void refreshData(List<PathItem> pathItemList){
        this.pathItemList=pathItemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.path_item,parent,false);
        PathViewHolder pathViewHolder=new PathViewHolder(view,listener);
        return pathViewHolder;
    }
    //Each Cell
    @Override
    public void onBindViewHolder(@NonNull PathViewHolder holder, int position) {
        PathItem currentItem=pathItemList.get(position);
        holder.textView1.setText(currentItem.getVisit().getDate());
        holder.textView2.setText(currentItem.getVisit().getTitle());
    }
    @Override
    public int getItemCount() {
        return pathItemList.size();
    }

    public interface OnPathClickListener{
        void onPathClick(int position);
    }
    public void setOnPathListener(OnPathClickListener listener){
        this.listener=listener;
    }

    public PathItem getClickedPath(int position){
        return pathItemList.get(position);
    }


    public static class PathViewHolder extends RecyclerView.ViewHolder{
        public TextView textView1;
        public TextView textView2;


        public PathViewHolder(@NonNull View itemView, final OnPathClickListener mlistener) {
            super(itemView);
            textView1=itemView.findViewById(R.id.DateView);
            textView2=itemView.findViewById(R.id.PathView);
            //On path click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener!=null){
                        int position=getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            mlistener.onPathClick(position);
                        }
                    }

                }
            });
        }
    }







}
