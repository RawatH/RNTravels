package rn.travels.in.rntravels.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import rn.travels.in.rntravels.R;
import rn.travels.in.rntravels.util.Util;

/**
 * Created by demo on 17/03/18.
 */

public class NotificationFragment  extends BackFragment {

    private Switch notificationSwitch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        notificationSwitch = view.findViewById(R.id.notificationSwitch);
        notificationSwitch.setChecked(Util.isNotificationEnabled(ctx));
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Util.updateNotifSetting(ctx,isChecked);
            }
        });
    }


    @Override
    public String getTitle() {
        return "Notification Setting";
    }
}