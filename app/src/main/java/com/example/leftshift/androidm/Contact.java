package com.example.leftshift.androidm;

/**
 * Created by Akshay on 09-10-2015.
 */
public class Contact {
    public static final Contact[] CONTACTS = {
            new Contact("Tereasa"),
            new Contact("Chang"),
            new Contact("Kory"),
            new Contact("Clare"),
            new Contact("Landon"),
            new Contact("Kyle"),
            new Contact("Deana"),
            new Contact("Daria"),
            new Contact("Melisa"),
            new Contact("Sammie"),
    };
    public static final String ID = "contact_id";
    public static final int INVALID_ID = -1;
    private final String mName;
    public Contact(String name) {
        mName = name;
    }

    public static Contact byId(int id) {
        return CONTACTS[id];
    }
    public String getName() {
        return mName;
    }
    public int getIcon() {
        return R.mipmap.ic_launcher;
    }
}
