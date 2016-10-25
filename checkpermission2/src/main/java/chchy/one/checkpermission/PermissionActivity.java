package chchy.one.checkpermission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class PermissionActivity extends AppCompatActivity {

    public final static String PermissionType = "cn.com.itep.corelib.Permission.Type";
    public final static String PermissionTips = "cn.com.itep.corelib, Permission.Tips";
    public final static int PermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        if (getActionBar() != null){
            getActionBar().hide();
        }

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

//        Log.d("PermissionActivity", "onCreate");

        Intent intent = getIntent();
//        Log.d("PermissionActivity", "getIntent");
//        String permissionType = intent.getStringExtra(PermissionActivity.PermissionType);
        List<String> permissionTypes = intent.getStringArrayListExtra(PermissionActivity.PermissionType);
        String permissionTips = intent.getStringExtra(PermissionActivity.PermissionTips);
//        Log.d("PermissionActivity", permissionType);
        if (permissionTypes != null){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissionTypes.get(0))){
                //显示用户提示权限的作用，采用对话框的形式
                if (permissionTips == null || permissionTips.length() == 0){
                    requestPermissionDialog(getString(R.string.default_permission_tips), permissionTypes);
                }
                else {
                    switch (permissionTypes.get(0)){
                        case Manifest.permission.ACCESS_COARSE_LOCATION:
//                            requestPermissionDialog("蓝牙扫描打印需要使用位置权限，请授予权限，否则将无法使用蓝牙打印", permissionType);
                            requestPermissionDialog(permissionTips, permissionTypes);
                            break;

                        default:
                            finish();
                            break;
                    }
                }
            }
            else{
                ActivityCompat.requestPermissions(this, permissionTypes.toArray(new String[permissionTypes.size()]), PermissionCode);
            }
        }
        else{
            finish();
        }
    }

    protected void requestPermissionDialog(String message, final List<String> permissionTypes){
        new AlertDialog.Builder(this).setTitle("权限申请").setMessage(message).setPositiveButton("授予", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(PermissionActivity.this, permissionTypes.toArray(new String[permissionTypes.size()]), PermissionCode);
            }
        }).setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PermissionCode:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //权限被授予，返回继续执行
//                    BluetoothPrinter.getInstance(this).permissionResult(grantResults[0]);
                    PermissionCheck.sPermissionResult.permissionResult(grantResults[0]);
                    PermissionCheck.sPermissionResult = null;
                }
                else{
                    //权限被拒绝，提示用户后，返回不执行
                }
                break;
        }

        finish();
    }
}
