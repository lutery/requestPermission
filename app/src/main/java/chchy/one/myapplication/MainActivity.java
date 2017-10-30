package chchy.one.myapplication;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//        List<String> testList = new ArrayList<>(){{add("1")}, {add("2")}, {add("3")}};
//
//        List<String> resList = testList.stream().filter(num -> num.equals("1")).collect(Collectors.toList());

//        new Thread(() -> System.out.print("hello")).start();
    }
}
