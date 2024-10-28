package org.smartregister.chw.interactor;

import com.google.gson.Gson;
import com.vijay.jsonwizard.utils.FormUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.chw.activity.GeProfileActivity;
import org.smartregister.chw.anc.util.JsonFormUtils;
import org.smartregister.chw.anc.util.NCUtils;
import org.smartregister.chw.core.contract.CoreGeProfileContract;
import org.smartregister.chw.util.Utils;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.repository.AllSharedPreferences;

import timber.log.Timber;

public class GeProfileInteractor implements CoreGeProfileContract.Interactor {
    private final GeProfileActivity view;
    public GeProfileInteractor(GeProfileActivity view) {
        this.view = view;
    }
    @Override
    public void processDataToEvent(String data) {
//      Access some data such as userid, teamid that are bound to user, from shared_prefs
        AllSharedPreferences allSharedPreferences = Utils.getAllSharedPreferences();

//      Convert JSON data to Event
        Event event = JsonFormUtils.processJsonForm(allSharedPreferences,data,"ec_gender_services"); // add table name

//      Use Gson library by Google to convert event to Json string
        Gson gsonVar = JsonFormUtils.gson;
        String jsonString = gsonVar.toJson(event);

        try {
//      Convert Json String into Json object
            JSONObject jsonObject = new JSONObject(jsonString);

//      Save and process the created Event
            NCUtils.processEvent(event.getBaseEntityId(), jsonObject);

        } catch (Exception e) {
            Timber.e(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject getJsonForm(CommonPersonObjectClient commonPersonObjectClient, String formName) {
        try{
//        Get JSON form from Repository or Assets
            FormUtils formUtils = new FormUtils();
            JSONObject jsonObject = formUtils.getFormJsonFromRepositoryOrAssets(view,formName);

//        Link json object to the json form using base_entity_id key
            jsonObject.put("entity_id",commonPersonObjectClient.getColumnmaps().get("base_entity_id"));

//        Return the modified jsonObject
            return jsonObject;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}
