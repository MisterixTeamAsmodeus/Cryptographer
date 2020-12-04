package com.itschool.buzuverov.cryptographer.Code;

public class Binary implements Cipher {

    @Override
    public void setParamsEncode(Object key) {

    }

    @Override
    public void setParamsDecode(Object key) {

    }

    public String encode(String input){
        String out = new String();
        for (int val : input.toCharArray())
        {
            out += Integer.toBinaryString(val) + " ";
        }
        return out;
    }

    public String decode(String input) throws NumberFormatException{
        String out = new String();
        String[] arr = input.split("\\s");
        for (String str : arr) {
            out += (char) Integer.parseInt(str, 2);
        }
        out = out.toLowerCase();
        return out;
    }
}
