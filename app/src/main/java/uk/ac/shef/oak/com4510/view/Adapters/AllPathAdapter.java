package uk.ac.shef.oak.com4510.view.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.shef.oak.com4510.model.Objects.PathItem;
import uk.ac.shef.oak.com4510.R;

public class AllPathAdapter extends RecyclerView.Adapter<AllPathAdapter.AllPathViewHolder>{
    private List<PathItem> pathItemList;
    private Context context;
    private ImageAdapter.ImageViewClickListener imageListener;

    public AllPathAdapter(List<PathItem> pathItemList, Context context,ImageAdapter.ImageViewClickListener listener){
        this.pathItemList=pathItemList;
        this.context=context;
        this.imageListener=listener;

    }

    public void refreshData(List<PathItem> pathItemList){
        this.pathItemList=pathItemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AllPathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_all_path_images,parent,false);
        AllPathViewHolder viewHolder=new AllPathViewHolder(view);
        return viewHolder;
    }
    //Cell
    @Override
    public void onBindViewHolder(@NonNull AllPathViewHolder holder, int position) {
        PathItem currentItem=pathItemList.get(position);
        holder.textView1.setText(currentItem.getVisit().getDate());
        holder.textView2.setText(currentItem.getVisit().getTitle());
        ImageAdapter imageAdapter= new ImageAdapter(context,currentItem.getImageList());
        imageAdapter.setListener(imageListener);
        holder.gridView.setAdapter(imageAdapter);
    }


    @Override
    public int getItemCount() {
        return pathItemList.size();
    }
    public static class AllPathViewHolder extends RecyclerView.ViewHolder{
        public TextView textView1;
        public TextView textView2;
        public GridView gridView;


        public AllPathViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.PathDateView);
            textView2=itemView.findViewById(R.id.PathNameView);
            gridView=itemView.findViewById(R.id.grid2_view);
        }
    }
}

