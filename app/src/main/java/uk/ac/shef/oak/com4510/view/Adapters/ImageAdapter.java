package uk.ac.shef.oak.com4510.view.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


import java.util.List;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.model.models.VisitImages;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<VisitImages> images;



    private ImageViewClickListener listener;

    public interface ImageViewClickListener{
     void onImageViewClick(int position, VisitImages image);
    }
    public void setListener(ImageViewClickListener listener) {
        this.listener = listener;
    }


    public ImageAdapter (Context context, List<VisitImages> images){
        this.context=context;
        this.images=images;
    }
    public void refreshImageData(List<VisitImages>images){
        this.images=images;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(context);
        Bitmap bitmap= BitmapFactory.decodeByteArray(images.get(position).getImage(),0,images.get(position).getImage().length);
        //Database Image
//                imageView.setImageBitmap(bitmap);
        //Dummy Image
        imageView.setImageResource(R.drawable.b1);
        imageView.setTag(position);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340,350));
        //On each Image Click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition=(int) v.getTag();
                VisitImages clickedVisitImage= images.get(clickedPosition);
                if (listener!=null){
                    listener.onImageViewClick(clickedPosition,clickedVisitImage);
                }
            }
        });

        return imageView;
    }
}
