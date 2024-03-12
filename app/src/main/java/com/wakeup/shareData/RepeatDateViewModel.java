package com.wakeup.shareData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RepeatDateViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public RepeatDateViewModel() {
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
            if(mText.getValue().equals("")) {
                mText.setValue(text);
                return;
            }
            mText.setValue(mText.getValue() + ", " + text);
        }
    }
}
