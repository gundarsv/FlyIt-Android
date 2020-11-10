package com.flyit.application.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.flyit.application.models.FlightSearch;

import java.util.ArrayList;

public class SearchFlightAdapter extends ArrayAdapter<FlightSearch> {
    private ArrayList<FlightSearch> items;
    private ArrayList<FlightSearch> itemsAll;
    private ArrayList<FlightSearch> suggestions;
    private int viewResourceId;

    @SuppressWarnings("unchecked")
    public SearchFlightAdapter(Context context, int viewResourceId, ArrayList<FlightSearch> items) {
        super(context, viewResourceId, items);
        this.items = items;
        this.itemsAll = (ArrayList<FlightSearch>) items.clone();
        this.suggestions = new ArrayList<FlightSearch>();
        this.viewResourceId = viewResourceId;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
        }

        FlightSearch flightSearch = items.get(position);

        if (flightSearch == null) {
            return v;
        }

        TextView searchDropdown = (TextView) v.findViewById(android.R.id.text1);

        if (searchDropdown == null) {
            return v;
        }

        searchDropdown.setText(flightSearch.getFlightNo() + " "
                + flightSearch.getDateTimeOffset().getDayOfMonth() + "/"
                + flightSearch.getDateTimeOffset().getMonthValue() + "/"
                + flightSearch.getDateTimeOffset().getYear());

        return v;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        public String convertResultToString(Object resultValue) {
            String str = ((FlightSearch) (resultValue)).getFlightNo();

            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();

                for (FlightSearch product : itemsAll) {
                    if (product.getFlightNo().toLowerCase()
                            .startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(product);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();

                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            @SuppressWarnings("unchecked")
            ArrayList<FlightSearch> filteredList = (ArrayList<FlightSearch>) results.values;
            if (results != null && results.count > 0) {
                clear();

                for (FlightSearch c : filteredList) {
                    add(c);

                }
                notifyDataSetChanged();
            }
        }
    };
}
