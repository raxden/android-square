package com.raxdenstudios.square.fragment.module;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by agomez on 02/06/2015.
 */
public interface ListViewModule {

    void onListViewModuleLoaded(ListViewModuleListener module);

    ListView onLoadListView();
    View onLoadEmptyListView();

    void onItemClick(ListView listView, View view, int position, long id);
    void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
    void onScrollStateChanged(AbsListView view, int scrollState);

    void onProgressShow(String progressLabel);
    void onProgressHide();

    interface ListViewModuleListener {
        void setListAdapter(ListAdapter adapter);
        void setSelection(int position);
        int getSelectedItemPosition();
        long getSelectedItemId();
        ListView getListView();
        void setEmptyText(CharSequence text);
        void setListShown(boolean shown);
        void setListShownNoAnimation(boolean shown);
        ListAdapter getListAdapter();
    }

}
