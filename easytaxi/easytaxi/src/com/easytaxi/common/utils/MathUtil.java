package com.easytaxi.common.utils;
/**
 * Math util
 * @author renmian
 *
 */
public class MathUtil {
	/**
	 * 保留小数点后ppnum位
	 * @param infloat
	 * @param ppnum
	 * @return
	 */
	public static String get2Point(float infloat, int ppnum)
	{
		if( infloat == 0 )
			return "0.0";
		if( ppnum == 2 && infloat<0.01 )
			return "0.0";
		else if( ppnum == 4 && infloat<0.0001 )
			return "0.0";

		String outfloat = (new Float(infloat)).toString();
		if( outfloat.indexOf("E-4") != -1 ) {
			int theEnd = (new Integer(outfloat.substring(0,1))).intValue();
			if( (new Integer(outfloat.substring(2,3))).intValue() > 4 )
				theEnd = theEnd + 1;
			if( theEnd < 9 )
				outfloat = "0.000" + theEnd;
			else
				outfloat = "0.00" + theEnd;
		}

		int ee;
		String stree;
		int pp;
		float chfloat = (new Float(outfloat)).floatValue();

		if( outfloat.indexOf(".") != -1 ) {
			ee = outfloat.indexOf(".") + 1;
			stree = outfloat.substring(ee);
			if( stree.length()>ppnum ) {
				if( stree.length() == ppnum+1 )
					pp =  new Integer(stree.substring(ppnum)).intValue();
				else
					pp =  new Integer(stree.substring(ppnum, ppnum+1)).intValue();

				if( pp > 4 ) {
					if( ppnum == 2 )
						chfloat = chfloat + 0.01f;
					else if( ppnum == 4 ) {
						chfloat = chfloat + 0.0001f;
					}
				}

				outfloat = (new Float(chfloat)).toString();
				try {
				    outfloat = outfloat.substring(0, ee) + outfloat.substring(ee, ee+ppnum);
				} catch (Exception e) {
				    outfloat = outfloat.substring(0, ee) + outfloat.substring(ee);
	            }
			}
		}

		return outfloat;
	}

}
