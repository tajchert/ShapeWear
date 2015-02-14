package pl.tajchert.shapewear;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Would give us wrong result of "getIsRound()", on first run here


        ShapeWear.setOnShapeChangeListener(new ShapeWear.OnShapeChangeListener() {
            @Override
            public void shapeDetected(boolean isRound) {
                //There is fine.
                Log.d(TAG, "shapeDetected :" + isRound);
            }
        });
        ShapeWear.initShapeWear(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Would give us wrong result of "getIsRound()", on first run here
        Log.d(TAG, "onResume shape: " + ShapeWear.getShape());
        try {
            //Woul be thrown on first run as shape will be not detected when calling onResume
            Log.d(TAG, "onResume shape: " + ShapeWear.isRound());
        } catch (Exception e) {
            Log.e(TAG, "Exception from isRound: " + e.getLocalizedMessage());
        }
    }
}
