package com.example.sha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {

    String input1;

    TextView headText, resultView;
    EditText inputText;
    Button convertText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         headText = (TextView)findViewById(R.id.headText);
         resultView = (TextView)findViewById(R.id.resultView);
         inputText = (EditText)findViewById(R.id.inputText);
         convertText = (Button)findViewById(R.id.convertText);


         convertText.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 input1 = inputText.getText().toString();
                 resultView.setText(encryptThisString(input1));
             }

             public String encryptThisString(String input1) {
                 try {
                     // getInstance() method is called with algorithm SHA-1
                     MessageDigest md = MessageDigest.getInstance("SHA-1");

                     // digest() method is called
                     // to calculate message digest of the input string
                     // returned as array of byte
                     byte[] messageDigest = md.digest(input1.getBytes());

                     // Convert byte array into signum representation
                     BigInteger no = new BigInteger(1, messageDigest);

                     // Convert message digest into hex value
                     String hashtext = no.toString(16);

                     // Add preceding 0s to make it 32 bit
                     while (hashtext.length() < 32) {
                         hashtext = "0" + hashtext;
                     }

                     // return the HashText
                     return hashtext;
                 }
                 // For specifying wrong message digest algorithms
                 catch (NoSuchAlgorithmException e) {
                     throw new RuntimeException(e);
                 }
             }

    });}}


