package ramin.seyghaly.ex_fragmentation;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Random;

import ramin.seyghaly.fragmentation.dialog.BaseDialogFragment;
import ramin.seyghaly.fragmentation.fragment.BaseFragment;
import ramin.seyghaly.fragmentation.bottomsheet.BaseBottomsheet;
import ramin.seyghaly.fragmentation.bottomsheet.OnBottomsheetListener;

public class TestFragment extends BaseFragment {

    private View view;
    private String tag = random();
    public static int x = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (this.view == null) {
            this.view = inflater.inflate(R.layout.fragment_test, container, false);
        }
        return this.view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatTextView textView = view.findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSafe() && x < 3) {
                    x++;
                    ((MainActivity) getActivity()).addFragment(new TestFragment(), true);
                }else {
                    CustomDialogFragment customDialogFragment = new CustomDialogFragment(new BaseDialogFragment.Builder()
                            .setDialogLayout(R.layout.dialog_test)
                            .bulid()
                    );
                    customDialogFragment.show(getActivity().getSupportFragmentManager(), customDialogFragment.getTag());
                }
            }
        });
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isSafe()) {
                    CustomBottomsheet customBottomsheet = new CustomBottomsheet(new BaseBottomsheet.Builder()
                            .setCanceledOnTouchOutside(false)
                            .setPeekHeight(400)
                            .setBackgroundColor(Color.parseColor("#00000000"))
                            .setBottomsheetLayout(R.layout.bottomsheet_custom)
                            .setOnBottomsheetListener(new OnBottomsheetListener() {
                                @Override
                                public void onBottomsheetCreated() {
                                    Log.i("sdfsdgsdfgsfd", "onBottomsheetCreated");
                                }

                                @Override
                                public void onBottomsheetOpen() {
                                    Log.i("sdfsdgsdfgsfd", "onBottomsheetOpen");
                                }

                                @Override
                                public void onBottomsheetClose() {
                                    Log.i("sdfsdgsdfgsfd", "onBottomsheetClose");
                                }
                            })
                            .bulid());
                    customBottomsheet.show(getActivity().getSupportFragmentManager(), customBottomsheet.getTag());
                }
                return false;
            }
        });
        textView.setText(tag);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("sdfsdgsdfgsfd", "onActivityCreated");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("sdfsdgsdfgsfd", "onSaveInstanceState");
    }

    public static String random() {
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
    public void visibleAgain() {
        Log.i("sdfsdgsdfgsfd", "visible again - " + tag);
        super.visibleAgain();
    }

    @Override
    public void inVisible() {
        Log.i("sdfsdgsdfgsfd", "inVisible - " + tag);
        super.inVisible();
    }

}
