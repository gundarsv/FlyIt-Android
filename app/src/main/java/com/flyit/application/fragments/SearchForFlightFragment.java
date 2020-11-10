package com.flyit.application.fragments;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.flyit.application.R;
import com.flyit.application.adapters.SearchFlightAdapter;
import com.flyit.application.fragments.utils.FragmentUtils;
import com.flyit.application.models.FlightSearch;
import com.flyit.application.viewModels.SearchForFlightViewModel;

import java.util.ArrayList;

public class SearchForFlightFragment extends Fragment {

    private FragmentManager fragmentManager;
    private SearchForFlightViewModel searchForFlightViewModel;
    private AutoCompleteTextView mSearchFlightNo;
    private Button mButtonGoToFlights;
    private ProgressBar mAddFlightProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchforflights, container, false);

        mSearchFlightNo = view.findViewById(R.id.searchFlightNo);
        mButtonGoToFlights = view.findViewById(R.id.buttonGoToFlights);
        mAddFlightProgressBar = view.findViewById(R.id.addFlightProgressBar);

        searchForFlightViewModel = new ViewModelProvider(this).get(SearchForFlightViewModel.class);

        this.fragmentManager = getActivity().getSupportFragmentManager();

        mButtonGoToFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.changeFragment(getActivity().getViewModelStore(), fragmentManager, new FlightsFragment(), "FlightsFragment", null, R.id.fragment_container);
            }
        });

        mSearchFlightNo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (mSearchFlightNo.getRight() - mSearchFlightNo.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        searchForFlightViewModel.searchFlight(mSearchFlightNo.getText().toString());

                        return true;
                    }
                }
                return false;
            }
        });

        searchForFlightViewModel.getMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }
        });

        searchForFlightViewModel.getSearchedFlights().observe(getViewLifecycleOwner(), new Observer<FlightSearch>() {
            @Override
            public void onChanged(FlightSearch flight) {
                ArrayList<FlightSearch> flightSearches = new ArrayList<>();
                flightSearches.add(flight);

                SearchFlightAdapter adapter = new SearchFlightAdapter(getActivity(), android.R.layout.select_dialog_item, flightSearches);
                mSearchFlightNo.setAdapter(adapter);
                mSearchFlightNo.showDropDown();
            }
        });

        mSearchFlightNo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FlightSearch selectedItem = (FlightSearch) parent.getItemAtPosition(position);
                searchForFlightViewModel.addFlight(selectedItem);
            }
        });

        searchForFlightViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mSearchFlightNo.setEnabled(false);
                    mAddFlightProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mSearchFlightNo.setEnabled(true);
                    mAddFlightProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        searchForFlightViewModel.getIsFlightNumberValid().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    changeAutoCompleteColors(mSearchFlightNo, R.color.colorInvalidRed);
                }
                else {
                    changeAutoCompleteColors(mSearchFlightNo, R.color.colorWhite);
                }
            }
        });

        return view;
    }

    private void changeAutoCompleteColors(AutoCompleteTextView textView, int colorResource) {
        setTextViewDrawableColor(textView, colorResource);
        textView.setTextColor(ContextCompat.getColor(getContext(), colorResource));
        textView.setHintTextColor(ContextCompat.getColor(getContext(), colorResource));
        textView.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), colorResource)));
    }

    private void setTextViewDrawableColor(AutoCompleteTextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
