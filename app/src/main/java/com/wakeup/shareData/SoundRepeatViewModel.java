package com.wakeup.shareData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SoundRepeatViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SoundRepeatViewModel() {
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
        if(mText.getValue() == null) {
            mText.setValue(text);
        } else {
            mText.setValue(mText.getValue() + " " + text);
        }
    }
}
