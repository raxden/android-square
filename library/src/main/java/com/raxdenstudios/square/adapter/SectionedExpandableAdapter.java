package com.raxdenstudios.square.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by agomez on 03/08/2015.
 */
@Deprecated
public class SectionedExpandableAdapter<O, T> extends BaseExpandableListAdapter {

    private static final String TAG = SectionedExpandableAdapter.class.getSimpleName();

    private Context mContext;
    private LayoutInflater mInflater;
    private int mGroupResource;
    private int mChildResource;
    private int mGroupFieldId = 0;
    private int mChildFieldId = 0;
    private int mLastExpandedGroupPosition;

    private List<O> mGroupObjects;
    private Map<O, List<T>> mObjects;

    public SectionedExpandableAdapter(Context context, int groupResource, int childResource) {
        init(context, groupResource, childResource, 0, 0, null);
    }

    public SectionedExpandableAdapter(Context context, int groupResource, int childResource, int textViewGroupResourceId, int textViewChildResourceId) {
        init(context, groupResource, childResource, textViewGroupResourceId, textViewChildResourceId, null);
    }

    public SectionedExpandableAdapter(Context context, int groupResource, int childResource, int textViewGroupResourceId, int textViewChildResourceId, Map<O, List<T>> items) {
        init(context, groupResource, childResource, textViewGroupResourceId, textViewChildResourceId, items);
    }

    private void init(Context context, int groupResource, int childResource, int textViewGroupResourceId, int textViewChildResourceId, Map<O, List<T>> items) {
        mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mGroupResource = groupResource;
        mChildResource = childResource;
        mGroupFieldId = textViewGroupResourceId;
        mChildFieldId = textViewChildResourceId;
        mObjects = items;
        mGroupObjects = new ArrayList<>(mObjects.keySet());
    }

    @Override
    public T getChild(int groupPosition, int childPosition) {
        return mObjects.get(mGroupObjects.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return createChildViewFromResource(groupPosition, childPosition, isLastChild, convertView, parent, mChildResource);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mObjects.get(mGroupObjects.get(groupPosition)).size();
    }

    @Override
    public O getGroup(int groupPosition) {
        return mGroupObjects.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mObjects.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return createGroupViewFromResource(groupPosition, isExpanded, convertView, parent, mGroupResource);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public Context getContext() {
        return mContext;
    }

    private View createGroupViewFromResource(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent, int resource) {
        View view;
        TextView text;

        if (convertView == null) {
            view = mInflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        try {
            if (mGroupFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
                text = (TextView) view;
            } else {
                //  Otherwise, find the TextView field within the layout
                text = (TextView) view.findViewById(mGroupFieldId);
            }
        } catch (ClassCastException e) {
            Log.e(TAG, "You must supply a resource ID for a TextView");
            throw new IllegalStateException("MenuExpandableListAdapter requires the resource ID to be a TextView", e);
        }

        Object groupItem = getGroup(groupPosition);
        if (groupItem instanceof CharSequence) {
            text.setText((CharSequence)groupItem);
        } else {
            text.setText(groupItem.toString());
        }

        return view;
    }


    private View createChildViewFromResource(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent, int resource) {
        View view;
        TextView text;

        if (convertView == null) {
            view = mInflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        try {
            if (mChildFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
                text = (TextView) view;
            } else {
                //  Otherwise, find the TextView field within the layout
                text = (TextView) view.findViewById(mChildFieldId);
            }
        } catch (ClassCastException e) {
            Log.e(TAG, "You must supply a resource ID for a TextView");
            throw new IllegalStateException("MenuExpandableListAdapter requires the resource ID to be a TextView", e);
        }

        Object childItem = getChild(groupPosition, childPosition);
        if (childItem instanceof CharSequence) {
            text.setText((CharSequence)childItem);
        } else {
            text.setText(childItem.toString());
        }

        return view;
    }
}
