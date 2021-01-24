package ramin.seyghaly.fragmentation;

import androidx.annotation.IdRes;

public interface ActivityDelegate {

    void replaceFragment(@IdRes int rootId, BaseFragment baseFragment, boolean addToBackStack);

    void addFragment(@IdRes int rootId, BaseFragment baseFragment, boolean addToBackStack);

}
