package org.smartregister.chw.model;

import org.smartregister.chw.contract.GeRegisterFragmentContract;

public class GeRegisterFragmentModel implements GeRegisterFragmentContract.Model {
    @Override
    public String getMainCondition() {
        return "gender='Female'";
    }

    @Override
    public String getTablename() {
        return "ec_family_member";
    }

    @Override
    public String getDefaultSortQuery() {
        return "last_interacted_with DESC";
    }

    @Override
    public String getMainSelect(String mainCondition) {
        return "SELECT id as _id, *  FROM " +getTablename()+ " WHERE " +mainCondition;
    }

    @Override
    public String getCountSelect(String mainCondition) {
        return "SELECT COUNT(*) FROM " +mainCondition;
    }
}
