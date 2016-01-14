package com.raxdenstudios.square.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SectionedAdapter<O, T> extends BaseAdapter {

	private static int TYPE_SECTION_HEADER = 0;

    private Context mContext;
    private LayoutInflater mInflater;
    private int mResource;
    private int mFieldId = 0;

    private Map<O, ArrayAdapter<T>> mObjects;

	public SectionedAdapter(Context context, int resource) {
        init(context, resource, 0, null);
	}

    public SectionedAdapter(Context context, int resource, int textViewResourceId) {
        init(context, resource, textViewResourceId, null);
    }

    public SectionedAdapter(Context context, int resource, int textViewResourceId, Map<O, ArrayAdapter<T>> objects) {
        init(context, resource, textViewResourceId, objects);
    }

    private void init(Context context, int resource, int textViewResourceId, Map<O, ArrayAdapter<T>> objects) {
        mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResource = resource;
        mObjects = objects;
        mFieldId = textViewResourceId;
    }

	@Override
	public Object getItem(int position) {
        for (Map.Entry<O, ArrayAdapter<T>> entry : mObjects.entrySet()) {
            if (position == 0) {
                return entry;
            }
            ArrayAdapter<T> adapter = entry.getValue();
            int size = adapter.getCount() + 1;
            if (position < size) {
                return (adapter.getItem(position-1));
            }
            position -= size;
        }
        return null;
	}
	
	public ArrayList<T> getItems() {
        ArrayList<T> items = new ArrayList<T>();
        for (Map.Entry<O, ArrayAdapter<T>> entry : mObjects.entrySet()) {
            ArrayAdapter<T> adapter = entry.getValue();
            for (int position = 0; position < adapter.getCount(); position++) {
                items.add(adapter.getItem(position));
            }
        }
        return items;
	}

	public void clear() {
        if (mObjects != null && !mObjects.isEmpty()) {
            for (Map.Entry<O, ArrayAdapter<T>> entry : mObjects.entrySet()) {
                ArrayAdapter<T> adapter = entry.getValue();
                adapter.clear();
            }
            mObjects.clear();
        }
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int sectionIndex = 0;
        for (Map.Entry<O, ArrayAdapter<T>> entry : mObjects.entrySet()) {
            if (position == 0) {
                return getHeaderView(sectionIndex, convertView, parent);
            }
            ArrayAdapter<T> adapter = entry.getValue();
            int size = adapter.getCount() + 1;
            if (position < size) {
                return (adapter.getView(position - 1, convertView, parent));
            }
            position -= size;
            sectionIndex++;
        }
        return null;
	}	
	
	protected View getHeaderView(int headerPosition, View convertView, ViewGroup parent) {
        return createViewFromResource(headerPosition, convertView, parent, mResource);
    }

    private View createViewFromResource(int headerPosition, View convertView, ViewGroup parent, int resource) {
        View view;
        TextView text;

        if (convertView == null) {
            view = mInflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        try {
            if (mFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
                text = (TextView) view;
            } else {
                //  Otherwise, find the TextView field within the layout
                text = (TextView) view.findViewById(mFieldId);
            }
        } catch (ClassCastException e) {
            Log.e("SectionedAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException("SectionedAdapter requires the resource ID to be a TextView", e);
        }

        Object item = getHeaderSection(headerPosition);
        if (item instanceof CharSequence) {
            text.setText((CharSequence)item);
        } else {
            text.setText(item.toString());
        }

        return view;
    }

	@Override
	public int getCount() {
		int total = 0;
        if (mObjects != null && !mObjects.isEmpty()) {
            for (Map.Entry<O, ArrayAdapter<T>> entry : mObjects.entrySet()) {
                ArrayAdapter<T> adapter = entry.getValue();
                total += adapter.getCount() + 1; // add one for header
            }
        }
		return total;
	}
	
	public int getViewTypeCount() {
		int total = 1;	// one for the header, plus those from sections
        if (mObjects != null && !mObjects.isEmpty()) {
            for (Map.Entry<O, ArrayAdapter<T>> entry : mObjects.entrySet()) {
                ArrayAdapter<T> adapter = entry.getValue();
                total += adapter.getViewTypeCount();
            }
        }
		return total;
	}
	
	public int getItemViewType(int position) {
		int typeOffset=TYPE_SECTION_HEADER + 1;	// start counting from here
        if (mObjects != null && !mObjects.isEmpty()) {
            for (Map.Entry<O, ArrayAdapter<T>> entry : mObjects.entrySet()) {
                if (position == 0) {
                    return (TYPE_SECTION_HEADER);
                }
                ArrayAdapter<T> adapter = entry.getValue();
                int size = adapter.getCount() + 1;
                if (position < size) {
                    return (typeOffset + adapter.getItemViewType(position - 1));
                }
                position -= size;
                typeOffset += adapter.getViewTypeCount();
            }
        }
		return -1;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return(false);
	}

	@Override
	public boolean isEnabled(int position) {
		return (getItemViewType(position) != TYPE_SECTION_HEADER);
	}

	public void addSection(O section, ArrayAdapter<T> adapter) {
        if (mObjects == null) {
            mObjects = new LinkedHashMap<O, ArrayAdapter<T>>();
        }
        mObjects.put(section, adapter);
	}

    public Map<O, ArrayAdapter<T>> getSections() {
        return mObjects;
    }

    public O getHeaderSection(int headerPosition) {
        return (O)mObjects.keySet().toArray()[headerPosition];
    }

	public ArrayAdapter<T> getSection(O section) {
		if (mObjects.containsKey(section)) {
            return mObjects.get(section);
		}
		return null;
	}

    public boolean containsSection(O section) {
        return mObjects != null && !mObjects.isEmpty() && mObjects.containsKey(section);
    }

    /**
     * Returns the context associated with this sectioned adapter. The context is used
     * to create views from the resource passed to the constructor.
     *
     * @return The Context associated with this adapter.
     */
    public Context getContext() {
        return mContext;
    }
}
