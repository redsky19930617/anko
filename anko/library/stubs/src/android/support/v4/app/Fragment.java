package android.support.v4.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Fragment {

    public Activity getActivity() {
        return null;
    }

    public void setArguments(Bundle args) {

    }

    public boolean isDetached() {
        return false;
    }

    public View getView() {
        return new View(getActivity());
    }

}
