package org.smartregister.chw.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.vijay.jsonwizard.utils.FormUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.chw.activity.GeProfileActivity;
import org.smartregister.chw.anc.util.JsonFormUtils;
import org.smartregister.chw.anc.util.NCUtils;
import org.smartregister.chw.core.contract.CoreGeProfileContract;
import org.smartregister.chw.core.presenter.CoreGeProfilePresenter;
import org.smartregister.chw.interactor.GeProfileInteractor;
import org.smartregister.chw.model.GeProfileModel;
import org.smartregister.chw.util.Utils;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.repository.AllSharedPreferences;

import timber.log.Timber;

public class GeProfilePresenter extends CoreGeProfilePresenter {

    private final GeProfileModel model;
    private final GeProfileInteractor interactor;

    public GeProfilePresenter(GeProfileModel model, GeProfileInteractor interactor) {
        this.model = model;
        this.interactor = interactor;
    }

    @Override
    public void processDataToEvent(String data) {
        super.processDataToEvent(data);
        interactor.processDataToEvent(data);
    }

    @Override
    public JSONObject getJsonForm(CommonPersonObjectClient commonPersonObjectClient, String formName){
        return interactor.getJsonForm(commonPersonObjectClient,formName);
    }

    @Override
    public CoreGeProfileContract.ClientInfo getClientsInfo(CommonPersonObjectClient clientsInfo) {
        return model.getClientsInfo(clientsInfo);
    }

    public String getViewHistory(String mainCondition){
        return model.getViewHistory(mainCondition);
    }
}
