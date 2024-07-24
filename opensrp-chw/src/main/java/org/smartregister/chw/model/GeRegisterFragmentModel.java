package org.smartregister.chw.model;

import org.smartregister.chw.contract.GeRegisterFragmentContract;
import org.smartregister.chw.core.utils.CoreConstants;

public class GeRegisterFragmentModel implements GeRegisterFragmentContract.Model {
    @Override
    public String getMainCondition() {
        return "is_closed=0";
    }

    @Override
    public String getTablename() {
        return "ec_gender_equality";
//        return CoreConstants.TABLE_NAME.FAMILY_MEMBER;
    }

    @Override
    public String getDefaultSortQuery() {
        return "last_interacted_with DESC";
    }

    @Override
    public String getMainSelect(String mainCondition) {
        return "SELECT efm.id as _id, efm.*, ege.*, fam.village_town FROM " +getTablename()+ " efm " +
                "INNER JOIN " +CoreConstants.TABLE_NAME.FAMILY_MEMBER+  " ege " +
                "ON efm.base_entity_id = ege.base_entity_id " +
                "INNER JOIN "+CoreConstants.TABLE_NAME.FAMILY+ " fam " +
                "ON ege.relational_id = fam.base_entity_id " +"WHERE efm." + mainCondition;
    }

    @Override
    public String getCountSelect(String mainCondition) {
        return "SELECT COUNT(*) FROM " +mainCondition;
    }
}
