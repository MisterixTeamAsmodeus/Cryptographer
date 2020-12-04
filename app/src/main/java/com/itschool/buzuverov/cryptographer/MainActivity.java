package com.itschool.buzuverov.cryptographer;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.itschool.buzuverov.cryptographer.Code.*;

public class MainActivity extends AppCompatActivity{

    private boolean isEncrypt = false;
    private EditText inputText, inputParams;
    private TextView outputText;
    private Cipher code = new AtbahCipher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        inputText = findViewById(R.id.main_input_text);
        outputText = findViewById(R.id.main_output_text);
        inputParams = findViewById(R.id.main_input_params);
        SwitchMaterial switchMaterial = findViewById(R.id.main_switch);
        final TextView textInfo = findViewById(R.id.main_text_info);
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isEncrypt = b;
                textInfo.setText(isEncrypt ? "Шифровать" : "Расшифровать");
                setParams(inputParams.getText().toString());
                setEncodeText(inputText.getText().toString());
                inputText.setSelection(inputText.getText().toString().length());
            }
        });
        Spinner inputCode = findViewById(R.id.main_choose_code);
        inputCode.setAdapter(ArrayAdapter.createFromResource(this, R.array.Cipher, R.layout.custom_spinner));
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setEncodeText(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputParams.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setParams(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setCodes(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void copyInput(View view) {
        try {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", inputText.getText());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Скоприованно", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
        }
    }

    public void paste(View view) {
        try {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = clipboard.getPrimaryClip();
            inputText.setText(clipData.getItemAt(0).getText().toString());
            inputText.setSelection(inputText.length());
        } catch (Exception e){
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
        }
    }

    public void clear(View view) {
        inputText.setText("");
        outputText.setText("");
    }

    public void copyOutput(View view) {
        try {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", outputText.getText());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Скоприованно", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show();
        }
    }

    private void setParams(String str) {
        if (isEncrypt){
            code.setParamsEncode(str);
        } else {
            code.setParamsDecode(str);
        }
        setEncodeText(inputText.getText().toString());
        inputText.setSelection(inputText.getText().toString().length());
    }

    private void setEncodeText(String str) {
        if (!str.equals(str.replace('ё', 'е'))) {
            str = str.replace('ё', 'е');
            inputText.setText(str);
            inputText.setSelection(inputText.length());
        }
        if (isEncrypt){
            try {
                outputText.setText(code.encode(str));
            } catch (Exception e){
                outputText.setText(inputText.getText());
            }
        } else {
            try {
                outputText.setText(code.decode(str));
            } catch (Exception e){
                outputText.setText(inputText.getText());
            }
        }
    }

    private void setCodes(int i) {
        switch (i) {
            case 0:
                code = new AtbahCipher();
                inputParams.setVisibility(View.GONE);
                break;
            case 1:
                code = new VernamCipher();
                inputParams.setVisibility(View.VISIBLE);
                inputParams.setHint(getResources().getString(R.string.hint_input_params_vigener));
                break;
            case 2:
                code = new VigenereСipher();
                inputParams.setVisibility(View.VISIBLE);
                inputParams.setHint(getResources().getString(R.string.hint_input_params_vigener));
                break;
            case 3:
                code = new CaesarShifts();
                inputParams.setVisibility(View.VISIBLE);
                inputParams.setHint(getResources().getString(R.string.hint_input_params_caeser));
                break;
            case 4:
                code = new Morse();
                inputParams.setVisibility(View.GONE);
                break;
            case 5:
                code = new Binary();
                inputParams.setVisibility(View.GONE);
                break;

            case 6:
                code = new VernamCipher();
                inputParams.setVisibility(View.VISIBLE);
                inputParams.setHint(getResources().getString(R.string.hint_input_params_num));
                break;
        }
        setParams("");
        inputParams.setText("");
        setEncodeText(inputText.getText().toString());
        inputText.setSelection(inputText.getText().toString().length());
    }

    public void share(View view) {
        String text = view.getId() == R.id.main_input_share ? inputText.getText().toString() : outputText.getText().toString();
        if (text.trim().length() != 0){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        } else {
            Toast.makeText(this, "Нет текста", Toast.LENGTH_LONG).show();
        }
    }
}
