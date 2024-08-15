package org.smartregister.chw.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
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
import org.smartregister.chw.contract.GeRegisterFragmentContract;
import org.smartregister.chw.core.contract.CoreGeProfileContract;
import org.smartregister.chw.fragment.GeRegisterFragment;
import org.smartregister.chw.model.GeProfileModel;
import org.smartregister.chw.model.GeRegisterFragmentModel;
import org.smartregister.chw.presenter.GeProfilePresenter;
import org.smartregister.chw.presenter.GeRegisterFragmentPresenter;
import org.smartregister.chw.util.Utils;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.view.activity.BaseProfileActivity;
import org.smartregister.view.contract.BaseProfileContract;
import org.smartregister.view.customcontrols.CustomFontTextView;

import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

public class GeProfileActivity extends BaseProfileActivity implements CoreGeProfileContract.View {
    CommonPersonObjectClient commonPersonObjectClient;
    private GeProfilePresenter presenter;
    @Override
    protected void initializePresenter() {
        GeProfileModel geProfileModel = new GeProfileModel();
        presenter = new GeProfilePresenter(geProfileModel);

    }

    @Override
    protected ViewPager setupViewPager(ViewPager viewPager) {
        return null;
    }

    @Override
    protected void fetchProfileData() {

    }


    @SuppressLint("TimberArgCount")
    @Override
    protected void setupViews() {
        super.setupViews();

        Intent intent = getIntent();

        commonPersonObjectClient = (CommonPersonObjectClient) intent.getSerializableExtra("client_info");

        CircleImageView avatorIV = findViewById(R.id.imageview_profile);
        CustomFontTextView profilenameTV = findViewById(R.id.textview_name);
        CustomFontTextView genderTV = findViewById(R.id.textview_detail_one);
        CustomFontTextView locationTV = findViewById(R.id.textview_detail_two);
        CustomFontTextView idTV = findViewById(R.id.textview_detail_three);
        Button profileButton = findViewById(R.id.btn_profile_registration_info);

        CoreGeProfileContract.ClientInfo clientInfo = presenter.getClientsInfo(commonPersonObjectClient);
        Timber.d("message", clientInfo);
        if (clientInfo != null) {
            profilenameTV.setText(clientInfo.getFullname());
            genderTV.setText(clientInfo.gender);
            locationTV.setText(clientInfo.location);
            idTV.setText(clientInfo.getUniqueId());
        }

//        Access image
        avatorIV.setImageDrawable(getResources().getDrawable(R.mipmap.ic_member));

//        Set Text in the Button - use strings resource
        profileButton.setText("Record Provision of GE Services");

//        Set listener on button once user clicks
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


    @SuppressLint("TimberArgCount")
    @Override
    public void startFormActivity(String formName, String entityId, String metaData) {
        super.startFormActivity(formName, entityId, metaData);
        try {

//              Convert JSON form to JSON object
            FormUtils formUtils = new FormUtils();
            JSONObject jsonObject =formUtils.getFormJsonFromRepositoryOrAssets(this,formName);

//              Link json Object to the json form using base_entity_id Key
            jsonObject.put("entity_id",commonPersonObjectClient.getColumnmaps().get("base_entity_id"));

//            Call startFormActivity method
            startFormActivity(jsonObject);

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

            presenter.convertDataToEvent(filledForm);

        }
    }

    @Override
    public void startFormActivity(JSONObject jsonObject) {
//        Launch form from parent Activity using Intent
        Intent intent = new Intent(GeProfileActivity.this, JsonFormActivity.class);

//        Convert json form to String
        intent.putExtra("json",jsonObject.toString());

//        Pass the requestCode and  Start the JsonFormActivity and wait for a result

        startActivityForResult(intent, 700);

    }
}



