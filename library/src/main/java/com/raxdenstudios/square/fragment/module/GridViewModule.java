package com.raxdenstudios.square.fragment.module;

import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by agomez on 03/06/2015.
 */
public interface GridViewModule {

    void onGridViewModuleLoaded(GridViewModuleListener module);

    GridView onLoadGridView();
    View onLoadEmptyGridView();

    void onItemClick(GridView GridView, View view, int position, long id);
    void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
    void onScrollStateChanged(AbsListView view, int scrollState);

    void onProgressShow(String progressLabel);
    void onProgressHide();

    interface GridViewModuleListener {
        void setListAdapter(ListAdapter adapter);
        void setSelection(int position);
        int getSelectedItemPosition();
        long getSelectedItemId();
        GridView getGridView();
        void setEmptyText(CharSequence text);
        void setListShown(boolean shown);
        void setListShownNoAnimation(boolean shown);
        ListAdapter getListAdapter();
    }

}
