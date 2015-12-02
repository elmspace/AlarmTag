package com.kash.alarmtag;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class TestActivity extends Activity {

	private static final String TAG = "NFC";
	private NfcAdapter nfcAdapter;
	private PendingIntent nfcPendingIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		// initialize NFC
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		nfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "enableForegroundMode");

		// foreground mode gives the current active application priority for
		// reading scanned tags
		IntentFilter tagDetected = new IntentFilter(
				NfcAdapter.ACTION_TAG_DISCOVERED); // filter for tags
		IntentFilter[] writeTagFilters = new IntentFilter[] { tagDetected };
		nfcAdapter.enableForegroundDispatch(this, nfcPendingIntent,
				writeTagFilters, null);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		Log.d(TAG, "onNewIntent");

		// check for NFC related actions
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {

			Context context = getApplicationContext();
			CharSequence text = "Your NFC is detected . . .";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();

			Intent intent_back = new Intent(TestActivity.this,
					MainActivity.class);
			startActivity(intent_back);

		}

	}

}
