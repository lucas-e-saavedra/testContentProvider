package com.example.lucassaavedra.testcontent;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Lucas.Saavedra on 24/1/2017.
 */

public class MyCursorAdapter extends CursorAdapter{
    public MyCursorAdapter(Context context){
        super(context, null, 0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_content, viewGroup, false);
        return itemView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvName = (TextView) view.findViewById(R.id.tvItenName);
        //tvName.setText(cursor.getString(cursor.getColumnIndex()));
        tvName.setText(cursor.getString(2));
    }
}
