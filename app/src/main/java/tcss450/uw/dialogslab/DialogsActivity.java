package tcss450.uw.dialogslab;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class DialogsActivity extends AppCompatActivity {

    private static int notificationID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
    }

    public void launch(View v){
        DialogFragment fragment = null;
        if (v.getId() == R.id.fire_missiles_button){
            fragment = new FireMissilesDialogFragment();
        } else if (v.getId() == R.id.list_button) {
            fragment = new ListDialogFragment();
        } else if (v.getId() == R.id.multi_list_button) {
            fragment = new MultiListDialogFragment();
        } else if (v.getId() == R.id.custom_button) {
            fragment = new CustomDialogFragment();
        } else if (v.getId() == R.id.time_button) {
            fragment = new TimePickerDialogFragment();
        } else if (v.getId() == R.id.date_button) {
        fragment = new DatePickerDialogFragment();
        } else if (v.getId() == R.id.notification_button){
            launchNotification();
        }
        if (fragment != null)
            fragment.show(getSupportFragmentManager(),"launch");
    }

    private void launchNotification(){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text));
        Intent resultIntent = new Intent(this, DialogsActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(DialogsActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID,builder.build());
    }
}
