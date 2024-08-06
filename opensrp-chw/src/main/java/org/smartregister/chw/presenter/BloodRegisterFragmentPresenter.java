package org.smartregister.chw.presenter;

import org.smartregister.chw.contract.BloodRegisterFragmentContract;
import org.smartregister.view.contract.BaseRegisterFragmentContract;

public class BloodRegisterFragmentPresenter implements BaseRegisterFragmentContract.Presenter {
   private BloodRegisterFragmentContract.Model model;
   private BaseRegisterFragmentContract.View view;

    public BloodRegisterFragmentPresenter(BloodRegisterFragmentContract.Model model, BaseRegisterFragmentContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void processViewConfigurations() {

    }

    @Override
    public void initializeQueries(String s) {

    }

    @Override
    public void startSync() {

    }

    @Override
    public void searchGlobally(String s) {

    }
}
