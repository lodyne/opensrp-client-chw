package org.smartregister.chw.contract;

import org.smartregister.view.contract.BaseRegisterFragmentContract;

public class BloodRegisterFragmentContract implements BaseRegisterFragmentContract {
    public interface Model {
        public String getMainCondition();

        public String getTablename();

        public String getDefaultSortQuery();

        public String getMainSelect(String mainCondition);

        public String getCountSelect(String mainCondition);
    }
}
