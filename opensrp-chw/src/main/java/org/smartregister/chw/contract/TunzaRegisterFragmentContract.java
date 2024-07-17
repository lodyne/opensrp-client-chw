package org.smartregister.chw.contract;

import org.smartregister.view.contract.BaseRegisterContract;
import org.smartregister.view.contract.BaseRegisterFragmentContract;
import org.smartregister.view.fragment.BaseRegisterFragment;

public interface TunzaRegisterFragmentContract extends BaseRegisterFragmentContract {

    public interface Model{
        public  String getMainCondition();

        public String getTableName();

        public String getDefaultSortQuery();

        public String getMainSelect(String mainCondition);

        public String getCountSelect(String mainCondition);


    }

}
