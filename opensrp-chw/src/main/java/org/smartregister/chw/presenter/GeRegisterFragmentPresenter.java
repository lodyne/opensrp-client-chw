package org.smartregister.chw.presenter;

import org.smartregister.chw.contract.GeRegisterFragmentContract;
import org.smartregister.chw.fragment.GeRegisterFragment;
import org.smartregister.view.contract.BaseRegisterFragmentContract;

public class GeRegisterFragmentPresenter implements BaseRegisterFragmentContract.Presenter {


    private final GeRegisterFragmentContract.Model model;
    private final GeRegisterFragmentContract.View view;

    public GeRegisterFragmentPresenter(GeRegisterFragmentContract.View view, GeRegisterFragmentContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void processViewConfigurations() {

    }

    @Override
    public void initializeQueries(String mainCondition) {
        view.initializeQueryParams(getTablename(),getCountSelect(mainCondition), getMainSelect(mainCondition));
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
    public String getTablename(){
        return model.getTablename();
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
