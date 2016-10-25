package chchy.one.myapplication;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import chchy.one.checkpermission.PermissionCheck;
import chchy.one.checkpermission.PermissionResult;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionCheck.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionResult() {
            @Override
            public void permissionResult(int resultCode) {
                Log.d(TAG, "resultCode = " + resultCode);
            }
        });
    }
}
