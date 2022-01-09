package com.zicheng;

import org.json.*;

import java.io.*;

/*
    JSON Pointer defines a String that can be used for accessing values on a JSON document.
    It can be related to what XPath does for an XML document.
    Through the use of JSON Pointer, we can fetch data from and alter a JSON file.
    Reference: https://github.com/susantamoh84/tutorials/blob/e208e0db03f371e7ec75065b2ab51e9c9b9eab0b/json/src/main/java/com/baeldung/jsonpointer/JsonPointerCrud.java
 */
public class ExtractSubObject_M1_2 {

    public static void main(String[] args)  throws IOException {

        // Convert XML to JSONObject
        JSONObject jObject = readFile(args[0]);

        // JSONPointer
        JSONPointer pointer = new JSONPointer(args[1]);

        // object is the subject of querying
        Object object = pointer.queryFrom(jObject);
        // Call the method to write the sub-object to a JSON file
        writeFile("file2.json", object);
        System.out.println("success");
    }

    /**
     * Read specific xml file and return the JSONObject
     * @param fileName
     * @return JSONObject
     * @throws IOException
     * @Reference: https://www.tutorialspoint.com/org_json/org_json_quick_guide.htm
     */
    private static JSONObject readFile(String fileName) throws IOException {
        // Use the StringBuild to concat strings
        StringBuilder builder = new StringBuilder();
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String strLine = reader.readLine();
        while(strLine != null){
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
}