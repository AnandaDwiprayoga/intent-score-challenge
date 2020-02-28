package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    public static final String KEY_HASIL = "hasil";
    public static final String KEY_LOGO1 = "logo1";
    public static final String KEY_LOGO2 = "logo2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        TextView tvResult = findViewById(R.id.textView5);
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView1 = findViewById(R.id.imageView1);

        String result = getIntent().getStringExtra(KEY_HASIL);

        if (result.equals("DRAW")){
            tvResult.setText("DRAW");
            imageView.setImageBitmap((Bitmap) getIntent().getParcelableExtra(KEY_LOGO1));
            imageView1.setImageBitmap((Bitmap) getIntent().getParcelableExtra(KEY_LOGO2));
        }else{
            tvResult.setText(result.toUpperCase() + " WINNER");
            imageView.setImageBitmap((Bitmap) getIntent().getParcelableExtra(KEY_LOGO1));
            imageView1.setImageBitmap((Bitmap) getIntent().getParcelableExtra(KEY_LOGO1));
        }
    }
}
