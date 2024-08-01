package org.smartregister.chw.presenter;

import org.smartregister.chw.contract.MimiRegisterFragmentContract;
import org.smartregister.view.contract.BaseRegisterFragmentContract;

public class MimiRegisterFragmentPresenter implements BaseRegisterFragmentContract.Presenter {
    private final MimiRegisterFragmentContract.Model model;
    private final BaseRegisterFragmentContract.View view;

    public MimiRegisterFragmentPresenter(MimiRegisterFragmentContract.Model model, BaseRegisterFragmentContract.View view) {
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
