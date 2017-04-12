package com.softwareacademy.searchandnavigate.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.softwareacademy.searchandnavigate.R;
import com.softwareacademy.searchandnavigate.model.dto.SearchParamsDto;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 *
 */

public class SearchQueryView extends LinearLayout {
    private EditText editText;
    private RadioButton nearbyRadio, textRadio, radialRadio;
    private AppCompatSeekBar radiusSeekBar;
    private Button searchButton;


    public SearchQueryView(Context context) {
        super(context);
    }

    public SearchQueryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchQueryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private SearchParamsDto searchParamsDto;

    private void init() {
        inflate(getContext(), R.layout.search_layout, null);

        searchParamsDto = new SearchParamsDto();

        editText = (EditText) this.findViewById(R.id.search_query);
        radialRadio = (RadioButton)this.findViewById(R.id.radial_search);
        textRadio = (RadioButton)this.findViewById(R.id.text_search);
        nearbyRadio = (RadioButton)this.findViewById(R.id.nearby_search);
        radiusSeekBar = (AppCompatSeekBar)this.findViewById(R.id.seek_bar);
        searchButton  =(Button)this.findViewById(R.id.search_button);

        RxTextView.textChanges(editText).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it->{

                },error->{error.printStackTrace();});

        textRadio.setOnCheckedChangeListener((buttonView, isChecked) -> {
            searchParamsDto.setSearchType("textsearch");
        });

    }



    public SearchParamsDto getSearchParamsDto(){
        return null;
    }
}
