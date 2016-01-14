package com.raxdenstudios.square.fragment.module.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.raxdenstudios.square.fragment.ModularFragment;
import com.raxdenstudios.square.fragment.module.GridViewModule;
import com.raxdenstudios.square.fragment.module.manager.ModuleFragmentImpl;

/**
 * Created by agomez on 03/06/2015.
 */
public class GridViewModuleImpl extends ModuleFragmentImpl implements AbsListView.OnScrollListener, GridViewModule.GridViewModuleListener {

    private static final String TAG = GridViewModuleImpl.class.getSimpleName();

    final private Handler mHandler = new Handler();

    final private Runnable mRequestFocus = new Runnable() {
        public void run() {
            mGrid.focusableViewAvailable(mGrid);
        }
    };

    final private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            onGridItemClick((GridView)parent, v, position, id);
        }
    };

    ListAdapter mAdapter;
    GridView mGrid;
    View mEmptyView;
    TextView mStandardEmptyView;
    CharSequence mEmptyText;
    boolean mGridShown;

    private GridViewModule mCallbacks;

    public GridViewModuleImpl(ModularFragment fragment) {
        if (!(fragment instanceof GridViewModule)) {
            throw new IllegalStateException("Fragment must implement GridViewModule.");
        }
        mCallbacks = (GridViewModule)fragment;
    }

    @Override
    public void onModuleCreate(Context context, Bundle savedInstanceState) {
        super.onModuleCreate(context, savedInstanceState);

        if (mCallbacks != null) mCallbacks.onGridViewModuleLoaded(this);
    }

    @Override
    public void onModuleViewCreated(Context context, View view, Bundle savedInstanceState) {
        super.onModuleViewCreated(context, view, savedInstanceState);
        ensureGrid();
    }

    @Override
    public void onModuleDestroyView(Context context) {
        super.onModuleDestroyView(context);

        mHandler.removeCallbacks(mRequestFocus);
        mGrid = null;
        mGridShown = false;
        mEmptyView = null;
        mStandardEmptyView = null;
    }

    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getGridView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param g The {@link GridView} where the click happened
     * @param v The view that was clicked within the GridView
     * @param position The position of the view in the list
     * @param id The row id of the item that was clicked
     */
    public void onGridItemClick(GridView g, View v, int position, long id) {
        if (mCallbacks != null) mCallbacks.onItemClick(g, v, position, id);
    }

    /**
     * Provide the cursor for the list view.
     */
    @Override
    public void setListAdapter(ListAdapter adapter) {
        boolean hadAdapter = mAdapter != null;
        mAdapter = adapter;
        if (mGrid != null) {
            mGrid.setAdapter(adapter);
            if (!mGridShown && !hadAdapter) {
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
        ensureGrid();
        mGrid.setSelection(position);
    }

    /**
     * Get the position of the currently selected list item.
     */
    @Override
    public int getSelectedItemPosition() {
        ensureGrid();
        return mGrid.getSelectedItemPosition();
    }

    /**
     * Get the cursor row ID of the currently selected list item.
     */
    @Override
    public long getSelectedItemId() {
        ensureGrid();
        return mGrid.getSelectedItemId();
    }

    /**
     * Get the activity's list view widget.
     */
    @Override
    public GridView getGridView() {
        ensureGrid();
        return mGrid;
    }

    /**
     * The default content for a ListFragment has a TextView that can
     * be shown when the list is empty.  If you would like to have it
     * shown, call this method to supply the text it should use.
     */
    @Override
    public void setEmptyText(CharSequence text) {
        ensureGrid();
        if (mStandardEmptyView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        mStandardEmptyView.setText(text);
        if (mEmptyText == null) {
            mGrid.setEmptyView(mStandardEmptyView);
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
        ensureGrid();
        if (mGridShown == shown) {
            return;
        }
        mGridShown = shown;
        if (shown) {
            if (mCallbacks != null) mCallbacks.onProgressHide();
        } else {
            if (mCallbacks != null) mCallbacks.onProgressShow("");
        }
    }

    /**
     * Get the ListAdapter associated with this activity's GridView.
     */
    @Override
    public ListAdapter getListAdapter() {
        return mAdapter;
    }

    private void ensureGrid() {
        if (mGrid != null) {
            return;
        }
        View root = mCallbacks != null ? ((ModularFragment)mCallbacks).getView() : null;
        if (root == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        mGrid = mCallbacks != null ? mCallbacks.onLoadGridView() : null;
        mEmptyView = mCallbacks != null ? mCallbacks.onLoadEmptyGridView() : null;
        if (mGrid != null) {
            if (mEmptyView != null) {
                mGrid.setEmptyView(mEmptyView);
            }
            mGridShown = true;
            mGrid.setOnItemClickListener(mOnClickListener);
            mGrid.setOnScrollListener(this);
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