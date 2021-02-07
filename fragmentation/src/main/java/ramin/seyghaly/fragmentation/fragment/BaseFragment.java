package ramin.seyghaly.fragmentation.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

import ramin.seyghaly.fragmentation.activity.BaseActivity;

public class BaseFragment extends Fragment implements FragmentDelegate {

    protected BaseActivity activity;
    private boolean visible = false;
    private final String tag = getClass().getName() + "-" + this.hashCode() + "-" + random();

    private String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        char tempChar;
        for (int i = 0; i < 4; i++) {
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        visible = true;
        super.onCreate(savedInstanceState);
    }

    public String getUniqueTag() {
        return tag;
    }

    protected int getWindowWidth() {
        return getView() == null ? activity.getWindow().getDecorView().getMeasuredWidth() : getView().getMeasuredWidth();
    }

    protected int getWindowHeight() {
        return getView() == null ? activity.getWindow().getDecorView().getMeasuredHeight() : getView().getMeasuredHeight();
    }

    public boolean isSafe() {
        return !(this.isRemoving() || activity == null || this.isDetached() || !this.isAdded() || this.getView() == null);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void visibleAgain() {
        visible = true;
    }

    @Override
    public void inVisible() {
        visible = false;
    }

    public boolean isFragmentvisible() {
        return visible;
    }

}