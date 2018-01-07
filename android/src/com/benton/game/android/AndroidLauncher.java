package com.benton.game.android;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.benton.game.MyGdxGame;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        // Create the layout
	        RelativeLayout layout = new RelativeLayout(this);

	        // Do the stuff that initialize() would do for you
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

	        // Create the libgdx View
	        View gameView = initializeForView(new MyGdxGame());

	        // Create and setup the AdMob view
	        AdView adView = new AdView(this);
	        adView.setAdUnitId("ca-app-pub-4978930607491155/5099950621");
	        adView.setAdSize(AdSize.BANNER);// Put in your secret key here
	        AdRequest adRequest = new AdRequest.Builder().build();
	        
	        adView.loadAd(adRequest);

	        // Add the libgdx view
	        layout.addView(gameView);

	        // Add the AdMob view
	        RelativeLayout.LayoutParams adParams = 
	            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
	                    RelativeLayout.LayoutParams.WRAP_CONTENT);
	        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

	        layout.addView(adView, adParams);

	        // Hook it all up
	        setContentView(layout);
	    }
}