package org.smartregister.chw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.vijay.jsonwizard.activities.JsonFormActivity;
import com.vijay.jsonwizard.utils.FormUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.chw.anc.util.JsonFormUtils;
import org.smartregister.chw.anc.util.NCUtils;
import org.smartregister.chw.core.custom_views.NavigationMenu;
import org.smartregister.chw.core.utils.Utils;
import org.smartregister.chw.fragment.GeRegisterFragment;
import org.smartregister.chw.presenter.GeRegisterActivityPresenter;
import org.smartregister.chw.referral.util.Util;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.helper.BottomNavigationHelper;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.List;

import timber.log.Timber;

public class GeRegisterActivity extends BaseRegisterActivity {

//    Add it's Presenter
    @Override
    protected void initializePresenter() {
        presenter = new GeRegisterActivityPresenter();
    }

// Default Fragment
    @Override
    protected BaseRegisterFragment getRegisterFragment() {
        return new GeRegisterFragment();
    }

//  Other Fragments
    @Override
    protected Fragment[] getOtherFragments() {
        return new Fragment[0];
    }

    @Override
    public void startFormActivity(String formName, String entityId, String metadata) {

//        Retrieve the JSON form file in a predefined location,
//        such as the application's assets or a repository directory
//        and parse it into a JSONObject
        try {
            JSONObject jsonObject =new FormUtils().getFormJsonFromRepositoryOrAssets(this,formName);

//        Link JSON  Object to the JSON form using entity_id Key
            jsonObject.put("entity_id", entityId);

//        Call startFormActivity that include the launched form
            startFormActivity(jsonObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startFormActivity(JSONObject jsonObject) {

//        Launch form from parent Activity using Intent
        Intent intent = new Intent(this, JsonFormActivity.class);

//        Convert JSON object to JSON String
        intent.putExtra("json",jsonObject.toString());

//        startActivity(intent);

        startActivityForResult(intent, 700);

    }

    @Override
    protected void onActivityResultExtended(int requestsCode, int resultsCode, Intent intent) {
        if (requestsCode == 700 && resultsCode == RESULT_OK){

//           Receive the data of type String using getStringExtra
            String filledForm = intent.getStringExtra("json");

            Timber.d("SHOW FILLED FORM "+filledForm );

//            Access some data such as userid, teamid that are binded to user, from shared_prefs
            AllSharedPreferences allSharedPreferences = Utils.getAllSharedPreferences();

//           Change the received filled form(String data) into an Event
            Event event = JsonFormUtils.processJsonForm(allSharedPreferences,filledForm,"ec_gender_equality"); // add table name

//            Use Gson library by Google to convert event to Json string
            Gson gson = JsonFormUtils.gson;
            String jsonString = gson.toJson(event);

            try {

//            Convert Json String into Json object
                JSONObject jsonObject = new JSONObject(jsonString);

//           Save and process the created Event
                NCUtils.processEvent(event.getBaseEntityId(), jsonObject);

            } catch (Exception e) {
                Timber.e(e);
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Link an instance of the Navigation menu to the Activity
        NavigationMenu.getInstance(this,null,null);

//        Receive the information sent by intent i.e baseEntityId
        String receiveBaseEntityId = getIntent().getStringExtra("BASE_ENTITY_ID");

//        Check if to open Activity with extra info (i.e. baseEntityId) or not
        if (receiveBaseEntityId != null){

//        Use Toast to get notification
            Toast.makeText(this,"Open Json Form",Toast.LENGTH_LONG).show();

//        Call json form using startFormActivity method
            startFormActivity("ge_enrollment_form", receiveBaseEntityId, null);


        }
    }

    @Override
    public List<String> getViewIdentifiers() {
        return null;
    }

    @Override
    public void startRegistration() {

    }

    @Override
    protected void registerBottomNavigation() {
        bottomNavigationHelper = new BottomNavigationHelper();
    }

//    the following method applies intent that is used to open one activity from another
//    Usage: Intent intent = new Intent(source,destination);
    public static void launchGeEnrollment(String baseEntityId, Activity activity){
        Intent intent = new Intent(activity,GeRegisterActivity.class);
        intent.putExtra("BASE_ENTITY_ID", baseEntityId);
        activity.startActivity(intent);
    }

}
