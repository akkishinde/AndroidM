package com.example.leftshift.androidm;

import android.content.pm.PackageManager;

/**
 * Created by leftshift on 10/6/15.
 */
public class PermissionUtil {
    private static boolean MNC;

    public static boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if(grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
