package com.ebcompany.hs4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RunHSMainServiceAtBootTime extends BroadcastReceiver {
    public RunHSMainServiceAtBootTime() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(context.getString(R.string.log_message_tag), "Boot completed event is catched");
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {
            Intent in = new Intent(context, HSMainService.class);
            context.startService(in);
        }
        Log.i(context.getString(R.string.log_message_tag), "HSMainService is run");
    }
}
