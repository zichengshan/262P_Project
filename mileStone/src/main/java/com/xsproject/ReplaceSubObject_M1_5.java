package com.xsproject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import java.io.*;

/**
 * param example: ../../../resources/smallXml1.xml /catalog/book/0
 */
public class ReplaceSubObject_M1_5 {
    public static void main(String[] args) throws IOException {
//        //args example: books.xml /catalog/book/0
//
//        // Convert XML to JSON
//        JSONObject object = readFile(args[0]);
//
//        // Get the key path and save it into keys array
//        String keyPath = args[1];
//        String[] Key_space_included = keyPath.split("/");
//
//        // After the split, a space will be the first element because there is a "/" at the start of ketPath
//        String [] keys = new String[Key_space_included.length-1];
//        for(int i = 0; i < keys.length; i++)
//            keys[i] = Key_space_included[i+1];
//
//        // Construct our new object to replace the target object
//        JSONObject myObject = new JSONObject();
//        myObject.put("author", "Chenxv");
//        myObject.put("price", "15");
//        myObject.put("genre", "Science");
//
//        // Call the function to replace the target object with the new one
//        Object replaced_object = replace(keys, object, myObject);

        // Write JSON object to file
//        writeFile("file5.json", replaced_object);
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<contact>\n"+
                "  <nick>Crista </nick>\n"+
                "  <name>Crista Lopes</name>\n" +
                "  <address>\n" +
                "    <street>Ave of Nowhere</street>\n" +
                "    <zipcode>92614</zipcode>\n" +
                "  </address>\n" +
                "</contact>";

        JSONObject xmlObject =  XML.toJSONObject(xmlString);
        String[] keyPath = new String[]{"contact", "address", "street"};
//        JSONObject object = new JSONObject();
        JSONObject replacement = XML.toJSONObject("<street>Ave of the Arts</street>\n");
        System.out.println( replace(keyPath, xmlObject, replacement));
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
     * Replace the target object with the new one
     * @param keyPath
     * @param object
     * @param myObject
     * @return
     */
    private static Object replace(String[] keyPath, Object object, Object myObject){
        if(object instanceof JSONObject){
            // reach the target
            if(keyPath.length == 0)
                return myObject;
            else{
                JSONObject curObject = (JSONObject) object;
                // Get the outermost object
                Object tempObject = curObject.get(keyPath[0]);

                // if we have not reached the target, update the key path and recall the replace() function
                String[] newKeyPath = new String[keyPath.length-1];
                for(int i = 0; i < keyPath.length -1; i++){
                    newKeyPath[i] = keyPath[i+1];
                }
                Object newObject = replace(newKeyPath,tempObject,myObject);

                // update the curObject
                curObject.put(keyPath[0], newObject);
                return (Object) curObject;
            }
        }
        else if(object instanceof JSONArray){
            if(keyPath.length == 0)
                return myObject;
            else{
                JSONArray curArray = (JSONArray) object;

                // Get the specific key of the JSONArray
                Object tempObject = curArray.get(Integer.valueOf(keyPath[0]));

                // if we have not reached the target, update the key path and recall the replace() function
                String[] newKeyPath = new String[keyPath.length-1];
                for(int i = 0; i < keyPath.length - 1; i++){
                    newKeyPath[i] = keyPath[i+1];
                }
                Object newObject = replace(newKeyPath,tempObject,myObject);

                // update the curArray
                curArray.put(Integer.valueOf(keyPath[0]), newObject);
                return (Object) curArray;
            }
        }
        else{
            return (Object) myObject;
        }
    }
}
