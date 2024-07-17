package org.smartregister.chw.presenter;

import org.smartregister.chw.contract.TunzaRegisterFragmentContract;
import org.smartregister.chw.fragment.GeRegisterFragment;
import org.smartregister.view.contract.BaseRegisterContract;
import org.smartregister.view.contract.BaseRegisterFragmentContract;

import java.util.List;

public class TunzaRegisterFragmentPresenter implements BaseRegisterFragmentContract.Presenter {

    TunzaRegisterFragmentContract.Model model;

    BaseRegisterFragmentContract.View view;

    public TunzaRegisterFragmentPresenter(TunzaRegisterFragmentContract.Model model, BaseRegisterFragmentContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void processViewConfigurations() {

    }

    public String getTableName(){
        return model.getTableName();
    }

    @Override
    public void initializeQueries(String mainCondition) {
        view.initializeQueryParams(getTableName(),getCountSelect(mainCondition),getMainSelect(mainCondition));
        ((GeRegisterFragment)view).initializeAdapter();
        ((GeRegisterFragment)view).countExecute();
        ((GeRegisterFragment)view).filterandSortInInitializeQueries();

    }

    @Override
    public void startSync() {

    }

    @Override
    public void searchGlobally(String s) {

    }

    public String getMainCondition(){
        return model.getMainCondition();
    }



    public String getDefaultSortQuery(){
        return model.getDefaultSortQuery();
    }

    public String getMainSelect(String mainCondition){
        return model.getMainSelect(mainCondition);

    }

    public String getCountSelect(String mainCondition){
        return model.getCountSelect(mainCondition);
    }
}
