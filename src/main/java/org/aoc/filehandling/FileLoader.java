package org.aoc.filehandling;

import java.io.*;
import java.util.ArrayList;

public class FileLoader {

    public static ArrayList<String> loader (String filename){
        ArrayList<String> out = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;

        try{
            File file = new File(filename);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                out.add(line);
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                if(br != null){
                    br.close();
                }
                if(fr != null){
                    fr.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


        return out;
    }
}
