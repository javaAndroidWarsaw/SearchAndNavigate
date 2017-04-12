package com.softwareacademy.searchandnavigate.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.softwareacademy.searchandnavigate.R;
import com.softwareacademy.searchandnavigate.model.dto.SearchParamsDto;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

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
        init();
    }

    public SearchQueryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private SearchParamsDto searchParamsDto;
    private Map<String, String> querys;
    private String obligateKey = "keyword";
    private PublishSubject<SearchParamsDto> subject;

    private void init() {

        inflate(getContext(), R.layout.search_layout, this);
        this.setOrientation(VERTICAL);
        subject = PublishSubject.create();
        querys = new HashMap<>();
        searchParamsDto = new SearchParamsDto();
        querys.put("location", "52.2,21");

        editText = (EditText) this.findViewById(R.id.search_query);
        radialRadio = (RadioButton) this.findViewById(R.id.radial_search);
        textRadio = (RadioButton) this.findViewById(R.id.text_search);
        nearbyRadio = (RadioButton) this.findViewById(R.id.nearby_search);
        radiusSeekBar = (AppCompatSeekBar) this.findViewById(R.id.seek_bar);
        searchButton = (Button) this.findViewById(R.id.search_button);

        setUpListeners();
        searchButton.setOnClickListener(v -> {
            searchParamsDto.setQuerys(querys);
            subject.onNext(searchParamsDto);
        });
    }

    public Observable<SearchParamsDto> subscribeToStream(){
        return subject;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        subject.onComplete();
    }

    private void setUpListeners() {
        RxTextView.textChanges(editText).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it -> {
                    querys.put(obligateKey,it.toString());
                }, error -> {
                    error.printStackTrace();
                });

        textRadio.setOnCheckedChangeListener((buttonView, isChecked) -> {
            searchParamsDto.setSearchType("textsearch");
            removaAndAddObligateKeyAfterChange("query");
        });
        nearbyRadio.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    searchParamsDto.setSearchType("nearbysearch");
                }
        );
        radialRadio.setOnCheckedChangeListener((buttonView, isChecked) -> {
            searchParamsDto.setSearchType("radarsearch");
            removaAndAddObligateKeyAfterChange("keyword");
        });

        radiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                querys.put("radius",String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void removaAndAddObligateKeyAfterChange(String key) {
        querys.remove(obligateKey);
        obligateKey = key;
        querys.put(obligateKey,editText.getText().toString());
    }


    public SearchParamsDto getSearchParamsDto() {
        return null;
    }
}
