package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.graphics.drawable.ColorDrawable




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


         val showname:TextView=findViewById(R.id.textView12)
        val showbtn:Button=findViewById(R.id.button1)
        val showbtn2:Button=findViewById(R.id.button2)

        var ctr:Int=0
        var flag:Boolean=false;
        showbtn.setOnClickListener{
            ctr=ctr+1;
                showname.text="button is clicked $ctr times"
//            Toast.makeText(this,"toast is created",Toast.LENGTH_SHORT).show()

            }
        showbtn2.setOnClickListener{
            if(flag==false)showbtn2.text="Enable"
            else
                showbtn2.text="Disable"
            showbtn.isEnabled=flag
            flag=(!flag)
        }
        showbtn.setOnLongClickListener{
            showname.text="button is clicked for a long time"
            true
        }
        // implicit intent

        val showbtn4:Button=findViewById(R.id.button4)
        val edittext:EditText=findViewById(R.id.editText1)
        val urll=edittext.text;
        showbtn4.setOnClickListener{
            val intent=Intent().apply{
                action= Intent.ACTION_VIEW
                data= Uri.parse(urll.toString())
            }
            val any = if (intent.resolveActivity(packageManager) != null) {
                val title="start this app with..."
                val chooser=Intent.createChooser(intent,title)
                startActivity(chooser)
            } else {
                Toast.makeText(this, "enter a address to search!!", Toast.LENGTH_LONG);
            }

        }

        // explicit intent

        val showbtn6:Button=findViewById(R.id.button6)
        showbtn6.setOnClickListener {

            val intent=Intent(this,MainActivity2::class.java).apply {
                putExtra("name","sayan adak")
            }
            startActivity(intent)

        }
        // phone call permission

        val showbtnph:Button=findViewById(R.id.button7)
        showbtnph.setOnClickListener {
            val intent=Intent(this,phonecallpermission::class.java)
            startActivity(intent)
        }


    }
}