package org.smartregister.chw.activity;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;
import org.smartregister.chw.fragment.BloodRegisterFragment;
import org.smartregister.chw.presenter.BloodRegisterActivityPresenter;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.List;

public class BloodRegisterActivity extends BaseRegisterActivity {
    @Override
    protected void initializePresenter() {
        presenter = new BloodRegisterActivityPresenter();

    }

    @Override
    protected BaseRegisterFragment getRegisterFragment() {
        return new BloodRegisterFragment();
    }

    @Override
    protected Fragment[] getOtherFragments() {
        return new Fragment[0];
    }

    @Override
    public void startFormActivity(String s, String s1, String s2) {

    }

    @Override
    public void startFormActivity(JSONObject jsonObject) {

    }

    @Override
    protected void onActivityResultExtended(int i, int i1, Intent intent) {

    }

    @Override
    public List<String> getViewIdentifiers() {
        return null;
    }

    @Override
    public void startRegistration() {

    }
}
