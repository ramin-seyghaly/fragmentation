package ramin.seyghaly.fragmentation.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import ramin.seyghaly.fragmentation.R;
import ramin.seyghaly.fragmentation.bottomsheet.FragmentationButtomsheet;
import ramin.seyghaly.fragmentation.bottomsheet.OnBottomsheetListener;
import ramin.seyghaly.fragmentation.exceptions.FragmentationException;


public abstract class FragmentationDialogFragment extends DialogFragment implements DialogDelegate {

    private View rootView, view;
    private OnDialogListener onDialogListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, getStyle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_layout, container);
        FrameLayout rootDialog = rootView.findViewById(R.id.dialogRoot);
        view = inflater.inflate(getDialogLayout(), container);
        rootDialog.addView(view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getBackgroundColor()));
        getDialog().setCanceledOnTouchOutside(getCanceledOnTouchOutside());
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(android.content.DialogInterface dialog, int keyCode, android.view.KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == android.view.KeyEvent.KEYCODE_BACK) {
                    boolean shouldClosed = onBackPressed();
                    if (shouldClosed) {
                        close();
                    }
                    return true;
                }
                return false;

            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onDialogListener = getOnDialogListener();
        if (onDialogListener != null){
            onDialogListener.onDialogOpen();
        }
        onLayoutCreated(view);
    }

    /*@NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                try {
                    getDialog().dismiss();
                    MemberDialogFragment.super.onDismiss(getDialog());
                } catch (Exception e) {
                }
            }
        };
    }*/

    public void close() {
        try {
            if (getDialog() != null) {
                getDialog().dismiss();
                FragmentationDialogFragment.super.onDismiss(getDialog());
                if (onDialogListener != null) {
                    onDialogListener.onDialogClose();
                }
            }
        } catch (Exception e) {
        }
    }

    /*public void open() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                if (onBottomsheetListener != null){
                    onBottomsheetListener.onBottomsheetOpen();
                }
            }
        }, 500);
    }*/

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    public abstract OnDialogListener getOnDialogListener();

    @StyleRes
    public abstract int getStyle();

    public abstract boolean getCanceledOnTouchOutside();

    @ColorInt
    public abstract int getBackgroundColor();

    @LayoutRes
    public abstract int getDialogLayout();

    public abstract void onLayoutCreated(View rootView);

    @Override
    public boolean onBackPressed() {
        return true;
    }

}