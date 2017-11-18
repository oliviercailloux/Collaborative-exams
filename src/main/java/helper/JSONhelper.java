package helper;

import java.io.*;
import java.nio.file.Files;
import com.google.gson.*;


public class JSONhelper {
	
	//returns Json from object using GSON
    public static String ObjectToJson(Object object){
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setDateFormat("yyyy-mm-dd").create();
            return gson.toJson(object);
            
    		}catch(Exception e){
    			System.out.println("txjson objecttojson => \n"+e.getMessage()); return null;
    		}
       

    }
    //returns object from json using GSON
    public static Object JsonToObject(String json,Class<?> type){

        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setDateFormat("yyyy-mm-dd").create();
            Object object = gson.fromJson(json,type);
            return object;
            
        }catch(JsonParseException e){
			System.out.println("txjson jsontobject parse excep => \n"+e.getMessage());return null;
		}catch(Exception e){
    			System.out.println("txjson jsontobject => \n"+e.getMessage());return null;
    		}

    }
    
    public static void stringToFile( String text, String fileName )
    {
    try
    {
       File file = new File( fileName );

       // if file doesnt exists, then create it 
       if ( ! file.exists( ) )
       {
           file.createNewFile( );
       }

       FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
       BufferedWriter bw = new BufferedWriter( fw );
       bw.write( text );
       bw.close( );
       //System.out.println("Done writing to " + fileName); //For testing 
    }
    catch( IOException e )
    {
    System.out.println("Error: " + e);
    e.printStackTrace( );
    }
   } //End method stringToFile
    
    public static String fileToString(String filename) {
        File f = new File(filename);
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            return new String(bytes,"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
}

}
