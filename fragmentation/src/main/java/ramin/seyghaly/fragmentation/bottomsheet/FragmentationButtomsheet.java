package ramin.seyghaly.fragmentation.bottomsheet;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ramin.seyghaly.fragmentation.R;
import ramin.seyghaly.fragmentation.exceptions.FragmentationException;

public abstract class FragmentationButtomsheet extends BottomSheetDialogFragment implements BottomsheetDelegate {

    private View rootView, view;
    private BottomSheetBehavior behavior;
    private OnBottomsheetListener onBottomsheetListener = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, getStyle());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("sadsaf","3");
        rootView = inflater.inflate(R.layout.bottomsheet_layout, container);
        RelativeLayout rootBottomsheet = rootView.findViewById(R.id.rootBottomsheet);
        view = inflater.inflate(getBottomsheetLayout(), container);
        rootBottomsheet.addView(view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getBackgroundColor()));
        getDialog().setCanceledOnTouchOutside(getCanceledOnTouchOutside());
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(android.content.DialogInterface dialog, int keyCode, android.view.KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == android.view.KeyEvent.KEYCODE_BACK) {
                    boolean shouldClosed = onBackPressed();
                    if (shouldClosed) {
                        hiddenState();
                    }
                    return true;
                }
                return false;

            }
        });
        behavior = BottomSheetBehavior.from(rootBottomsheet);
        behavior.setPeekHeight(getPeekHeight());
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    close();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBottomsheetListener = getOnBottomsheetListener();
        if (onBottomsheetListener != null){
            onBottomsheetListener.onBottomsheetCreated();
        }
        onLayoutCreated(view);
    }

    public abstract OnBottomsheetListener getOnBottomsheetListener();

    @StyleRes
    public abstract int getStyle();

    public abstract boolean getCanceledOnTouchOutside();

    @ColorInt
    public abstract int getBackgroundColor();

    public abstract int getPeekHeight();

    @LayoutRes
    public abstract int getBottomsheetLayout();

    public abstract void onLayoutCreated(View rootView);

    public void close() {
        try {
            getDialog().dismiss();
            FragmentationButtomsheet.super.onDismiss(getDialog());
            if (onBottomsheetListener != null){
                onBottomsheetListener.onBottomsheetClose();
            }
        } catch (Exception e) {
        }
    }

    private void hiddenState() {
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    public void open() {
        if (behavior == null){
            throw new FragmentationException(FragmentationException.DO_NOT_USE_ON_CREATE_VIEW);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                if (onBottomsheetListener != null){
                    onBottomsheetListener.onBottomsheetOpen();
                }
            }
        }, 500);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
