package com.example.astroweathercz1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private IFragment2AListener listener;
    private EditText editText;
    private Button buttonOK;

    public interface IFragment2AListener {
        void onInput2Sent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2_fragment, container, false);
        editText = v.findViewById(R.id.editTextFragment2);
        buttonOK = v.findViewById(R.id.buttonFragment2);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editText.getText();
                listener.onInput2Sent(input);
            }
        });

        return v;
    }

    public void updateEditText(CharSequence newText) {
        editText.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        jeżeli nasza aktywność implementuje IFragmentAListener
        if (context instanceof IFragment2AListener) {
            listener = (IFragment2AListener) context;
        } else {
            throw new RuntimeException(context.toString() + " musisz zaimplementowac IFragment2AListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
