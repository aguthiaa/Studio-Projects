package com.peter.telephonymanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView phoneDetails;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneDetails = findViewById(R.id.textView);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        //Calling the methods of telephony manager that returns the information
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        String IMEINumber = tm.getDeviceId();
        String SubscriberId=tm.getDeviceId();
        String SIMSerialNumber=tm.getSimSerialNumber();
        String networkCountryISO=tm.getNetworkCountryIso();
        String SIMCountryISO=tm.getSimCountryIso();
        String softwareVersion=tm.getDeviceSoftwareVersion();
        String voiceMailNumber=tm.getVoiceMailNumber();


        //Get the phone type

        String strPhoneType="";


        int phoneType=tm.getPhoneType();

        switch (phoneType){
            case (TelephonyManager.PHONE_TYPE_CDMA):

                strPhoneType="CMDA";
                break;

            case (TelephonyManager.PHONE_TYPE_GSM):
                strPhoneType="GSM";
                break;

            case (TelephonyManager.PHONE_TYPE_NONE):
                strPhoneType="NONE";
                break;
        }

        //getting information if phone is roaming

        boolean isRoaming=tm.isNetworkRoaming();

        String info="Phone Details : \n";
        info+="\n IMEI Number : "+IMEINumber;
        info+="\n Subscriber ID : "+SubscriberId;
        info+="\n SIM Serial Number : "+SIMSerialNumber;
        info+="\n Network Country ISO : "+networkCountryISO;
        info+="\n SIM Country ISO : "+SIMCountryISO;
        info+="\n Software Version : "+softwareVersion;
        info+="\n Voice Mail Number : "+voiceMailNumber;
        info+="\n Phone Network Type : "+strPhoneType;
        info+="\n is Roaming:? "+isRoaming;

        phoneDetails.setText(info);

    }
}
