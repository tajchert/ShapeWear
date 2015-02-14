/*
The MIT License (MIT)

Copyright (c) 2015 Michal Tajchert

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package pl.tajchert.shapewear;

import android.app.Activity;
import android.view.View;
import android.view.WindowInsets;

public class ShapeWear {
    public static final String SHAPE_ROUND = "round";
    public static final String SHAPE_RECTANGLE = "rect";
    public static final String SHAPE_UNSURE = "unsure";

    private static String shape = SHAPE_UNSURE;
    private static OnShapeChangeListener onShapeChangeListener;

    /**
     * Initialized at any moment of app life cycle to determine screen shape
     * @param view
     */
    public static void initShapeWear(View view){
        view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                if (insets.isRound()) {
                    shape = SHAPE_ROUND;
                } else {
                    shape = SHAPE_RECTANGLE;
                }
                if(onShapeChangeListener != null){
                    try {
                        onShapeChangeListener.shapeDetected(isRound());
                    } catch (Exception e) {
                        //Do nothing as we just set shape so it is impossible to throw it here
                    }
                }
                return insets;
            }
        });
    }

    /**
     * Initialized at any moment of app life cycle to determine screen shape
     * @param activity
     */
    public static void initShapeWear(Activity activity){
        initShapeWear(activity.getWindow().getDecorView().findViewById(android.R.id.content));
    }

    /**
     * Method used to get most common (for now) parameter, is screen is round or not. Will throw an Exception is it is not detected.
     * @return boolean is screen is round or not
     * @throws Exception
     */
    public static boolean isRound() throws Exception {
        if(shape == null || shape.equals(SHAPE_UNSURE)){
            throw new Exception("ShapeWear still doesn't have correct screen shape at this point, subscribe to OnShapeChangeListener or call this method later on. Also you can call getShape() to get String representation, will return SHAPE_UNSURE if not specified.");
        } else if (shape.equals(SHAPE_ROUND)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Safe-proof method, but will return SHAPE_UNSURE instead of throwing Exception.
     * @return String name of screen type
     */
    public static String getShape(){
        return shape;
    }

    public static OnShapeChangeListener getOnShapeChangeListener() {
        return onShapeChangeListener;
    }

    public static void setOnShapeChangeListener(OnShapeChangeListener onShapeChangeListener) {
        ShapeWear.onShapeChangeListener = onShapeChangeListener;
        if(!getShape().equals(SHAPE_UNSURE) && ShapeWear.onShapeChangeListener != null){
            try {
                ShapeWear.onShapeChangeListener.shapeDetected(isRound());
            } catch (Exception e) {
                //We checked that with SHAPE_UNSURE already
            }
        }
    }

    public interface OnShapeChangeListener {
        void shapeDetected(boolean isRound);
    }
}
