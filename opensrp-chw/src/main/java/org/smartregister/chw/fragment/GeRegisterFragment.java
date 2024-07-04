package org.smartregister.chw.fragment;

import android.view.View;

import org.smartregister.chw.contract.GeRegisterFragmentContract;
import org.smartregister.chw.model.GeRegisterFragmentModel;
import org.smartregister.chw.presenter.GeRegisterFragmentPresenter;
import org.smartregister.chw.provider.OpdRegisterProvider;
import org.smartregister.cursoradapter.RecyclerViewPaginatedAdapter;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.HashMap;

public class GeRegisterFragment extends BaseRegisterFragment {

    public void initializeAdapter() {
        OpdRegisterProvider childRegisterProvider = new OpdRegisterProvider(getActivity(), registerActionHandler, paginationViewHandler);
        clientAdapter = new RecyclerViewPaginatedAdapter(null, childRegisterProvider, context().commonrepository(this.tablename));
        clientAdapter.setCurrentlimit(20);
        clientsView.setAdapter(clientAdapter);
    }
    @Override
    protected void initializePresenter() {
        GeRegisterFragmentContract.Model geRegisterFragmentModel = new GeRegisterFragmentModel();
        presenter = new GeRegisterFragmentPresenter(GeRegisterFragment.this,geRegisterFragmentModel);

    }

    @Override
    public void setUniqueID(String s) {

    }

    @Override
    public void setAdvancedSearchFormData(HashMap<String, String> hashMap) {

    }

    @Override
    protected String getMainCondition() {
        GeRegisterFragmentPresenter x = (GeRegisterFragmentPresenter) presenter;

        return x.getMainCondition();
    }

    @Override
    protected String getDefaultSortQuery() {
        GeRegisterFragmentPresenter y = (GeRegisterFragmentPresenter) presenter;

        return y.getDefaultSortQuery();
    }

    @Override
    protected void startRegistration() {

    }

    @Override
    protected void onViewClicked(View view) {

    }

    @Override
    public void showNotFoundPopup(String s) {

    }

    @Override
    public String getTablename() {
        return ((GeRegisterFragmentPresenter)presenter).getTablename();
    }
}
