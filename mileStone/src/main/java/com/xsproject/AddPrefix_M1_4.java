package com.xsproject;

import org.json.JSONObject;
import org.json.XML;

import java.io.*;

public class AddPrefix_M1_4 {
    public static void main(String[] args) {

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
     * @param jo
     * @throws IOException
     */
    private static void writeFile(String fileName, JSONObject jo) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        out.write(jo.toString(4));
        out.close();
        System.out.println("Success!");
    }


    private static void addPrefix(JSONObject object, String str){
        
    }
}
