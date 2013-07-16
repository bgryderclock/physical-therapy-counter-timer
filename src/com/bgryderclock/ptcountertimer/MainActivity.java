package com.bgryderclock.ptcountertimer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
//import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	int iCounter = 0;
	MediaPlayer mp10;
	MediaPlayer mp5;
	MediaPlayer mp3; 

	//@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);

		mp5=MediaPlayer.create(getBaseContext(), R.raw.onetofive);     
		mp3=MediaPlayer.create(getBaseContext(), R.raw.onetothree); 
		mp10=MediaPlayer.create(getBaseContext(), R.raw.onetoten); 
		final Button bstart = (Button) findViewById(R.id.bStart);
		final Button breset = (Button) findViewById(R.id.bReset);
		final Button bchange = (Button) findViewById(R.id.bChangeCount);
		final Button bdonate = (Button) findViewById(R.id.bDonate);

		bstart.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				//Log.i("PT", "start button");

				String bchangetext = bchange.getText().toString();

				if(mp10.isPlaying() || mp5.isPlaying() || mp3.isPlaying()){
					//do nothing
				} //end if	
				else{

					// play the sound per the button text
					if(bchangetext.equals("5 Sec Count (Click to change)")){
						mp5.start();
					}
					else if(bchangetext.equals("10 Sec Count (Click to change)")){
						mp10.start();
					}
					else{ //it's a 3 Sec Count 
						mp3.start();
					}
					iCounter++;	
					bstart.setText(String.valueOf(iCounter));
				}// end else

			}// void onClick
		});// end b start listener

		breset.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				//Log.i("PT", "reset button");

				iCounter = 0;
				bstart.setText(String.valueOf("Start"));
	
				if(mp10.isPlaying()){
					mp10.stop();
				} // end if
				if(mp5.isPlaying()){
					mp5.stop();
				} // end if
				if(mp3.isPlaying()){
					mp3.stop();
				} // end if
				
			}// void onClick
		});// end b reset listener

		bchange.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//bstart.setText("Starting");

				String bchangetext = bchange.getText().toString();

				//Log.i("PT", "change button");
				//Log.i("PT", bchangetext);


				if(bchangetext.equals("5 Sec Count (Click to change)")){
					bchange.setText(String.valueOf("10 Sec Count (Click to change)"));
				}
				else if(bchangetext.equals("10 Sec Count (Click to change)")){
					bchange.setText(String.valueOf("3 Sec Count (Click to change)"));
				}
				else if(bchangetext.equals("3 Sec Count (Click to change)")){
					bchange.setText(String.valueOf("5 Sec Count (Click to change)"));
				}
				else{
					bchange.setText(String.valueOf("err:" + bchangetext));
				}

			}// void onClick
		});// end b reset listener

		bdonate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				//http://bgryderclock.blogspot.com/p/donate.html
				String url = "http://bgryderclock.blogspot.com/p/donate.html";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}// void onClick
		});// end b reset listener

	} //end onCreate

} //end public class MainActivity extends Activity 
