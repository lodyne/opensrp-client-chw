package org.smartregister.chw.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.vijay.jsonwizard.utils.FormUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.chw.anc.util.JsonFormUtils;
import org.smartregister.chw.anc.util.NCUtils;
import org.smartregister.chw.core.contract.CoreGeProfileContract;
import org.smartregister.chw.core.presenter.CoreGeProfilePresenter;
import org.smartregister.chw.model.GeProfileModel;
import org.smartregister.chw.util.Utils;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.repository.AllSharedPreferences;

import timber.log.Timber;

public class GeProfilePresenter extends CoreGeProfilePresenter {

    private final GeProfileModel model;

    public GeProfilePresenter(GeProfileModel model) {
        this.model = model;
    }

    @Override
    public void convertDataToEvent(String data) {
        super.convertDataToEvent(data);
//       Access some data such as userid, teamid that are binded to user, from shared_prefs
        AllSharedPreferences allSharedPreferences = Utils.getAllSharedPreferences();

        Event event = JsonFormUtils.processJsonForm(allSharedPreferences,data,"ec_gender_services"); // add table name

//       Use Gson library by Google to convert event to Json string
        Gson gson = JsonFormUtils.gson;
        String jsonString = gson.toJson(event);

        try {

//       Convert Json String into Json object
            JSONObject jsonObject = new JSONObject(jsonString);

//       Save and process the created Event
            NCUtils.processEvent(event.getBaseEntityId(), jsonObject);

        } catch (Exception e) {
            Timber.e(e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public CoreGeProfileContract.ClientInfo getClientsInfo(CommonPersonObjectClient clientsInfo) {
        return model.getClientsInfo(clientsInfo);
    }
}
