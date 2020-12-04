package com.itschool.buzuverov.cryptographer.Code;

public class CaesarShifts implements Cipher {

    private int params = 0;

    @Override
    public void setParamsEncode(Object key) {
        try {
            params = Integer.parseInt(String.valueOf(key));
        } catch (Exception e){
            params = 0;
        }

    }

    @Override
    public void setParamsDecode(Object key) {
        try {
            params = Integer.parseInt(String.valueOf(key)) * -1;
        } catch (Exception e){
            params = 0;
        }
    }

    @Override
    public String encode(String input) {
        StringBuilder out = new StringBuilder();
        char[] in = input.toCharArray();
        for (int i = 0; i < in.length; i++) {
            if (params % 25 == 0){
                out.append(input.charAt(i));
                continue;
            }
            if ((int)in[i] <= 1071 && (int)in[i] >= 1040){
                if ((int)in[i] + params % 31 < 1040){
                    char ch = (char)(1072 + (int)in[i] - 1040 + params % 31);
                    out.append(ch);
                } else if ((int)in[i] + params % 31 > 1071){
                    char ch = (char)(1039 + (int)in[i] - 1071 + params % 31);
                    out.append(ch);
                } else {
                    char ch = (char)((int)in[i] + params % 31);
                    out.append(ch);
                }
            } else

            if ((int)in[i] <= 1103 && (int)in[i] >= 1072){
                if ((int)in[i] + params % 31 < 1072){
                    char ch = (char)(1104 + (int)in[i] - 1072 + params % 31);
                    out.append(ch);
                } else if ((int)in[i] + params % 31 > 1103){
                    char ch = (char)(1071 + (int)in[i] - 1103 + params % 31);
                    out.append(ch);
                } else {
                    char ch = (char)((int)in[i] + params % 31);
                    out.append(ch);
                }
            } else

            if ((int)in[i] <= 90 && (int)in[i] >= 65){
                if ((int)in[i] + params % 25 < 65){
                    char ch = (char)(91 + (int)in[i] - 65 + params % 25);
                    out.append(ch);
                } else if ((int)in[i] + params % 25 > 90){
                    char ch = (char)(64 + (int)in[i] - 90 + params % 25);
                    out.append(ch);
                } else {
                    char ch = (char)((int)in[i] + params % 25);
                    out.append(ch);
                }
            } else

            if ((int)in[i] <= 122 && (int)in[i] >= 97){
                if ((int)in[i] + params % 25 < 97){
                    char ch = (char)(123 + (int)in[i] - 97 + params % 25);
                    out.append(ch);
                } else if ((int)in[i] + params % 25 > 122){
                    char ch = (char)(97 + (int)in[i] - 122 + params % 25);
                    out.append(ch);
                } else {
                    char ch = (char)((int)in[i] + params % 25);
                    out.append(ch);
                }
            } else {
                out.append(input.charAt(i));
                continue;
            }
        }
        return out.toString();
    }

    @Override
    public String decode(String input) {
        return encode(input);
    }
}
