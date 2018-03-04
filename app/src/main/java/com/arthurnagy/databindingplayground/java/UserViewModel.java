/*
 * Copyright (c) 2018 Halcyon Mobile
 * http://www.halcyonmobile.com
 * All rights reserved.
 */

package com.arthurnagy.databindingplayground.java;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.arthurnagy.databindingplayground.BR;
import com.arthurnagy.databindingplayground.R;
import com.arthurnagy.databindingplayground.StringProvider;

public class UserViewModel extends BaseObservable {

    private String firstName = "";
    private String lastName = "";
    private final StringProvider stringProvider;

    public UserViewModel(StringProvider stringProvider) {
        this.stringProvider = stringProvider;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    @Bindable({"firstName", "lastName"})
    public String getDisplayName() {
        return stringProvider.getString(R.string.display_name, firstName, lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

}
