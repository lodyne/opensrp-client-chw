package org.smartregister.chw.model;

import org.smartregister.chw.contract.MimiRegisterFragmentContract;
import org.smartregister.view.contract.BaseRegisterFragmentContract;

public class MimiRegisterFragmentModel implements MimiRegisterFragmentContract.Model {

    @Override
    public String getMainCondition() {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
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
