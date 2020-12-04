package com.itschool.buzuverov.cryptographer.Code;

public class VigenereСipher implements Cipher{
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
        CaesarShifts shifts = new CaesarShifts();

        for (int i = 0; i < input.length(); i++){
            shifts.setParamsEncode((int)params.charAt(i % params.length()));
            out += shifts.encode(String.valueOf(input.charAt(i)));
        }

        return out;
    }

    @Override
    public String decode(String input) {
        String out = new String();
        CaesarShifts shifts = new CaesarShifts();

        for (int i = 0; i < input.length(); i++){
            shifts.setParamsDecode((int)(params.charAt(i % params.length())));
            out += shifts.encode(String.valueOf(input.charAt(i)));
        }
        return out;
    }
}
