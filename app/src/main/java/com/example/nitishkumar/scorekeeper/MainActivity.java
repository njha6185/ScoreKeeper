package com.example.nitishkumar.scorekeeper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Region;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner team1Spinner, team2Spinner, noOfOverSpinner;
    private String teamName1, teamName2, noOfOvers;
    private Button startGameButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        findTeamOver();

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        teamName1 + " V/S " + teamName2 + " " + noOfOvers + "  Over Match",
                         Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("Team1", teamName1);
                intent.putExtra("Team2", teamName2);
                intent.putExtra("Overs", noOfOvers);
                if (teamName1.equalsIgnoreCase(teamName2))
                {
                    sameTeamAlertDialogue();
                }
                else
                {
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void sameTeamAlertDialogue()
    {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("LAZY BOY !!!");
        dialog.setMessage("Trying to fool me. I know Same Team Cannot Play Match to themselves. Select 2 Different Teams");
        dialog.setButton(Dialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.show();
    }

    public void setTeamListToSpinner()
    {
        ArrayAdapter<CharSequence> teamNameAdapter = ArrayAdapter.createFromResource(this, R.array.team_name,
                android.R.layout.simple_spinner_item);
        teamNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team1Spinner.setAdapter(teamNameAdapter);
        team2Spinner.setAdapter(teamNameAdapter);
    }

    public void setTeamOverListToSpinner()
    {
        ArrayAdapter<CharSequence> overListAdapter = ArrayAdapter.createFromResource(this, R.array.overs,
                android.R.layout.simple_spinner_item);
        overListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noOfOverSpinner.setAdapter(overListAdapter);
    }

    public void findTeamOver()
    {
        setTeamListToSpinner();
        setTeamOverListToSpinner();
        team1Spinner.setOnItemSelectedListener(this);
        team2Spinner.setOnItemSelectedListener(this);
        noOfOverSpinner.setOnItemSelectedListener(this);
    }

    public void init()
    {
        team1Spinner = (Spinner)findViewById(R.id.team1);
        team2Spinner = (Spinner)findViewById(R.id.team2);
        noOfOverSpinner = (Spinner)findViewById(R.id.over);
        startGameButton = (Button)findViewById(R.id.startGame);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId())
        {
            case R.id.team1 :
                teamName1 = adapterView.getItemAtPosition(i).toString();
                break;

            case R.id.team2 :
                teamName2 = adapterView.getItemAtPosition(i).toString();
                break;

            case R.id.over :
                noOfOvers = adapterView.getItemAtPosition(i).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
