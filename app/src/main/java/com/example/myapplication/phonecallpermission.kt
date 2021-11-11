package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
//import java.util.jar.Manifest
import android.Manifest  // using this as above was giving error
const val got_permission=0
class phonecallpermission : AppCompatActivity(),ActivityCompat.OnRequestPermissionsResultCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phonecallpermission)

        val showbtnph:Button=findViewById(R.id.call)

        showbtnph.setOnClickListener {
//            makephonecall(); // we cant make phone calls like this, we have to ask permission for it
            makephonecall_after_permission(it)
        }

    }
    // function to make the phone call
    private fun makephonecall()
    {
        val intent= Intent().apply{
            action=ACTION_CALL
            data= Uri.parse("tel: 9629677506")
        }
        startActivity(intent)
    }
    // function to check if permission is granted , if not then ask permission
    private fun makephonecall_after_permission(btn: View) {

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED) // permission is already granted so make call
        {
            makephonecall()
        }
        else // permission is not granted so request for permission
        {
            requestcall_permission(btn)
        }

    }
    // function to request permission for phone call
    private fun requestcall_permission(btn: View){

        //it checks if we need to show the permission or not, that is if user has previously denied the permission then we should show a dialogue and tell the details, why we
        // need the permission, for this we use snackbar
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)) // true means we should tell why we are asking permission
        {
            val snack=Snackbar.make(btn,"we need your permission to make call,pls allow the permission",Snackbar.LENGTH_INDEFINITE)

            // now creating a snack and tell user the reason and providing a button with "OK" text, then we will show the permissions
            snack.setAction("OK",View.OnClickListener {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),got_permission) // it shows the permission in the array and store the result in got_permission variable
            })
            snack.show()
        }
        else // this is first time we are asking permission so there is no need to show snack
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),got_permission)
        }

    }

    // checking weather we should proceed making the call if permission is given or we show a toast that permission os denied

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(grantResults.size==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            makephonecall()
        }
        else
        {
            Toast.makeText(this,"permission denied to make phone call",Toast.LENGTH_SHORT).show()
        }

    }


}