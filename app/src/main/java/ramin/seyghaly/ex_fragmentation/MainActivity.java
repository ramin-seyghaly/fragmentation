package ramin.seyghaly.ex_fragmentation;

import android.os.Bundle;

import ramin.seyghaly.fragmentation.activity.BaseActivity;
import ramin.seyghaly.fragmentation.fragment.BaseFragment;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CustomBottomsheet customBottomsheet = new CustomBottomsheet(new BaseBottomsheet.Builder()
//                .setCanceledOnTouchOutside(false)
//                .setPeekHeight(400)
//                .setBackgroundColor(Color.parseColor("#00000000"))
//                .setBottomsheetLayout(R.layout.bottomsheet_custom)
//                .setOnBottomsheetListener(new OnBottomsheetListener() {
//                    @Override
//                    public void onBottomsheetCreated() {
//                        Log.i("dsfsdfdg","onBottomsheetCreated");
//                    }
//
//                    @Override
//                    public void onBottomsheetOpen() {
//                        Log.i("dsfsdfdg","onBottomsheetOpen");
//                    }
//
//                    @Override
//                    public void onBottomsheetClose() {
//                        Log.i("dsfsdfdg","onBottomsheetClose");
//                    }
//                })
//                .bulid());
//        customBottomsheet.show(getSupportFragmentManager(),customBottomsheet.getTag());

        addFragment(new TestFragment(),false);

    }

    public void addFragment(BaseFragment baseFragment,boolean addToBackStack){
        addFragment(R.id.container,baseFragment,addToBackStack);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}