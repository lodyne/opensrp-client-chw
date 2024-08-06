package org.smartregister.chw.model;

import org.smartregister.chw.contract.BloodRegisterFragmentContract;

public class BloodRegisterFragmentModel implements BloodRegisterFragmentContract.Model {
    @Override
    public String getMainCondition() {
        return "is_closed=0";
    }

    @Override
    public String getTablename() {
        return "ec_gender_equality";
    }

    @Override
    public String getDefaultSortQuery() {
        return null;
    }

    @Override
    public String getMainSelect(String mainCondition) {
        return null;
    }

    @Override
    public String getCountSelect(String mainCondition) {
        return null;
    }
}
