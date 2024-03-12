package com.wakeup.shareData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MissonViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public MissonViewModel() {
        mText = new MutableLiveData<>();
    }

    public MutableLiveData<String> getText() {
        return mText;
    }

    public void setText(String text) {
        mText.setValue(text);
    }

    public void clearText() {
        mText.setValue("");
    }

    public void appendText(String text) {
        mText.setValue(mText.getValue() + text);
    }

}
