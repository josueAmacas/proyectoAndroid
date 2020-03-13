package com.example.macasjosue.Vista.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macasjosue.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfowindowsAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindows;
    private Context context;

    public InfowindowsAdapter(Context context) {
        this.context = context;
        mWindows = LayoutInflater.from(context).inflate(R.layout.item_mapa,null);
    }

    private void rendowWindow(Marker marker, View view){
        String title = marker.getTitle();
        TextView tvTitle = (TextView) view.findViewById(R.id.lblTituloInfo);

        if(!title.equals("")){
            tvTitle.setText(title);
        }

        String description = marker.getSnippet();
        TextView tvDesc = (TextView) view.findViewById(R.id.lblDescripcionInfo);

        if(!description.equals("")){
            tvDesc.setText(description);
        }


        ImageView imageView = view.findViewById(R.id.imgMap);

    }

    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindow(marker,mWindows);
        return mWindows;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindow(marker,mWindows);
        return mWindows;
    }
}
