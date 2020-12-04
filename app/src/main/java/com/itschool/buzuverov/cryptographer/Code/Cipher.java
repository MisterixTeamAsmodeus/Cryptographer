package com.itschool.buzuverov.cryptographer.Code;

public interface Cipher {
    void setParamsEncode(Object key);
    void setParamsDecode(Object key);
    String encode(String input);
    String decode(String input);
}
