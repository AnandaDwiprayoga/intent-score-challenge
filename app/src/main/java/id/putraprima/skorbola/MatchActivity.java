package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static id.putraprima.skorbola.ResultActivity.KEY_PEMAIN;
import static id.putraprima.skorbola.ResultActivity.KEY_RESULT;
import static id.putraprima.skorbola.ScorerActivity.AWAY_SENDER;
import static id.putraprima.skorbola.ScorerActivity.HOME_SENDER;
import static id.putraprima.skorbola.ScorerActivity.SENDER_KEY;
import static id.putraprima.skorbola.WinnerActivity.KEY_HASIL;
import static id.putraprima.skorbola.WinnerActivity.KEY_LOGO1;
import static id.putraprima.skorbola.WinnerActivity.KEY_LOGO2;

public class MatchActivity extends AppCompatActivity {

    public static final String KEY_TEAM = "team";
    private static final int REQUEST_SCORER = 100;
    public static final int RESULT_HOME = 105;
    public static final int RESULT_AWAY= 106;
    public static final String NAME_SCORER = "scorer_name";

    private TextView txtHome;
    private TextView txtAway;
    private TextView txtHomeScore;
    private TextView txtAwayScore;

    private ImageView logoHome;
    private ImageView logoAway;


//    private ArrayList<String> homeScorer = new ArrayList<>();
//    private ArrayList<String> awayScorer = new ArrayList<>();

    private TextView tvHomeResult;
    private TextView tvAwayResult;
    private TextView tvTimer;
    int timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_lands);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        txtHome  = findViewById(R.id.txt_home);
        txtAway  = findViewById(R.id.txt_away);
        logoHome = findViewById(R.id.home_logo);
        logoAway = findViewById(R.id.away_logo);
        txtHomeScore = findViewById(R.id.score_home);
        txtAwayScore = findViewById(R.id.score_away);

        tvHomeResult = findViewById(R.id.resultHome);
        tvAwayResult = findViewById(R.id.resultAway);
        tvTimer      = findViewById(R.id.tv_timer);

        final TeamModel teamInput = getIntent().getParcelableExtra(KEY_TEAM);

        txtHome.setText(teamInput.getNamaTeamHome());
        txtAway.setText(teamInput.getNamaTeamAway());
        logoHome.setImageBitmap(teamInput.getLogoTeamHome());
        logoAway.setImageBitmap(teamInput.getLogoTeamAway());

        txtAwayScore.setText("0");
        txtHomeScore.setText("0");

        timer = 60;
        tvTimer.setText(timer + "");
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(timer + "");
                timer = timer-1;
            }

            @Override
            public void onFinish() {
                tvTimer.setText("Finish");

                int scoreHome = Integer.parseInt(txtHomeScore.getText().toString());
                int scoreAway = Integer.parseInt(txtAwayScore.getText().toString());

                Intent intent = new Intent(getApplicationContext(), WinnerActivity.class);
                if (scoreHome == scoreAway){
                    intent.putExtra(KEY_HASIL, "DRAW");
                    intent.putExtra(KEY_LOGO1, teamInput.getLogoTeamHome());
                    intent.putExtra(KEY_LOGO2, teamInput.getLogoTeamAway());
                }else if (scoreAway < scoreHome){
                    intent.putExtra(KEY_HASIL, txtHome.getText().toString());
                    intent.putExtra(KEY_LOGO1, teamInput.getLogoTeamHome());
                }else{
                    intent.putExtra(KEY_HASIL, txtAway.getText().toString());
                    intent.putExtra(KEY_LOGO1, teamInput.getLogoTeamAway());
                }

                startActivity(intent);
            }
        }.start();
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",
    }

    public void addHomeScorer(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        intent.putExtra(SENDER_KEY,HOME_SENDER);
        startActivityForResult(intent,REQUEST_SCORER);
    }

    public void addAwayScorer(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        intent.putExtra(SENDER_KEY,AWAY_SENDER);
        startActivityForResult(intent,REQUEST_SCORER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SCORER){
            if (resultCode == RESULT_HOME){
                int homeScore = Integer.parseInt(txtHomeScore.getText().toString()) + 1;
                txtHomeScore.setText(homeScore + "");
//                homeScorer.add(data.getStringExtra(NAME_SCORER));
                String currentHomeScorer = tvHomeResult.getText().toString();
                tvHomeResult.setText(currentHomeScorer + "\n" + data.getStringExtra(NAME_SCORER));
            }else if (resultCode == RESULT_AWAY){
                int awayScore = Integer.parseInt(txtAwayScore.getText().toString()) + 1;
                txtAwayScore.setText(awayScore + "");
                String currentAwayScorer = tvAwayResult.getText().toString();
                tvAwayResult.setText(currentAwayScorer + "\n" + data.getStringExtra(NAME_SCORER));
            }
        }
    }

//    public void btnCetakHasil(View view) {
//        int scoreHome = Integer.parseInt(txtHomeScore.getText().toString());
//        int scoreAway = Integer.parseInt(txtAwayScore.getText().toString());
//
//        Intent intent = new Intent(this, ResultActivity.class);
//        if (scoreHome == scoreAway){
//            intent.putExtra(KEY_RESULT, "DRAW");
//        }else if (scoreAway < scoreHome){
//            intent.putExtra(KEY_RESULT, txtHome.getText().toString());
//            intent.putExtra(KEY_PEMAIN, homeScorer);
//        }else{
//            intent.putExtra(KEY_RESULT, txtAway.getText().toString());
//            intent.putExtra(KEY_PEMAIN, awayScorer);
//        }
//        startActivity(intent);
//    }
}



