package com.assl.appassessment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class SignUpFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private MainActivityViewModel mainActivityViewModel;
    private ConstraintLayout rootLayout;
    private Button signUpButton;
    private TextInputLayout name, address, email, phoneNumber, password, location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        addFocusChangeListener();
        addTextWatchers();
        setOnClickListeners();
        MutableLiveData<String> locationData = Navigation.findNavController(view).getCurrentBackStackEntry().getSavedStateHandle().getLiveData("location");
        locationData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mainActivityViewModel.getUserDetails().setLocation(s);
            }
        });
    }

    private void enableSignInButton() {
        if(name.getEditText().getText().toString().trim().length()>0 && name.getError()==null &&
                address.getEditText().getText().toString().trim().length()>0 && address.getError()==null &&
                email.getEditText().getText().toString().trim().length()>0 && email.getError()==null &&
                phoneNumber.getEditText().getText().toString().trim().length()>0 && phoneNumber.getError()==null &&
                password.getEditText().getText().toString().trim().length()>0 && password.getError()==null &&
                location.getEditText().getText().toString().trim().length()>0 && location.getError()==null)
            signUpButton.setEnabled(true);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String currentString = charSequence.toString().trim();
        if (name.hasFocus()) {
            mainActivityViewModel.getUserDetails().setName(currentString);
            setErrorMessage(name, currentString.length()<4, "Should contains at least 4 letters");
        } else if (address.hasFocus()) {
            mainActivityViewModel.getUserDetails().setAddress(currentString);
            setErrorMessage(address, currentString.length()<10, "Should contains at least 10 letters");
        } else if (email.hasFocus()) {
            mainActivityViewModel.getUserDetails().setEmail(currentString);
            setErrorMessage(email, !Patterns.EMAIL_ADDRESS.matcher(currentString).matches(), "Invalid email");
        } else if (phoneNumber.hasFocus()){
            mainActivityViewModel.getUserDetails().setPhoneNumber(currentString);
            setErrorMessage(phoneNumber, currentString.length()<13 || !currentString.contains("+") || currentString.matches("[#,*.-;()/]"), "+91 6666644444");
        }else if(password.hasFocus()) {
            mainActivityViewModel.getUserDetails().setPassword(currentString);
            setErrorMessage(password, !checkPassword(currentString), "Must contain: One Capital letter\nOne Small letter\nOne Number\nMin length 8\nMax Length 15");
        }else if(location.hasFocus()) {
            mainActivityViewModel.getUserDetails().setLocation(currentString);
            setErrorMessage(location, currentString.length()==0, "*Required");
        }
    }

    private boolean checkPassword(String currentString) {
        boolean containsLowerCaseAlphabet = false, containsUpperCaseAlphabet = false, containsNumber = false;
        for(int i=0;i<currentString.length();i++){
            char currentChar = currentString.charAt(i);
            if(Character.isLowerCase(currentChar))
                containsLowerCaseAlphabet = true;
            else if (Character.isUpperCase(currentChar))
                containsUpperCaseAlphabet = true;
            else if (Character.isDigit(currentChar))
                containsNumber = true;
        }
        if(containsLowerCaseAlphabet && containsUpperCaseAlphabet && containsNumber && currentString.length()<16 && currentString.length()>7)
            return true;
        return false;
    }

    @Override
    public void afterTextChanged(Editable editable) {}

    private void setErrorMessage(TextInputLayout view, boolean condition, String messageOnCondition) {
        if(condition) {
            view.setError(messageOnCondition);
            signUpButton.setEnabled(false);
        } else
            view.setError(null);
    }

    private void addTextWatchers() {
        Objects.requireNonNull(name.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(address.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(email.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(phoneNumber.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(password.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(location.getEditText()).addTextChangedListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view==signUpButton){
            //TODO: take data make json out of it, store it in room
            try {
                JSONObject obj = new JSONObject();
                obj.put("name", mainActivityViewModel.getUserDetails().getName());
                obj.put("address", mainActivityViewModel.getUserDetails().getAddress());
                obj.put("email", mainActivityViewModel.getUserDetails().getEmail());
                obj.put("phoneNumber", mainActivityViewModel.getUserDetails().getPhoneNumber());
                obj.put("password", mainActivityViewModel.getUserDetails().getPassword());
                obj.put("location",mainActivityViewModel.getUserDetails().getLocation());
                mainActivityViewModel.storeUserDetails(obj, requireContext());
                Navigation.findNavController(view).navigate(R.id.allUsersListFragment);
                mainActivityViewModel.getUserDetails().clearAllFields();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else
            Navigation.findNavController(view).navigate(R.id.mapFragment);
    }


    private void setOnClickListeners() {
        Objects.requireNonNull(location.getEditText()).setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    private void populateViews() {
        if(mainActivityViewModel.getUserDetails().getName().length()>0)
            name.getEditText().setText(mainActivityViewModel.getUserDetails().getName());
        if(mainActivityViewModel.getUserDetails().getAddress().length()>0)
            address.getEditText().setText(mainActivityViewModel.getUserDetails().getAddress());
        if(mainActivityViewModel.getUserDetails().getEmail().length()>0)
            email.getEditText().setText(mainActivityViewModel.getUserDetails().getEmail());
        if(mainActivityViewModel.getUserDetails().getPhoneNumber().length()>0)
            phoneNumber.getEditText().setText(mainActivityViewModel.getUserDetails().getPhoneNumber());
        if(mainActivityViewModel.getUserDetails().getPassword().length()>0)
            password.getEditText().setText(mainActivityViewModel.getUserDetails().getPassword());
        if(mainActivityViewModel.getUserDetails().getLocation().length()>0)
            location.getEditText().setText(mainActivityViewModel.getUserDetails().getLocation());
    }


    private void initViews(View view) {
        rootLayout = view.findViewById(R.id.sign_up_main_layout);
        name = view.findViewById(R.id.name);
        address = view.findViewById(R.id.address);
        email = view.findViewById(R.id.email);
        phoneNumber = view.findViewById(R.id.phone_number);
        password = view.findViewById(R.id.password);
        location = view.findViewById(R.id.location);
        signUpButton = view.findViewById(R.id.sign_up_button);
    }

    private void addFocusChangeListener() {
        rootLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b && !(view instanceof EditText))
                    hideKeyboard(view);
            }
        });
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm!=null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        populateViews();
        enableSignInButton();
    }
}