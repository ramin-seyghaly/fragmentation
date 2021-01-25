package ramin.seyghaly.fragmentation.bottomsheet;

import android.app.Dialog;
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

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ramin.seyghaly.fragmentation.R;
import ramin.seyghaly.fragmentation.bottomsheet.FragmentationButtomsheet;
import ramin.seyghaly.fragmentation.exceptions.FragmentationException;

public abstract class BaseBottomsheet<T> extends FragmentationButtomsheet {

    private final @StyleRes int style;
    private final boolean canceledOnTouchOutside;
    private final @ColorInt int backgroundColor;
    private final int peekHeight;
    private final @LayoutRes int layout;
    private final OnBottomsheetListener onBottomsheetListener;

    public BaseBottomsheet(Builder builder){
        style = builder.style;
        canceledOnTouchOutside = builder.canceledOnTouchOutside;
        backgroundColor = builder.backgroundColor;
        peekHeight = builder.peekHeight;
        layout = builder.layout;
        onBottomsheetListener = builder.onBottomsheetListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("sadsaf","2");
        if (container != null){
            Log.i("sadsaf","child count = " + container.getChildCount());
            throw new FragmentationException("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        }else {
            Log.i("sadsaf","child count = " + null);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static class Builder{
        private @StyleRes int style = R.style.BottomsheetStyle;
        private boolean canceledOnTouchOutside = false;
        private @ColorInt int backgroundColor = Color.parseColor("#00000000");
        private int peekHeight = 0;
        private @LayoutRes int layout = 0;
        private OnBottomsheetListener onBottomsheetListener;

        public Builder setStyle(@StyleRes int style){
            this.style = style;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setPeekHeight(int peekHeight) {
            this.peekHeight = peekHeight;
            return this;
        }

        public Builder setBottomsheetLayout(@LayoutRes int layout){
            this.layout = layout;
            return this;
        }

        public Builder setOnBottomsheetListener(OnBottomsheetListener onBottomsheetListener) {
            this.onBottomsheetListener = onBottomsheetListener;
            return this;
        }

        public Builder bulid(){
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
    public int getPeekHeight() {
        return peekHeight;
    }

    @Override
    public int getBottomsheetLayout() {
        return layout;
    }

    @Override
    public OnBottomsheetListener getOnBottomsheetListener() {
        return onBottomsheetListener;
    }
}