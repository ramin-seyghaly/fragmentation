package ramin.seyghaly.fragmentation.dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;

import ramin.seyghaly.fragmentation.R;
import ramin.seyghaly.fragmentation.bottomsheet.BaseBottomsheet;
import ramin.seyghaly.fragmentation.bottomsheet.OnBottomsheetListener;
import ramin.seyghaly.fragmentation.exceptions.FragmentationException;

public abstract class BaseDialogFragment extends FragmentationDialogFragment {

    private final @StyleRes int style;
    private final boolean canceledOnTouchOutside;
    private final @ColorInt int backgroundColor;
    private final @LayoutRes int layout;
    private final OnDialogListener onDialogListener;

    public BaseDialogFragment(Builder builder){
        style = builder.style;
        canceledOnTouchOutside = builder.canceledOnTouchOutside;
        backgroundColor = builder.backgroundColor;
        layout = builder.layout;
        onDialogListener = builder.onDialogListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container != null){
            throw new FragmentationException("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        }else {
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static class Builder{
        private @StyleRes int style = R.style.DialogStyle;
        private boolean canceledOnTouchOutside = false;
        private @ColorInt int backgroundColor = Color.parseColor("#00000000");
        private @LayoutRes int layout = 0;
        private OnDialogListener onDialogListener;

        public BaseDialogFragment.Builder setStyle(@StyleRes int style){
            this.style = style;
            return this;
        }

        public BaseDialogFragment.Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public BaseDialogFragment.Builder setBackgroundColor(@ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public BaseDialogFragment.Builder setDialogLayout(@LayoutRes int layout){
            this.layout = layout;
            return this;
        }

        public BaseDialogFragment.Builder setOnDialogListener(OnDialogListener onDialogListener) {
            this.onDialogListener = onDialogListener;
            return this;
        }

        public BaseDialogFragment.Builder bulid(){
            if (layout == 0){
                throw new FragmentationException(FragmentationException.THE_VIEW_IS_NOT_SET);
            }
            return this;
        }
    }

    @Override
    public int getStyle() {
        return style;
    }

    @Override
    public boolean getCanceledOnTouchOutside() {
        return canceledOnTouchOutside;
    }

    @Override
    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public int getDialogLayout() {
        return layout;
    }

    @Override
    public OnDialogListener getOnDialogListener() {
        return onDialogListener;
    }

}
