package chchy.one.checkpermission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 辉 on 2016/8/5.
 */
public class PermissionCheck {
    public static PermissionResult sPermissionResult = null;

    public static boolean checkPermission(Context context, String checkPermission, PermissionResult permissionResult){
        sPermissionResult = permissionResult;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT && ContextCompat.checkSelfPermission(context, checkPermission) != PackageManager.PERMISSION_GRANTED){
            Log.d("StartWirelessPrint", "ContextCompat");
            Log.d("StartWirelessPrint", "Permission Result is " + ContextCompat.checkSelfPermission(context, checkPermission));

//            PermissionActivity permissionActivity = new PermissionActivity();
            ArrayList<String> requestPermissions = new ArrayList<>();
            requestPermissions.add(checkPermission);
            Intent intent = new Intent(context, PermissionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra(PermissionActivity.PermissionType, checkPermission);
            intent.putStringArrayListExtra(PermissionActivity.PermissionType, requestPermissions);
//            intent.putExtra(PermissionActivity.PermissionTips, "蓝牙扫描打印需要使用位置权限，请授予权限，否则将无法使用蓝牙打印");
            context.startActivity(intent);

            return false;
        }
        else{
            return true;
        }
    }

    public static boolean checkPermission(Context context, String checkPermission, String checkTips, PermissionResult permissionResult){
        sPermissionResult = permissionResult;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT && ContextCompat.checkSelfPermission(context, checkPermission) != PackageManager.PERMISSION_GRANTED){
            Log.d("StartWirelessPrint", "ContextCompat");
            Log.d("StartWirelessPrint", "Permission Result is " + ContextCompat.checkSelfPermission(context, checkPermission));

//            PermissionActivity permissionActivity = new PermissionActivity();
            ArrayList<String> requestPermissions = new ArrayList<>();
            requestPermissions.add(checkPermission);
            Intent intent = new Intent(context, PermissionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra(PermissionActivity.PermissionType, checkPermission);
            intent.putStringArrayListExtra(PermissionActivity.PermissionType, requestPermissions);
            intent.putExtra(PermissionActivity.PermissionTips, checkTips);
            context.startActivity(intent);

            return false;
        }
        else{
            return true;
        }
    }

    public static boolean checkPermission(Context context, String[] checkPermissions, PermissionResult permissionResult){
        sPermissionResult = permissionResult;
        //Manifest.permission.ACCESS_COARSE_LOCATION

        ArrayList<String> requestPermissions = new ArrayList<>();
        for (String permission: checkPermissions) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT && ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions.add(permission);
            }
        }

        if (requestPermissions.size() > 0){
            Intent intent = new Intent(context, PermissionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putStringArrayListExtra(PermissionActivity.PermissionType, requestPermissions);
            context.startActivity(intent);
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean hasPermission(Context context, String checkPermission, PermissionResult permissionResult){
        sPermissionResult = permissionResult;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT && ContextCompat.checkSelfPermission(context, checkPermission) != PackageManager.PERMISSION_GRANTED){
            return false;
        }
        else{
            return true;
        }
    }
}
