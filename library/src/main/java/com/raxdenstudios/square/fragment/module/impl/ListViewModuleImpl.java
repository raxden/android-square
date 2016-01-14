package com.raxdenstudios.square.fragment.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.raxdenstudios.square.fragment.ModularFragment;
import com.raxdenstudios.square.fragment.module.ListViewModule;
import com.raxdenstudios.square.fragment.module.manager.ModuleFragmentImpl;

/**
 * Created by agomez on 02/06/2015.
 */
public class ListViewModuleImpl extends ModuleFragmentImpl implements AbsListView.OnScrollListener, ListViewModule.ListViewModuleListener {

    private static final String TAG = ListViewModuleImpl.class.getSimpleName();

    final private Handler mHandler = new Handler();

    final private Runnable mRequestFocus = new Runnable() {
        public void run() {
            mList.focusableViewAvailable(mList);
        }
    };

    final private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            onListItemClick((ListView)parent, v, position, id);
        }
    };

    ListAdapter mAdapter;
    ListView mList;
    View mEmptyView;
    TextView mStandardEmptyView;
    CharSequence mEmptyText;
    boolean mListShown;

    private ListViewModule mCallbacks;

    public ListViewModuleImpl(ModularFragment fragment) {
        if (!(fragment instanceof ListViewModule)) {
            throw new IllegalStateException("Fragment must implement ListViewModule.");
        }
        mCallbacks = (ListViewModule)fragment;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        if (mCallbacks != null) mCallbacks.onListViewModuleLoaded(this);
    }

    @Override
    public void onModuleViewCreated(Context context, View view, Bundle savedInstanceState) {
        super.onModuleViewCreated(context, view, savedInstanceState);
        ensureList();
    }

    @Override
    public void onModuleDestroyView(Context context) {
        super.onModuleDestroyView(context);

        mHandler.removeCallbacks(mRequestFocus);
        mList = null;
        mListShown = false;
        mEmptyView = null;
        mStandardEmptyView = null;
    }

    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getListView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l The ListView where the click happened
     * @param v The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id The row id of the item that was clicked
     */
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (mCallbacks != null) mCallbacks.onItemClick(l, v, position, id);
    }

    /**
     * Provide the cursor for the list view.
     */
    @Override
    public void setListAdapter(ListAdapter adapter) {
        boolean hadAdapter = mAdapter != null;
        mAdapter = adapter;
        if (mList != null) {
            mList.setAdapter(adapter);
            if (!mListShown && !hadAdapter) {
                // The list was hidden, and previously didn't have an
                // adapter.  It is now time to show it.
                setListShown(true, ((ModularFragment)mCallbacks).getView().getWindowToken() != null);
            }
        }
    }

    /**
     * Set the currently selected list item to the specified
     * position with the adapter's data
     *
     * @param position
     */
    @Override
    public void setSelection(int position) {
        ensureList();
        mList.setSelection(position);
    }

    /**
     * Get the position of the currently selected list item.
     */
    @Override
    public int getSelectedItemPosition() {
        ensureList();
        return mList.getSelectedItemPosition();
    }

    /**
     * Get the cursor row ID of the currently selected list item.
     */
    @Override
    public long getSelectedItemId() {
        ensureList();
        return mList.getSelectedItemId();
    }

    /**
     * Get the activity's list view widget.
     */
    @Override
    public ListView getListView() {
        ensureList();
        return mList;
    }

    /**
     * The default content for a ListFragment has a TextView that can
     * be shown when the list is empty.  If you would like to have it
     * shown, call this method to supply the text it should use.
     */
    @Override
    public void setEmptyText(CharSequence text) {
        ensureList();
        if (mStandardEmptyView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        mStandardEmptyView.setText(text);
        if (mEmptyText == null) {
            mList.setEmptyView(mStandardEmptyView);
        }
        mEmptyText = text;
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * <p>Applications do not normally need to use this themselves.  The default
     * behavior of ListFragment is to start with the list not being shown, only
     * showing it once an adapter is given with {@link #setListAdapter(ListAdapter)}.
     * If the list at that point had not been shown, when it does get shown
     * it will be do without the user ever seeing the hidden state.
     *
     * @param shown If true, the list view is shown; if false, the progress
     * indicator.  The initial value is true.
     */
    @Override
    public void setListShown(boolean shown) {
        setListShown(shown, true);
    }

    /**
     * Like {@link #setListShown(boolean)}, but no animation is used when
     * transitioning from the previous state.
     */
    @Override
    public void setListShownNoAnimation(boolean shown) {
        setListShown(shown, false);
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * @param shown If true, the list view is shown; if false, the progress
     * indicator.  The initial value is true.
     * @param animate If true, an animation will be used to transition to the
     * new state.
     */
    private void setListShown(boolean shown, boolean animate) {
        ensureList();
        if (mListShown == shown) {
            return;
        }
        mListShown = shown;
        if (shown) {
            if (mCallbacks != null) mCallbacks.onProgressHide();
        } else {
            if (mCallbacks != null) mCallbacks.onProgressShow("");
        }
    }

    /**
     * Get the ListAdapter associated with this activity's ListView.
     */
    @Override
    public ListAdapter getListAdapter() {
        return mAdapter;
    }

    private void ensureList() {
        if (mList != null) {
            return;
        }
        View root = mCallbacks != null ? ((ModularFragment)mCallbacks).getView() : null;
        if (root == null) {
            throw new IllegalStateException("Content view not yet created");
        }

        mList = mCallbacks != null ? mCallbacks.onLoadListView() : null;
        mEmptyView = mCallbacks != null ? mCallbacks.onLoadEmptyListView() : null;
        if (mList != null) {
            if (mEmptyView != null) {
                mList.setEmptyView(mEmptyView);
            }
            mListShown = true;
            mList.setOnItemClickListener(mOnClickListener);
            mList.setOnScrollListener(this);
            if (mAdapter != null) {
                ListAdapter adapter = mAdapter;
                mAdapter = null;
                setListAdapter(adapter);
            } else {
                // We are starting without an adapter, so assume we won't
                // have our data right away and start with the progress indicator.
                setListShown(false, false);
            }
            mHandler.post(mRequestFocus);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mCallbacks != null) mCallbacks.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mCallbacks != null) mCallbacks.onScrollStateChanged(view, scrollState);
    }

}
