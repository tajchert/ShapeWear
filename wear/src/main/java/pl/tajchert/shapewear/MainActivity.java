package pl.tajchert.shapewear;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private TextView screenShapeText;
    private TextView screenSizeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenShapeText = (TextView) findViewById(R.id.ScreenShape);
        screenSizeText = (TextView) findViewById(R.id.ScreenSize);


        //"getIsRound()" would give us wrong result (exception), on first run here

        ShapeWear.setOnShapeChangeListener(new ShapeWear.OnShapeChangeListener() {
            @Override
            public void shapeDetected(boolean isRound) {
                //There is fine.
                Log.d(TAG, "shapeDetected isRound:" + isRound);
                Log.d(TAG, "shapeDetected getShape:" + ShapeWear.getShape());
                if(screenShapeText != null) {
                    screenShapeText.setText("isRound: " + isRound);
                }
            }
        });
        ShapeWear.setOnSizeChangeListener(new ShapeWear.OnSizeChangeListener() {
            @Override
            public void sizeDetected(int widthPx, int heightPx) {
                Log.d(TAG, "sizeDetected w:" + widthPx + ", h:" + heightPx);
                if(screenSizeText != null) {
                    screenSizeText.setText("size: " + widthPx + "x" + heightPx);
                }
            }
        });
        ShapeWear.initShapeWear(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //"getIsRound()" would give us wrong result (exception), on first run here
        Log.d(TAG, "onResume shape: " + ShapeWear.getShape()); //Exception proof method to get shape, would give us ShapeWear.SHAPE_UNSURE if not determined shape yet
        try {
            //Would be thrown on first run as shape will be still not detected when calling onResume()
            Log.d(TAG, "onResume shape: " + ShapeWear.isRound());
        } catch (ShapeWear.ScreenShapeNotDetectedException e) {
            Log.e(TAG, "ScreenShapeNotDetectedException from isRound: " + e.getMessage());
        }
    }
}
