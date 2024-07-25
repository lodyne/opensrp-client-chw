package org.smartregister.chw.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.viewpager.widget.ViewPager;


import com.google.gson.Gson;
import com.vijay.jsonwizard.activities.JsonFormActivity;
import com.vijay.jsonwizard.utils.FormUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.chw.R;
import org.smartregister.chw.anc.util.JsonFormUtils;
import org.smartregister.chw.anc.util.NCUtils;
import org.smartregister.chw.util.Utils;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.view.activity.BaseProfileActivity;
import org.smartregister.view.customcontrols.CustomFontTextView;

import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

public class GeProfileActivity extends BaseProfileActivity {
    CommonPersonObjectClient clientsInfo;
    @Override
    protected void initializePresenter() {

    }

    @Override
    protected ViewPager setupViewPager(ViewPager viewPager) {
        return null;
    }

    @Override
    protected void fetchProfileData() {

    }



    @Override
    protected void setupViews() {
        super.setupViews();

        Intent intent = getIntent();

        clientsInfo = (CommonPersonObjectClient) intent.getSerializableExtra("client_info");

        CircleImageView avatorIV = findViewById(R.id.imageview_profile);
        CustomFontTextView profilenameTV = findViewById(R.id.textview_name);
        CustomFontTextView genderTV = findViewById(R.id.textview_detail_one);
        CustomFontTextView locationTV = findViewById(R.id.textview_detail_two);
        CustomFontTextView idTV = findViewById(R.id.textview_detail_three);
        Button profileButton = findViewById(R.id.btn_profile_registration_info);

        if (clientsInfo != null) {


            String firstname = clientsInfo.getColumnmaps().get("first_name");
            String middlename = clientsInfo.getColumnmaps().get("middle_name");
            String lastname = clientsInfo.getColumnmaps().get("last_name");
            Integer age = Utils.getAgeFromDate(clientsInfo.getColumnmaps().get("dob"));
            String gender = clientsInfo.getColumnmaps().get("gender");
            String location = clientsInfo.getColumnmaps().get("village_town");
            String uniqueID = clientsInfo.getColumnmaps().get("unique_id");

            String fullname = firstname + " " + middlename + " " + lastname + " , " + age;
            String Id = "ID: " + uniqueID;


            profilenameTV.setText(fullname);
            genderTV.setText(gender);
            locationTV.setText(location);
            idTV.setText(Id);

        }
        avatorIV.setImageDrawable(getResources().getDrawable(R.mipmap.ic_member));
        profileButton.setText("Record Provision of GE Services");

        profileButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                      Receive the information sent by intent i.e entity_id
                        String received_base_entity_id = getIntent().getStringExtra("entity_id");
                        startFormActivity("individual_ge_form", received_base_entity_id, null);

                    }
                }
        );
    }

    @Override
    public void startMicroFormActivity(String formName, String entityId, String metaData) {
        super.startMicroFormActivity(formName, entityId, metaData);
    }

    @SuppressLint("TimberArgCount")
    @Override
    public void startFormActivity(String formName, String entityId, String metaData) {
        super.startFormActivity(formName, entityId, metaData);
        try {

//              Convert JSON form to JSON object
            FormUtils formUtils = new FormUtils();
            JSONObject jsonObject =formUtils.getFormJsonFromRepositoryOrAssets(this,formName);

            Timber.d("MWAMBAAAA",jsonObject);

//              Link json Object to the json form using base_entity_id Key
            jsonObject.put("entity_id",clientsInfo.getColumnmaps().get("base_entity_id"));


//              Launch form from parent Activity using Intent
            Intent intent = new Intent(GeProfileActivity.this, JsonFormActivity.class);

//              Convert json form to String
            jsonObject.toString();
            intent.putExtra("json",jsonObject.toString());

//               Pass the requestCode and  Start the JsonFormActivity and wait for a result

            startActivityForResult(intent, 700);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 700 && resultCode == RESULT_OK){

//           Receive the data of type String using getStringExtra
            String filledForm = intent.getStringExtra("json");

            Timber.d("SHOW FILLED FORM "+filledForm );

//            Access some data such as userid, teamid that are binded to user, from shared_prefs
            AllSharedPreferences allSharedPreferences = Utils.getAllSharedPreferences();

//           Change the received filled form into an Event
            Event event = JsonFormUtils.processJsonForm(allSharedPreferences,filledForm,""); // add table name


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
}



