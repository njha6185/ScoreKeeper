package com.example.nitishkumar.scorekeeper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends AppCompatActivity implements OnClickListener {

    private String teamName1, teamName2, noOfOvers;
    private TextView team1Text, team2Text, overText;
    private Button t1dotBall, t1Extras, t1oneRun, t1twoRun, t1threeRun, t1fourRun, t1fiveRun, t1sixRun, t1outPlayer,
            t2dotBall, t2Extras, t2oneRun, t2twoRun, t2threeRun, t2fourRun, t2fiveRun, t2sixRun, t2outPlayer, restartGame,
            resetScore;
    private int team1Run, team2Run, team1totalBalls,team2totalBalls, team1overNo, team2overNo,
            team1ballNo, team2ballNo, t1wicket, t2wicket;
    private TextView team1ScoreCard, team2ScoreCard, hintNotes;
    private int matchTotalOver, extraRuns;
    private boolean team1playChance, team2playChance;
    private boolean out1, extra1, out2, extra2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        setTeamNameAndOver();
    }

    public void getTeamNameAndOver()
    {
        Bundle bundle = getIntent().getExtras();
        teamName1 = bundle.getString("Team1");
        teamName2 = bundle.getString("Team2");
        noOfOvers = bundle.getString("Overs");
        matchTotalOver = Integer.parseInt(noOfOvers);
    }

    public void init()
    {
        team1Text = (TextView)findViewById(R.id.team1TextView);
        team2Text = (TextView)findViewById(R.id.team2TextView);
        overText = (TextView)findViewById(R.id.overTextView);

        t1dotBall = (Button)findViewById(R.id.t1zeroRunButon);              t1dotBall.setOnClickListener(this);
        t1Extras = (Button)findViewById(R.id.t1extra);                      t1Extras.setOnClickListener(this);
        t1oneRun = (Button)findViewById(R.id.t1oneRunButon);                t1oneRun.setOnClickListener(this);
        t1twoRun = (Button)findViewById(R.id.t1twoRunButon);                t1twoRun.setOnClickListener(this);
        t1threeRun = (Button)findViewById(R.id.t1threeRunButon);            t1threeRun.setOnClickListener(this);
        t1fourRun = (Button)findViewById(R.id.t1fourRunButon);              t1fourRun.setOnClickListener(this);
        t1fiveRun = (Button)findViewById(R.id.t1fiveRunButon);              t1fiveRun.setOnClickListener(this);
        t1sixRun = (Button)findViewById(R.id.t1sixRunButon);                t1sixRun.setOnClickListener(this);
        t1outPlayer = (Button)findViewById(R.id.t1out);                     t1outPlayer.setOnClickListener(this);

        t2dotBall = (Button)findViewById(R.id.t2zeroRunButon);              t2dotBall.setOnClickListener(this);
        t2Extras = (Button)findViewById(R.id.t2extra);                      t2Extras.setOnClickListener(this);
        t2oneRun = (Button)findViewById(R.id.t2oneRunButon);                t2oneRun.setOnClickListener(this);
        t2twoRun = (Button)findViewById(R.id.t2twoRunButon);                t2twoRun.setOnClickListener(this);
        t2threeRun = (Button)findViewById(R.id.t2threeRunButon);            t2threeRun.setOnClickListener(this);
        t2fourRun = (Button)findViewById(R.id.t2fourRunButon);              t2fourRun.setOnClickListener(this);
        t2fiveRun = (Button)findViewById(R.id.t2fiveRunButon);              t2fiveRun.setOnClickListener(this);
        t2sixRun = (Button)findViewById(R.id.t2sixRunButon);                t2sixRun.setOnClickListener(this);
        t2outPlayer = (Button)findViewById(R.id.t2out);                     t2outPlayer.setOnClickListener(this);

        restartGame = (Button)findViewById(R.id.restartButon);              restartGame.setOnClickListener(this);
        resetScore = (Button)findViewById(R.id.resetButon);                 resetScore.setOnClickListener(this);

        team1Run = 0;      team1totalBalls = 0;      team1overNo = 0;     team1ballNo = 0;      t1wicket = 0;
        team2Run = 0;      team2totalBalls = 0;      team2overNo = 0;     team2ballNo = 0;      t2wicket = 0;

        team1playChance = false;        team2playChance = false;

        team1ScoreCard = (TextView)findViewById(R.id.team1ScoreBoardTextView);
        team2ScoreCard = (TextView)findViewById(R.id.team2ScoreBoardTextView);

        hintNotes = (TextView)findViewById(R.id.winHintNotes);

        team2ScoreCard.setAlpha(0.4f);

        extraRuns = 0;

        out1 = false;        extra1 = false;        out2 = false;       extra2 = false;
    }

    public void setTeamNameAndOver()
    {
        getTeamNameAndOver();
        team1Text.setText(teamName1);
        team2Text.setText(teamName2);
        overText.setText(noOfOvers + "\nOV");
    }

    public void updateScoreBoardTeam1(int run, int wicket, int ball)
    {
        if(team1playChance)
        {
            team1ScoreCard.setAlpha(0.4f);
            team2ScoreCard.setAlpha(1f);
        }
        else
        {
            team2ScoreCard.setAlpha(0.4f);
            team1ScoreCard.setAlpha(1f);
            team1Run += run;
            t1wicket += wicket;
            team1totalBalls += ball;

            int over = team1totalBalls / 6;
            int ballNo = team1totalBalls % 6;

            String scoreText = "0/0\\n0.0";
            scoreText = team1Run + "/" + t1wicket + "\n" + over + "." + ballNo;
            team1ScoreCard.setText(scoreText);
            if (    (team1totalBalls == (matchTotalOver*6) && team1totalBalls!=0) || (t1wicket == 10 && t1wicket != 0)   )
            {
                team1ScoreCard.setAlpha(0.4f);
                team2ScoreCard.setAlpha(1f);
                team2playChance = true;
                team1playChance = true;
            }
        }
        resultDialogue();
    }

    public void updateScoreBoardTeam2(int run, int ball, int wicket)
    {
        if (team2playChance)
        {
            team1ScoreCard.setAlpha(0.4f);
            team2ScoreCard.setAlpha(1f);
            team2Run += run;
            t2wicket += wicket;
            team2totalBalls += ball;

            int over = team2totalBalls / 6;
            int ballNo = team2totalBalls % 6;

            String scoreText = "0/0\\n0.0";
            scoreText = team2Run + "/" + t2wicket + "\n" + over + "." + ballNo ;
            team2ScoreCard.setText(scoreText);

            if(    (team2totalBalls == (matchTotalOver*6) && team2totalBalls!=0) || (t2wicket == 10 && t2wicket != 0)   || (team2Run > team1Run))
            {
                team1ScoreCard.setAlpha(0.4f);
                team2ScoreCard.setAlpha(0.4f);
                team2playChance = false;
                team1playChance = true;
            }
        }
        else
        {
            team2ScoreCard.setAlpha(0.4f);
        }
        resultDialogue();
    }

    public void resultDialogue()
    {

        String msg1 = teamName1 + " NEED TO TAKE " + (10 - t2wicket) + " WICKET OR RESTRICT UNDER " + team1Run + " RUNS TO WIN and ";
        String msg2 = teamName2 + " NEED " + (team1Run - team2Run + 1) + " MORE RUNS TO WIN ";
        if (team2playChance)
        {
            hintNotes.setText(msg1 + msg2);
        }

        if(team2playChance == false && team1playChance == true)
        {
            if(team1Run > team2Run)
            {
                Toast.makeText(this, teamName1 + " WON THE MATCH BY " + (team1Run - team2Run) + " RUN FROM " + teamName2,
                        Toast.LENGTH_SHORT).show();

                String winMsg = teamName1 + " WON THE MATCH BY " + (team1Run - team2Run) + " RUN FROM " + teamName2;
                hintNotes.setText(winMsg);
                winningAlertDialogue(winMsg);
            }
            else if(team1Run < team2Run)
            {
                Toast.makeText(this, teamName2 + " WON THE MATCH BY " + (10 - t2wicket) + " WICKET FROM " + teamName1,
                        Toast.LENGTH_SHORT).show();
                String winMsg = teamName2 + " WON THE MATCH BY " + (10 - t2wicket) + " WICKET FROM " + teamName1;
                hintNotes.setText(winMsg);
                winningAlertDialogue(winMsg);
            }
            else
            {
                Toast.makeText(this, "MATCH DRAWN!!!", Toast.LENGTH_SHORT).show();
                String winMsg = "MATCH DRAWN!!!";
                winningAlertDialogue(winMsg);
            }
        }
    }

    public void momentAlertDialogue(String msg, boolean wicket)
    {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle(msg);
        if (wicket)
        {
            dialog.setMessage("Thats Great Bowling and Its OUT");
        }
        else
        {
            dialog.setMessage("Thats master Piece Stroke by Batsman and its "+ msg);
        }
        dialog.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    public void winningAlertDialogue(String msg)
    {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("MATCH RESULT SUMMARY !!!");
        dialog.setMessage(msg);
        dialog.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    public void extraAndOutChoiceDialogue(String ballType)
    {
        extraRuns = 0;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Choose Total Runs on " + ballType + " :");
        String[] choice = {"0+0", "1+0", "1+1", "1+2", "1+3", "1+4", "1+5", "1+6"};
        dialog.setItems(choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (extra1)
                {
                    updateScoreBoardTeam1(i, 0, 0);
                    extra1 = false;
                }
                if (out1)
                {
                    momentAlertDialogue(" OUT!!!", true);
                    if (i == 0)
                        updateScoreBoardTeam1(i, 1, 1);
                    else if (i > 0)
                        updateScoreBoardTeam1(i, 1, 0);
                    out1 = false;
                }
                if (extra2)
                {
                    updateScoreBoardTeam2(i, 0, 0);
                    extra2 = false;
                }
                if (out2)
                {
                    momentAlertDialogue(" OUT!!!", true);
                    if (i == 0)
                        updateScoreBoardTeam2(i, 1, 1);
                    else if (i > 0)
                        updateScoreBoardTeam2(i, 0, 1);
                    out2 = false;
                }
            }
        });
        AlertDialog alertDialog = dialog.create();
        dialog.show();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.t1zeroRunButon :
                updateScoreBoardTeam1(0, 0, 1);
                Toast.makeText(this, "DOT BALL", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t1extra :
                extra1 = true;
                extraAndOutChoiceDialogue("WIDE OR NO BALL");
                Toast.makeText(this, "Extra", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t1oneRunButon :
                updateScoreBoardTeam1(1, 0, 1);
                Toast.makeText(this, "One RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t1twoRunButon :
                updateScoreBoardTeam1(2, 0, 1);
                Toast.makeText(this, "2 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t1threeRunButon :
                updateScoreBoardTeam1(3, 0, 1);
                Toast.makeText(this, "3 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t1fourRunButon :
                momentAlertDialogue(" FOUR", false);
                updateScoreBoardTeam1(4, 0, 1);
                Toast.makeText(this, "4 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t1fiveRunButon :
                updateScoreBoardTeam1(5, 0, 1);
                Toast.makeText(this, "5 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t1sixRunButon :
                momentAlertDialogue(" SIXER", false);
                updateScoreBoardTeam1(6, 0, 1);
                Toast.makeText(this, "6 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t1out :
                out1 = true;
                extraAndOutChoiceDialogue("Runs On Ball along with wicket");
                Toast.makeText(this, "OUT", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t2zeroRunButon :
                updateScoreBoardTeam2(0, 1, 0);
                Toast.makeText(this, "DOT BALL", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t2extra :
                extra2 = true;
                extraAndOutChoiceDialogue("WIDE OR NO BALL");
                Toast.makeText(this, "Extra", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t2oneRunButon :
                updateScoreBoardTeam2(1,1, 0);
                Toast.makeText(this, "One RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t2twoRunButon :
                updateScoreBoardTeam2(2, 1, 0);
                Toast.makeText(this, "2 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t2threeRunButon :
                updateScoreBoardTeam2(3, 1, 0);
                Toast.makeText(this, "3 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t2fourRunButon :
                momentAlertDialogue(" FOUR", false);
                updateScoreBoardTeam2(4, 1, 0);
                Toast.makeText(this, "4 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t2fiveRunButon :
                updateScoreBoardTeam2(5, 1, 0);
                Toast.makeText(this, "5 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t2sixRunButon :
                momentAlertDialogue(" SIXER", false);
                updateScoreBoardTeam2(6, 1, 0);
                Toast.makeText(this, "6 RUN", Toast.LENGTH_SHORT).show();
                break;

            case R.id.t2out :
                out2 = true;
                extraAndOutChoiceDialogue("Runs On Ball along with wicket");
                Toast.makeText(this, "OUT", Toast.LENGTH_SHORT).show();
                break;

            case R.id.restartButon :
                init();
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Match Restarted", Toast.LENGTH_SHORT).show();
                break;

            case R.id.resetButon :
                init();
                updateScoreBoardTeam1(0, 0, 0);
                updateScoreBoardTeam2(0, 0, 0);
                Toast.makeText(this, "SCORE RESET", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}