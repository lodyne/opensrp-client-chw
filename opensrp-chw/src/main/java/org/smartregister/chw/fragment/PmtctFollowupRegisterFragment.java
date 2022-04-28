package org.smartregister.chw.fragment;

import org.smartregister.chw.R;
import org.smartregister.chw.activity.MotherChampionRegisterActivity;
import org.smartregister.chw.activity.PmtctFollowupDetailsActivity;
import org.smartregister.chw.core.fragment.CorePmtctRegisterFragment;
import org.smartregister.chw.model.PmtctFollowupRegisterFragmentModel;
import org.smartregister.chw.presenter.PmtctFollowupRegisterFragmentPresenter;
import org.smartregister.chw.provider.MotherChampionRegisterProvider;
import org.smartregister.configurableviews.model.View;
import org.smartregister.cursoradapter.RecyclerViewPaginatedAdapter;

import java.util.Set;

import timber.log.Timber;

public class PmtctFollowupRegisterFragment extends CorePmtctRegisterFragment {
    @Override
    protected void initializePresenter() {
        if (getActivity() == null) {
            return;
        }
        String viewConfigurationIdentifier = null;
        try {
            viewConfigurationIdentifier = ((MotherChampionRegisterActivity) getActivity()).getViewIdentifiers().get(0);
        } catch (Exception e) {
            Timber.e(e);
        }

        presenter = new PmtctFollowupRegisterFragmentPresenter(this, new PmtctFollowupRegisterFragmentModel(), viewConfigurationIdentifier);

    }

    @Override
    protected void openProfile(String baseEntityId) {
        PmtctFollowupDetailsActivity.startPmtctDetailsActivity(getActivity(), baseEntityId);
    }

    @Override
    public void setupViews(android.view.View view) {
        super.setupViews(view);
        titleLabelView.setText(R.string.missap_and_ltf_followups);
    }

    @Override
    public void initializeAdapter(Set<View> visibleColumns) {
        MotherChampionRegisterProvider motherChampionRegisterProvider = new MotherChampionRegisterProvider(getActivity(), paginationViewHandler, registerActionHandler, visibleColumns);
        clientAdapter = new RecyclerViewPaginatedAdapter(null, motherChampionRegisterProvider, context().commonrepository(this.tablename));
        clientAdapter.setCurrentlimit(20);
        clientsView.setAdapter(clientAdapter);
    }

    @Override
    protected void openFollowUpVisit(String baseEntityId) {
        //  PmtctFollowUpVisitActivity.startPmtctFollowUpActivity(getActivity(),baseEntityId);
    }

    @Override
    protected void refreshSyncProgressSpinner() {
        if (isSyncing()) {
            if (syncProgressBar != null) {
                syncProgressBar.setVisibility(android.view.View.VISIBLE);
            }
            if (syncButton != null) {
                syncButton.setVisibility(android.view.View.GONE);
            }
        } else {
            if (syncProgressBar != null) {
                syncProgressBar.setVisibility(android.view.View.GONE);
            }
            if (syncButton != null) {
                syncButton.setVisibility(android.view.View.GONE);
            }
        }
    }
}
