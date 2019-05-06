package com.ricardoangeles.mypuppy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ricardoangeles.mypuppy.mail.GMailSender;

public class SendMail extends AsyncTask<GMailSender, Void, Void> {
    private ProgressDialog progressDialog;
    private Context context;
    private ContactDeveloperActivity act;


    public SendMail(Context context){
        this.context = context;
        this.act = (ContactDeveloperActivity) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, context.getString(R.string.send_feedback),
                                             context.getString(R.string.sending_feedback),
                                             false, false);
    }
    @Override
    protected Void doInBackground(GMailSender ...params) {
        int count = params.length;
        for(int i = 0; i < count; i++){
            try{
                params[i].sendMail();
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context, context.getString(R.string.feedback_sent), Toast.LENGTH_LONG).show();
        act.close_activity();
    }
}
