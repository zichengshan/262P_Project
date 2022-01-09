package com.zicheng;
import java.io.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Test {
    //recursive function appends string to a json object, input is type object because if call from object is made then no modification is done
    private static Object append_string_to_key(Object obj, String str){
        if (obj instanceof JSONArray){
            JSONArray ArrayObj = (JSONArray)obj;
            JSONArray newArrayObj = new JSONArray();
            Iterator<Object> itobj = ArrayObj.iterator();
            int index = 0; // just for printing index of the array
            while (itobj.hasNext()){
                Object subObject = itobj.next();
                System.out.println("["+index+"]");
                Object newsubObject = append_string_to_key(subObject, str);
                newArrayObj.put(newsubObject); // put new Sub object. hopefully this replaces the previous object
                index +=1;
            }
            return newArrayObj;

        }else if (obj instanceof JSONObject){
            JSONObject json = (JSONObject)obj;
            Iterator<String> it = json.keys(); // set of keys which can be modified
            List<String> list = new ArrayList<String>();
            it.forEachRemaining(list::add); // all elements to list instead of iterating to make concurrent modification safe
            for(String key: list){
                System.out.println("["+key+"]");
                Object subObject = json.get(key); // the value pointed to by this key
                Object newObject = append_string_to_key(subObject, str);
                json.remove(key); // remove this key from json object
                String newKey = "swe262_"+key;
                json.put(newKey, newObject); // add new key,value pair
            }
            return (Object)json;
        }
        else{
            return obj;
        }
    }

    public static void main(String[] args) {
        File file = new File("books.xml"); // the xml file
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder file_string = new StringBuilder("");
            String line;
            while ((line = br.readLine()) != null) {
                file_string.append(line);
                file_string.append(System.lineSeparator());
            }
            String xmlText = file_string.toString();
            br.close();

            // convert XML to JSON
            JSONObject json = XML.toJSONObject(xmlText); // got the json object
            append_string_to_key(json, "swe262_");
            File outFile = new File("output4.json"); // default output file
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
            bw.write(json.toString(4)); // write in pretty form
            bw.flush();
            bw.close();
        }catch(IOException ioex){
            ioex.printStackTrace();
        }
    }

}

//
//import org.json.*;
//
//public class Test {
//    public static void main(String[] args) {
//        JSONArray list = new JSONArray();
//        list.put("name");
//        list.put("Robert");
//
//        System.out.println("XML from a JSONArray: ");
//        String xml = JSONML.toString(list);
//
//        JSONObject object = JSONML.toJSONObject(xml);
//        System.out.println(object);
//    }
//}

