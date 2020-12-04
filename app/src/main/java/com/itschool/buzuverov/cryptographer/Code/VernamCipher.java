package com.itschool.buzuverov.cryptographer.Code;

public class VernamCipher implements Cipher {
    private String params = "";

    @Override
    public void setParamsEncode(Object key) {
        params = String.valueOf(key);
    }

    @Override
    public void setParamsDecode(Object key) {
        params = String.valueOf(key);
    }

    @Override
    public String encode(String input) {
        String out = new String();
        Binary binary = new Binary();
        for (int i = 0; i < input.length(); i++){
            int f = Integer.parseInt(binary.encode(String.valueOf(input.charAt(i))).replaceAll(" ",""), 2);
            int t = Integer.parseInt(binary.encode(String.valueOf(params.charAt(i % params.length()))).replaceAll(" ",""), 2);
            out += Integer.toBinaryString(f ^ t) + " ";
        }
        return out;
    }

    @Override
    public String decode(String input) {
        String out = new String();
        String[] in = input.split(" ");
        Binary binary = new Binary();
        for (int i = 0; i < in.length; i++){
            int f = Integer.parseInt(in[i], 2);
            int t = Integer.parseInt(binary.encode(String.valueOf(params.charAt(i % params.length()))).replaceAll(" ",""), 2);
            System.out.println(f + " " + (f ^ t) + " " + t);
            out += (char)(f ^ t);
        }
        return out;
    }
}
