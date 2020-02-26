package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    public static final String KEY_RESULT = "key_result";
    public static final String KEY_PEMAIN = "key_pemain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvResult = findViewById(R.id.textView3);

        String result = getIntent().getStringExtra(KEY_RESULT);
        if (result.equals("DRAW")){
            tvResult.setText("DRAW");
        }else{
            String txtResult = result + " WIN\n";
            ArrayList<String> daftarPemain = getIntent().getStringArrayListExtra(KEY_PEMAIN);
            for (int i = 0; i< daftarPemain.size();i++){
                txtResult += daftarPemain.get(i) + "\n";
            }
            tvResult.setText(txtResult);
        }
    }
}
