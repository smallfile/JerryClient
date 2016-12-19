package com.jerry.sample.widget.progressbutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jerry.sample.R;
import com.jerry.uilib.widget.progressbutton.SubmitProcessButton;

public class MessageActivity extends Activity implements ProgressGenerator.OnCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_button_message);

        final EditText editMessage = (EditText) findViewById(R.id.editMessage);

        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        final SubmitProcessButton btnSend = (SubmitProcessButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnSend);
                btnSend.setEnabled(false);
                editMessage.setEnabled(false);
            }
        });
    }

    @Override
    public void onComplete() {
        Toast.makeText(this, R.string.progress_button_Loading_Complete, Toast.LENGTH_LONG).show();
    }

}
