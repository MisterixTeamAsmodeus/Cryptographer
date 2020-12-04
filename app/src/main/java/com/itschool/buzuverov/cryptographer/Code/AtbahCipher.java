package com.itschool.buzuverov.cryptographer.Code;

public class AtbahCipher implements Cipher {

    @Override
    public void setParamsEncode(Object key) {
    }

    @Override
    public void setParamsDecode(Object key) {
    }

    @Override
    public String encode(String input) {
        String out = new String();
        char[] in = input.toCharArray();
        for (int i = 0; i < in.length; i++) {
            if ((int)in[i] <= 1071 && (int)in[i] >= 1040){
                char ch = (char)(1071 - ((int)in[i] - 1040));
                out += ch;
            } else

            if ((int)in[i] <= 1103 && (int)in[i] >= 1072){
                char ch = (char)(1103 - ((int)in[i] - 1072));
                out += ch;
            } else

            if ((int)in[i] <= 90 && (int)in[i] >= 65){
                char ch = (char)(90 - ((int)in[i] - 65));
                out += ch;
            } else

            if ((int)in[i] <= 122 && (int)in[i] >= 97){
                char ch = (char)(122 - ((int)in[i] - 97));
                out += ch;
                } else{
                out += in[i];
            }
            }
        return out;
    }

    @Override
    public String decode(String input) {
        return encode(input);
    }
}
