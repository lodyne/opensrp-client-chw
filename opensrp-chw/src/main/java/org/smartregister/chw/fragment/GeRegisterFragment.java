package org.smartregister.chw.fragment;

import android.view.View;

import org.smartregister.chw.R;
import org.smartregister.chw.contract.GeRegisterFragmentContract;
import org.smartregister.chw.model.GeRegisterFragmentModel;
import org.smartregister.chw.presenter.GeRegisterFragmentPresenter;
import org.smartregister.chw.provider.OpdRegisterProvider;
import org.smartregister.cursoradapter.RecyclerViewPaginatedAdapter;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.customcontrols.CustomFontTextView;
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
    public void setupViews(View view) {
        super.setupViews(view);

        //        view.findViewById(R.id.scanQrCode).setVisibility(View.GONE);
        View qrCode = view.findViewById(R.id.scanQrCode);
        qrCode.setVisibility(View.GONE);

        view.findViewById(R.id.left_menu).setVisibility(View.VISIBLE);
        view.findViewById(R.id.opensrp_logo_image_view).setVisibility(View.GONE);
//        view.findViewById(R.id.txt_title_label).setVisibility(View.VISIBLE);


//        int x =6;
//        float z = (float)x;

        View view1 = view.findViewById(R.id.txt_title_label);
        CustomFontTextView txt = (CustomFontTextView)view1 ;
        txt.setVisibility(View.VISIBLE);
        txt.setText(R.string.menu_ge);

//        Implicit Casting
//        CustomFontTextView txt = view.findViewById(R.id.txt_title_label);
//        txt.setVisibility(View.VISIBLE);
//        txt.setText(R.string.menu_ge);

//        ((CustomFontTextView)view.findViewById(R.id.txt_title_label)).setText(R.string.menu_ge);

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
