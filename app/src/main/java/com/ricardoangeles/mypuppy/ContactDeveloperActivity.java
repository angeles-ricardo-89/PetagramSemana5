package com.ricardoangeles.mypuppy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ricardoangeles.mypuppy.mail.GMailSender;

public class ContactDeveloperActivity extends AppCompatActivity {
    private Button btSendComment;

    public void send_email(){
        EditText etName = (EditText) findViewById(R.id.etName);
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etMessage = (EditText) findViewById(R.id.etMessage);


        try {
            GMailSender sender = new GMailSender(getString(R.string.sender_email), getString(R.string.email_password));
            sender.setMailData("Comentario",
                    "Mensaje de " + etName.getText().toString() + "\n\n" + etMessage.getText().toString(),
                    etEmail.getText().toString(),
                    getString(R.string.sender_email));

            SendMail sm = new SendMail(this);
            sm.execute(sender);
            //sm.get();
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }

    public void configure_toolbar()
    {
        Toolbar abActionBar = (Toolbar) findViewById(R.id.abActionBar);
        setSupportActionBar(abActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void close_activity()
    {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_developer);
        configure_toolbar();
        btSendComment = (Button)findViewById(R.id.btSendComment);
        btSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_email();
            }
        });
    }
}
