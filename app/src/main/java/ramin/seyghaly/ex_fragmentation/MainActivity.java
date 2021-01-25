package ramin.seyghaly.ex_fragmentation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import ramin.seyghaly.fragmentation.BaseActivity;
import ramin.seyghaly.fragmentation.bottomsheet.BaseBottomsheet;
import ramin.seyghaly.fragmentation.bottomsheet.OnBottomsheetListener;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomBottomsheet customBottomsheet = new CustomBottomsheet(new BaseBottomsheet.Builder()
                .setCanceledOnTouchOutside(false)
                .setPeekHeight(400)
                .setBackgroundColor(Color.parseColor("#00000000"))
                .setBottomsheetLayout(R.layout.bottomsheet_custom)
                .setOnBottomsheetListener(new OnBottomsheetListener() {
                    @Override
                    public void onBottomsheetCreated() {
                        Log.i("dsfsdfdg","onBottomsheetCreated");
                    }

                    @Override
                    public void onBottomsheetOpen() {
                        Log.i("dsfsdfdg","onBottomsheetOpen");
                    }

                    @Override
                    public void onBottomsheetClose() {
                        Log.i("dsfsdfdg","onBottomsheetClose");
                    }
                })
                .bulid());
        customBottomsheet.show(getSupportFragmentManager(),customBottomsheet.getTag());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}