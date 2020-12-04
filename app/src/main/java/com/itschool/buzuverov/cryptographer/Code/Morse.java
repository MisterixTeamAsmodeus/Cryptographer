package com.itschool.buzuverov.cryptographer.Code;

public class Morse implements Cipher {
    private char[] character;
    private String [] value;


    public Morse() {
        character = new char[]{ 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И',
                'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
                'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь',
                'Э', 'Ю', 'Я', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', '0', '.', ',', ':', ';', '\'', '\"', '-', '/', '_', '?', '!', ' '};

        value = new String[]{ ".–", "–...", ".––", "––.",
                "–..", ".", ".", "...–", "––..",
                "..", ".–––", "–.–", ".–..",
                "––", "–.", "–––", ".––.",
                ".–.", "...", "–", "..–",
                "..–.", "....", "–.–.",
                "–––.", "––––", "−−.−", "--.--",
                "−.−−", "−..−", "..−..",
                "..−−", ".−.−", ".−−−−",
                "..−−−", "...−−", "....−",
                ".....", "−....", "−−...",
                "−−−..", "−−−−.", "−−−−−",
                "......", ".-.-.-", "---...",
                "-.-.-.", ".----.", ".-..-.",
                "-....-", "-..-.", "..--.-",
                "..--..", "--..--", "-...-"};
    }

    @Override
    public void setParamsEncode(Object key) {

    }

    @Override
    public void setParamsDecode(Object key) {

    }

    public String encode(String input){
        String out = new String();
        input = input.toUpperCase();
        for(int i = 0; i < input.length(); i++){
            for (int i1 = 0; i1 < value.length; i1++){
                if(input.charAt(i) == character[i1]){
                    out += value[i1] + " ";
                    break;
                }
            }
        }
        return out;
    }

    public String decode(String input){
        String out = new String();
        String[] arr = input.split("\\s");
        for (String str : arr){
            for (int i1 = 0; i1 < value.length; i1++){
                if(str.equals(value[i1])){
                    out += character[i1];
                    break;
                }
            }
        }
        out = out.toLowerCase();
        return out;
    }
}
