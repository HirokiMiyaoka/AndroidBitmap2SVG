package net.azulite.platz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import android.graphics.Bitmap;
import android.graphics.Color;

// GitHub : https://github.com/HirokiMiyaoka/AndroidBitmap2SVG
// Author : Hiroki
// Other  : Load test by Inkscape 0.48

public class Bitmap2SVG
{
	public static boolean convert( File output, Bitmap input )
	{
		Bitmap2SVG svg;
		try
		{
			svg = new Bitmap2SVG( new PrintWriter( new BufferedWriter( new FileWriter( output ) ) ), true );
		} catch (IOException e)
		{
			return false;
		}

		svg.printSVGHeader( input.getWidth(), input.getHeight() );
		svg.printSVGBody( input );
		svg.printSVGFooter();

		return true;
	}

	private boolean trc_del;
	private PrintWriter pw;
	private int w, h;

	private Bitmap2SVG( PrintWriter pw, boolean trc_delete )
	{
		this.pw = pw;
		trc_del = trc_delete;
	}
	private void printSVGHeader( int width, int height )
	{
		pw.println( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" );

		pw.print( "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"" );
		pw.print( width );
		pw.print( "\" height=\"" );
		pw.print( height );
		pw.println( "\">" );
	}
	private void printSVGFooter()
	{
		pw.println( "</svg>" );
		pw.close();
	}
	private void printSVGBody( Bitmap bmp )
	{
		int x, y, c, a;
		w = bmp.getWidth();
		h = bmp.getHeight();
		for ( y = 0 ; y < h ; ++y )
		{
			for ( x = 0 ; x < w ;++x )
			{
				c = bmp.getPixel( x, y );
				a = Color.alpha( c );
				if ( trc_del && a == 0 ){ continue; }
				printDot( x, y, rgb( c ), a );
			}
		}
	}
	private void printDot( int x, int y, String rgb, int a )
	{
		pw.print( "<rect width=\"1\" height=\"1\" x=\"" );
		pw.print( x );
		pw.print( "\" y=\"" );
		pw.print( y );
		pw.print( "\" " );
		pw.print( "style=\"fill:#" );
		pw.print( rgb );
		if ( a < 255 )
		{
			pw.print( ";fill-opacity:" );
			pw.print( a / 255.0 );
		}
		pw.println( ";\" />" );
	}
	private static String rgb( int c )
	{
		return String.format( "%02x%02x%02x", Color.red( c ), Color.green( c ), Color.blue( c ) );
	}
}
