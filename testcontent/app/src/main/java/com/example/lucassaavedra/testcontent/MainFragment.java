package com.example.lucassaavedra.testcontent;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Lucas.Saavedra on 24/1/2017.
 */

public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    ListView lvMain;
    MyCursorAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        lvMain = (ListView) rootView.findViewById(R.id.lvMainList);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new MyCursorAdapter(getContext());
        lvMain.setAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            getLoaderManager().destroyLoader(0);
            if (mAdapter != null){
                mAdapter.changeCursor(null);
                mAdapter = null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        String AUTHORITY = "com.example.android.testing.notes.NotesProvider";
        String ACTIVIDAD = "mynotes";
        Uri uriContentProvider = Uri.parse("content://" + AUTHORITY + "/" + ACTIVIDAD);
        return new CursorLoader(getContext(), uriContentProvider, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mAdapter.swapCursor(null);
    }
}
