package org.smartregister.chw.model;

import org.smartregister.chw.contract.TunzaRegisterFragmentContract;

public class TunzaRegisterFragmentModel implements TunzaRegisterFragmentContract.Model {
    @Override
    public String getMainCondition() {
        return "is_closed = 0";
    }

    @Override
    public String getTableName() {
        return "ec_family_member";
    }

    @Override
    public String getDefaultSortQuery() {
        return "last_interacted_with DESC";
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
