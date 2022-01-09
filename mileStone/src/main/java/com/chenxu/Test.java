package com.chenxu;

import org.json.Cookie;
import org.json.JSONObject;

import java.io.*;

import org.json.XML;
public class Test{
//XMLToJson


    public static void main(String[] args) {
        File file = new File(args[0]);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder file_string = new StringBuilder("");
            while ((line = br.readLine())!= null){
                file_string.append(line);
                file_string.append(System.lineSeparator());
            }
            String xmlText = file_string.toString();
            br.close();
            // convert XML to JSON
            JSONObject json = XML.toJSONObject(xmlText);
            String jsonString = json.toString(4); //indent factor of 4 for pretty print
            //write json object to file1.json
            File fileFinal = new File("output1.json");
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileFinal));
            bw.write(jsonString);
            bw.flush();
            bw.close();
        }
        catch(FileNotFoundException f) {
            System.out.println("File not found");
        }
        catch(IOException ex) {
            System.out.println("can not read file");
        }
    }
}
