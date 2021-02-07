package ramin.seyghaly.fragmentation.activity;

import androidx.annotation.IdRes;

import ramin.seyghaly.fragmentation.fragment.BaseFragment;

public interface ActivityDelegate {

    void replaceFragment(@IdRes int rootId, BaseFragment baseFragment, boolean addToBackStack);

    void addFragment(@IdRes int rootId, BaseFragment baseFragment, boolean addToBackStack);

}
