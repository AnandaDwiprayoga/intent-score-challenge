package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import static id.putraprima.skorbola.MatchActivity.KEY_TEAM;

public class MainActivity extends AppCompatActivity {

    private EditText etHome;
    private EditText etAway;
    private static final int REQUEST_HOME_LOGO = 1;
    private static final int REQUEST_AWAY_LOGO = 2;
    private Bitmap logoHome;
    private Bitmap logoAway;
    private ImageView btnHomeLogo;
    private ImageView btnAwayLogo;

    private boolean changeHomeLogo = false;
    private boolean changeAwayLogo = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHome = findViewById(R.id.home_team);
        etAway = findViewById(R.id.away_team);

        btnHomeLogo = findViewById(R.id.home_logo);
        btnAwayLogo = findViewById(R.id.away_logo);

        logoAway = logoHome = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);

        btnHomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,REQUEST_HOME_LOGO);
            }
        });

        btnAwayLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,REQUEST_AWAY_LOGO);
            }
        });

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    public void btnNextMain(View view) {
        if (TextUtils.isEmpty(etHome.getText())){
            Toast.makeText(this, "Lengkapi home team", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(etAway.getText())){
            Toast.makeText(this, "Lengkapi away team", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!changeHomeLogo){
            Toast.makeText(this, "Ganti gambar home team", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!changeAwayLogo){
            Toast.makeText(this, "Ganti gambar away team", Toast.LENGTH_SHORT).show();
            return;
        }


        TeamModel teamModel = new TeamModel(etHome.getText().toString(),etAway.getText().toString(), Bitmap.createScaledBitmap(logoHome, 200,200, false), Bitmap.createScaledBitmap(logoAway,200,200, false));

        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra(KEY_TEAM, teamModel);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED){
            return;
        }

        if (requestCode == REQUEST_HOME_LOGO){
            if (data != null){
                try {
                    Uri imageUri = data.getData();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        logoHome = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getContentResolver(),imageUri));
                    }else{
                        logoHome = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    }
                    btnHomeLogo.setImageBitmap(logoHome);
                    changeHomeLogo = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (requestCode == REQUEST_AWAY_LOGO){
            if (data != null){
                try {
                    Uri imageUri = data.getData();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        logoAway = ImageDecoder.decodeBitmap(ImageDecoder.createSource(getContentResolver(),imageUri));
                    }else{
                        logoAway = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    }
                    btnAwayLogo.setImageBitmap(logoAway);
                    changeAwayLogo = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
