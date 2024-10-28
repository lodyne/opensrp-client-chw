package org.smartregister.chw.model;



import org.json.JSONObject;
import org.smartregister.chw.core.contract.CoreGeProfileContract;
import org.smartregister.chw.core.utils.CoreConstants;
import org.smartregister.chw.util.Utils;
import org.smartregister.commonregistry.CommonPersonObjectClient;

public class GeProfileModel implements CoreGeProfileContract.Model {

    @Override
    public CoreGeProfileContract.ClientInfo getClientsInfo(CommonPersonObjectClient clientsInfo) {
        String firstname = clientsInfo.getColumnmaps().get("first_name");
        String middlename = clientsInfo.getColumnmaps().get("middle_name");
        String lastname = clientsInfo.getColumnmaps().get("last_name");
        Integer age = Utils.getAgeFromDate(clientsInfo.getColumnmaps().get("dob"));
        String gender = clientsInfo.getColumnmaps().get("gender");
        String location = clientsInfo.getColumnmaps().get("village_town");
        String uniqueID = clientsInfo.getColumnmaps().get("unique_id");

        return new CoreGeProfileContract.ClientInfo(firstname, middlename, lastname, age, gender, location, uniqueID);
    }


    public String getMainCondition(String mainCondition){
        return "base_entity_id="+mainCondition;
    }
    public String getViewHistory(String mainCondition){
        return "SELECT * FROM "+CoreConstants.TABLE_NAME.GENDER_SERVICES+ " WHERE "+getMainCondition(mainCondition);

    }

}
