package ramin.seyghaly.ex_fragmentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import ramin.seyghaly.fragmentation.bottomsheet.BaseBottomsheet;

public class CustomBottomsheet extends BaseBottomsheet {

    private boolean customBackHandler = true;

    public CustomBottomsheet(Builder builder) {
        super(builder);
    }

    @Override
    public void onLayoutCreated(View rootView) {
        open();
        AppCompatTextView textView = rootView.findViewById(R.id.xx);
        textView.setText("sdfsdgdfg");
    }

    @Override
    public boolean onBackPressed() {
        if (customBackHandler){
            customBackHandler = false;
            return customBackHandler;
        }else {
            return super.onBackPressed();
        }
    }

}
