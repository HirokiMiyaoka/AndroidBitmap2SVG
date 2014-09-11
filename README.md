AndroidBitmap2SVG
=================

This prigram convert Android Bitmap to SVG.

このプログラムはAndroidのBitmapをSVGに変換するものです。

    File output = ...;
    Bitmap input = ...;
    
    if ( Bitmap2SVG.convert( output, input ) )
    {
        // Success
    } else
    {
        // Failure
    }
