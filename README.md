#ShapeWear - simple detecting shape on Android Wear
Less pain when you try to determine if screen is round or rectangular one on Android Wear smartwatch. Also additional shapes can be introduced in future. It also allows for simple detecting screen size. In both cases you can set up listeners for that, or via `getShape()`, `isRound()` etc.

###How to use it?
Just copy class [ShapeWear.java](https://github.com/tajchert/ShapeWear/blob/master/shapewear/src/main/java/pl/tajchert/shapewear/ShapeWear.java) to your project, and remember to initialized it on app start, such as:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    //...
    ShapeWear.initShapeWear(this);
}
```

specify listeners such as:

```java
ShapeWear.setOnShapeChangeListener(new ShapeWear.OnShapeChangeListener() {
    @Override
    public void shapeDetected(boolean isRound) {
        //Do your stuff here
    }
});
ShapeWear.setOnSizeChangeListener(new ShapeWear.OnSizeChangeListener() {
    @Override
    public void sizeDetected(int widthPx, int heightPx) {
        //Do your stuff here
    }
});
```

###Why not a library?
Maybe in the future, for now it is a perfect small class that get its jobs done.
