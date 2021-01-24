package ramin.seyghaly.fragmentation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public abstract class BaseActivity extends FragmentActivity implements ActivityDelegate {

    private int rootId = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (rootId != -1) {
            Fragment f = getSupportFragmentManager().findFragmentById(rootId);
            if (f instanceof BaseFragment) {
                boolean result = ((BaseFragment) f).onBackPressed();
                if (result) {
                    super.onBackPressed();
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    protected void closeAllFragments(FragmentManager supportFragmentManager) {
        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        List<Fragment> fragments = supportFragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void replaceFragment(int rootId, BaseFragment baseFragment, boolean addToBackStack) {
        this.rootId = rootId;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(rootId, baseFragment);
        if (addToBackStack) {
            ft.addToBackStack(baseFragment.getUniqueTag());
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void addFragment(int rootId, BaseFragment baseFragment, boolean addToBackStack) {
        this.rootId = rootId;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(rootId, baseFragment);
        if (addToBackStack) {
            ft.addToBackStack(baseFragment.getUniqueTag());
        }
        ft.commitAllowingStateLoss();
    }

}