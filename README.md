AndroidBitmap2SVG
=================

This prigram convert Android Bitmap to SVG.

[Platz](https://play.google.com/store/apps/details?id=net.azulite.platz)(Pixel art editor/GooglePlay) use this program.

このプログラムはAndroidのBitmapをSVGに変換するものです。

[Platz](https://play.google.com/store/apps/details?id=net.azulite.platz)(ドット絵エディタ/GooglePlay)で使われています。

    File output = ...;
    Bitmap input = ...;
    
    if ( Bitmap2SVG.convert( output, input ) )
    {
        // Success
    } else
    {
        // Failure
    }
