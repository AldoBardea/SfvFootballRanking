package ch.keo.sfvfootballranking.controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import ch.keo.sfvfootballranking.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
	    R.drawable.pic_01_afv,
	    R.drawable.pic_02_fvbj,
	    R.drawable.pic_03_ifv,
	    R.drawable.pic_04_fvnws,
	    R.drawable.pic_05_ofv,
            R.drawable.pic_06_sofv,
            R.drawable.pic_07_fvrz,
            R.drawable.pic_08_ftc,
            R.drawable.pic_09_aff,
	    R.drawable.pic_10_acgf,
	    R.drawable.pic_11_anf,
	    R.drawable.pic_12_acvf,
            R.drawable.pic_13_avf
    };
}