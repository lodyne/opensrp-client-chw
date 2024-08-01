package org.smartregister.chw.contract;

import android.content.Context;

import org.smartregister.view.contract.BaseRegisterFragmentContract;

public interface GeRegisterFragmentContract extends BaseRegisterFragmentContract {
    public interface Model {
        public String getMainCondition();

        public String getTablename();

        public String getDefaultSortQuery();

        public String getMainSelect(String mainCondition);

        public String getCountSelect(String mainCondition);
    }
}
