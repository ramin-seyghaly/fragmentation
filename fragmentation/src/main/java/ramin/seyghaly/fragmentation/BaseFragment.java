package ramin.seyghaly.fragmentation;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment implements FragmentDelegate {

    protected BaseActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String getUniqueTag() {
        return getClass().getName() + "-" + this.hashCode();
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

}