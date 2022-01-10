package com.zicheng;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddPrefix_M1_4 {
    public static void main(String[] args) throws IOException {
        // Convert XML to JSON
        JSONObject object = readFile("books.xml");

        Object newObject = addPrefix(object, "swe262_");
        // Write JSON object to file
        writeFile("file4.json", newObject);
    }

    /**
     * Read specific xml file and save the info as a string and return
     * reference: https://www.tutorialspoint.com/org_json/org_json_quick_guide.htm
     * @param fileName
     * @return
     * @throws IOException
     */
    private static JSONObject readFile(String fileName) throws IOException {
        File file = new File(fileName);
        StringBuilder builder = new StringBuilder("");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String strLine = reader.readLine();
        while(strLine!= null){
            // The trim() method removes whitespace from both sides of a string
            builder.append(strLine.trim() + " ");
            strLine = reader.readLine();
        }
        reader.close();
        return XML.toJSONObject(builder.toString());
    }

    /**
     * Write JSON object to file
     * @param fileName
     * @param object
     * @throws IOException
     */
    private static void writeFile(String fileName, Object object) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
        // Case1: object is JSONObject
        // Case2: object is JSONArray
        // Case3: single value
        if (object instanceof JSONObject){
            JSONObject jObject = (JSONObject)object;
            output.write(jObject.toString(4));
        }else if (object instanceof JSONArray){
            JSONArray jArray = (JSONArray)object;
            output.write(jArray.toString(4));
        }else{
            output.write(object.toString());
        }
        output.close();
    }

    /**
     * Add prefix to each key
     * @param object
     * @param str
     * @return
     */
    private static Object addPrefix(Object object, String str){
        if(object instanceof JSONObject){
            JSONObject jsonObject = (JSONObject) object;

            // If it is a JSONObject, save all the keys into array
            List<String> list = new ArrayList<>();
            for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
                String key = it.next();
                list.add(key);
            }
            // iterate the keys
            for(String key : list){
            // Get the current object and recall teh addprefix() function to make its inner keys get the prefix added
                Object subObject = jsonObject.get(key);
                Object newObject = addPrefix(subObject, str);
            // Remove the old object and put the new object
                jsonObject.remove(key);
                String newKey = str + key;
                jsonObject.put(newKey, newObject);
            }
            return jsonObject;
        }
        else if(object instanceof JSONArray){
            JSONArray jsonArray = (JSONArray) object;
            // position is used to keep track of the Object we modified
            int position = 0;
            // Iterate the jsonArray and call the addprefix() function to make its inner object's keys get the prefix added
            for (Iterator<Object> it = jsonArray.iterator(); it.hasNext(); ) {
                Object subObject = it.next();
                Object newObject = addPrefix(subObject, str);
                
                // Replace the old JSONArray with the new one
                jsonArray.put(position,newObject);
                position++;
            }
            return jsonArray;
        }
        else{
            return object;
        }
    }
}