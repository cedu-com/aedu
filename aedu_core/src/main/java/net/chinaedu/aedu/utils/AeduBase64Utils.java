package net.chinaedu.aedu.utils;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

public class AeduBase64Utils {
	 
	private static final byte[] base64_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes();	
	
    private static boolean is_base64( byte code )
    {       	
    	for( byte b : base64_chars )
    	{
    		if( b == code )
    			return true;
    	}
		return false;
    }

    private static byte getPos( byte code )
    {       	
    	int pos = 0;
    	for( byte b : base64_chars )
    	{
    		if( b == code )
    			return (byte)pos;
    		pos++;
    	}
		return (byte)-1;
    }
    
    public static String base64_encode(byte[] bytes_to_encode, int in_len )
    {
    	String ret = "";
    	int i = 0;
    	int j = 0;
    	byte[] char_array_3 = new byte[3];
    	byte[] char_array_4 = new byte[4];
	    int pos = 0;
	    
		  while (in_len-->0) {			  
			System.arraycopy( bytes_to_encode, pos++ , char_array_3 , i++, 1 );
		    if (i == 3) {
		    	char_array_4[0] = (byte) ((char_array_3[0] & 0xfc) >> 2);
		        char_array_4[1] = (byte) (((char_array_3[0] & 0x03) << 4) + ((char_array_3[1] & 0xf0) >> 4));
		        char_array_4[2] = (byte) (((char_array_3[1] & 0x0f) << 2) + ((char_array_3[2] & 0xc0) >> 6));
		        char_array_4[3] = (byte) ( char_array_3[2] & 0x3f);
	
		      for(i = 0; (i <4) ; i++)
		      {
				try {
					ret += new String( base64_chars , char_array_4[i] , 1, "UTF-8" );
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		      }
		      i = 0;
		    }
		  }

    	  if (i>0)
    	  {
    	    for(j = i; j < 3; j++)
    	      char_array_3[j] = '\0';

    	    char_array_4[0] = (byte) ((char_array_3[0] & 0xfc) >> 2);
	        char_array_4[1] = (byte) (((char_array_3[0] & 0x03) << 4) + ((char_array_3[1] & 0xf0) >> 4));
	        char_array_4[2] = (byte) (((char_array_3[1] & 0x0f) << 2) + ((char_array_3[2] & 0xc0) >> 6));
	        char_array_4[3] = (byte) ( char_array_3[2] & 0x3f);

    	    for (j = 0; (j < i + 1); j++)
    	    {
    	    	try {
					ret += new String( base64_chars , char_array_4[j] , 1, "UTF-8" );
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
    	    }
    	    
    	    while((i++ < 3))
    	      ret += '=';

    	  }

    	  return ret;

    }
    
    public static byte[] base64_decode( byte[] encoded_string ) 
    {    	  
    	  int in_len = encoded_string.length;
    	  int a = encoded_string[in_len-1]=='='?1:0;
    	  int b = encoded_string[in_len-2]=='='?1:0;
    	  byte[] ret = new byte[ in_len/4*3 - a - b ];
    	  int i = 0;
    	  int j = 0;
    	  int in_ = 0;
    	  byte[] char_array_4 = new byte[4];
    	  byte[] char_array_3 = new byte[3];
    	  int pos = 0;
    	  
    	  while (in_len-->0 && ( encoded_string[in_] != '-') && is_base64(encoded_string[in_])) {
    	    char_array_4[i++] = encoded_string[in_]; 
    	    in_++;
    	    if (i ==4) {
    	      for (i = 0; i <4; i++)
    	      { 
    	    	  char_array_4[i] = getPos( char_array_4[i] ) ;
    	      }

    	       char_array_3[0] = (byte)( (char_array_4[0] << 2) + ((char_array_4[1] & 0x30) >> 4))  ;
    	       char_array_3[1] = (byte)( ((char_array_4[1] & 0xf) << 4) + ((char_array_4[2] & 0x3c) >> 2) ) ;
    	       char_array_3[2] = (byte)( ((char_array_4[2] & 0x3) << 6) + char_array_4[3] ) ;

    	      for (i = 0; (i < 3); i++)
    	        ret[pos++] = (byte)( (char_array_3[i] ) & 0xFF );
    	      i = 0;
    	    }
    	  }

    	  if (i>0) {
    	    for (j = i; j <4; j++)
    	      char_array_4[j] = 0;

    	    for (j = 0; j <4; j++)
    	      char_array_4[j] = getPos(char_array_4[j]);

    	    char_array_3[0] = (byte)( (char_array_4[0] << 2) + ((char_array_4[1] & 0x30) >> 4));
 	        char_array_3[1] = (byte)( ((char_array_4[1] & 0xf) << 4) + ((char_array_4[2] & 0x3c) >> 2) );
 	        char_array_3[2] = (byte)( ((char_array_4[2] & 0x3) << 6) + char_array_4[3] );

    	    for (j = 0; (j < i - 1); j++) 
    	    	ret[pos++] = (byte) (char_array_3[j] & 0xFF) ;
    	  }

    	  return ret;
    	}

	/**
	 * encodeBase64File:(将文件转成base64 字符串). <br/>
	 * @author xujianbao
	 * @param path 文件路径
	 * @return
	 * @throws Exception
	 */
	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int)file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return Base64.encodeToString(buffer, Base64.DEFAULT);
	}

	/**
	 * decoderBase64File:(将base64字符解码保存文件). <br/>
	 * @author xujianbao
	 * @param base64Code 编码后的字串
	 * @param savePath  文件保存路径
	 * @throws Exception
	 */
	public static void decoderBase64File(String base64Code, String savePath) throws Exception {
		byte[] buffer = Base64.decode(base64Code, Base64.DEFAULT);
		FileOutputStream out = new FileOutputStream(savePath);
		out.write(buffer);
		out.close();

	}

	/**
	 * <p>将base64字符保存文本文件</p>
	 * @author xujianbao
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */
	public static void saveToFile(String base64Code, String targetPath) throws Exception {
		byte[] buffer = base64Code.getBytes();
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}
    
    //---------------------------------------------------------------------------------------------------------------------------------
    
    private static final byte[] base64_chars_ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@!".getBytes();	
    
    public static void main(String[] args)
    {
//    	System.out.println( "encode=" + BASE64.base64_encode( "100".getBytes() , "100".getBytes().length ) );
//    	System.out.println( "encode=" + BASE64.base64_encode( "1000".getBytes() , "1000".getBytes().length ) );
//    	System.out.println( "encode=" + BASE64.base64_encode( "10000".getBytes() , "10000".getBytes().length ) );
//    	System.out.println( "encode=" + BASE64.base64_encode( "100000".getBytes() , "100000".getBytes().length ) );
//    	
//    	System.out.println( "encode=" + BASE64.base64_encode( 
//    			"��격".getBytes( Charset.forName("UTF-8")) , 
//    			"��격".getBytes( Charset.forName("UTF-8") ).length ) );
//    	
//    	System.out.println( "encode=" + BASE64.base64_encode( 
//    			"��ɿ�".getBytes( Charset.forName("UTF-8")) , 
//    			"��ɿ�".getBytes( Charset.forName("UTF-8") ).length ) );
    	
    	
    	test("MTAw");
    	test("5p2O5Y+v5Y+v");
    	
    }
    
    private static void test( String code )
    {
    	String str = "";
		try {
			str = new String( code.getBytes() , "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			str = new String( AeduBase64Utils.base64_decode(str.getBytes()) , "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println( "decode=" + str );
    }
    
}

