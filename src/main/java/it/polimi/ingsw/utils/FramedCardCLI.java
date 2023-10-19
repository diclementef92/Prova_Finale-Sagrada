package it.polimi.ingsw.utils;

import java.util.ArrayList;

public class FramedCardCLI {

    /***
     *
     * @param card get the card description, considering the structure as "Name: ...", "Description: ...", "Color: ...", etc. Note that the single words in every field must be <= Utils.CARD_FRAME_DIMENSION
     * @return the formatted string to print
     */
    public static String StringtoFramedCard(String card){
        String name = "";
        String description = "";
        String token = "";
        String color = "";
        StringBuilder framed = new StringBuilder();
        for(String line: card.split("\\r?\\n")){
            if(line.contains("Name: "))
                name = line;
            else if(line.contains("Color: "))
                color = line;
            else if(line.contains("Description: "))
                description = line;
            else if(line.contains("Token: "))
                token = line;
        }
        framed.append(startFrame());

        StringBuilder temp = new StringBuilder();
        for(int i = name.length(); i < Utils.CARD_FRAME_DIMENSION; temp.append(" "), i++);
        temp.append("| |\n");
        framed.append("| |" + name + temp);

        temp = new StringBuilder();
        for(int i = color.length(); i < Utils.CARD_FRAME_DIMENSION; temp.append(" "), i++);
        temp.append("| |\n");
        framed.append("| |" + color + temp);

        temp = new StringBuilder();
        for(int i = token.length(); i < Utils.CARD_FRAME_DIMENSION; temp.append(" "), i++);
        temp.append("| |\n");
        framed.append("| |" + token + temp.toString());
//

        ArrayList<String> splitDescr = new ArrayList<>();
        for(String str:description.split(" "))
            splitDescr.add(str);

        while(!splitDescr.isEmpty()){
            temp = new StringBuilder();
            framed.append("| |");
            int count = Utils.CARD_FRAME_DIMENSION;
            for(int j = 0; j<splitDescr.size() && count >= splitDescr.get(j).length(); j++) {
                framed.append(splitDescr.get(j) + " ");
                count -= splitDescr.get(j).length() + 1;
                splitDescr.remove(j);
            }
            for(; count != 0 ; temp.append(" "), count--);
            temp.append("| |\n");
            framed.append(temp);
        }
        framed.append(endFrame());
        return framed.toString();
    }

    private static String startFrame(){
        return " ____________________________ \n|  _________________________ |\n";
    }

    private static String endFrame(){
        return "| |________________________| |\n| __________________________ |\n";
    }

    //per testarne la correttezza
    public static void main (String[] args){
        String test =  "------\n" +
                "Name: " + "tom" + "\n" +
                "Number: " + "12" + "\n" +
                "Color: " + "blue" + "\n" +
                "Description: " + "231ew dmc oev fnwww www wwwww www wwwow eoew ekwq nkq dnlkw qn" + "\n" +
                "Token: " + "3" + "\n" +
                "------\n";
        System.out.println(StringtoFramedCard(test));
    }
}
