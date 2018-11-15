package com.przyklady.web;

import com.przyklady.model.Uprawnienia;
import com.przyklady.model.UprawnieniaKontroler;
import com.przyklady.model.Uzytkownicy;
import com.przyklady.model.UzytkownikKontroler;
import javax.faces.model.ArrayDataModel;

public class KomponentSesyjny {

    private ArrayDataModel<Uprawnienia> uprawnienia;

    public ArrayDataModel<Uprawnienia> getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(ArrayDataModel<Uprawnienia> uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    private ArrayDataModel<Uzytkownicy> uzytkownicy;

    public ArrayDataModel<Uzytkownicy> getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(ArrayDataModel<Uzytkownicy> uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }

    public KomponentSesyjny() {
        updateUzytkownicy();
        updateUprawnienia();
    }

    public final void updateUzytkownicy() {
        UzytkownikKontroler uzytkownicyKontroler = new UzytkownikKontroler();
        uzytkownicy = new ArrayDataModel<>(uzytkownicyKontroler.getUzytkownicy());
    }

    public final void updateUprawnienia() {
        UprawnieniaKontroler uprawnieniaKontroler = new UprawnieniaKontroler();
        uprawnienia = new ArrayDataModel<>(uprawnieniaKontroler.getUprawnienia());
    }

    public void deleteUser() {
        Uzytkownicy u = uzytkownicy.getRowData();
        UzytkownikKontroler uzytkownicyKontroler
                = new UzytkownikKontroler();
        uzytkownicyKontroler.usunUzytkownika(u);
        updateUzytkownicy();
    }
    Uzytkownicy uzytkk;
    boolean edycja;

    public String editUser() {
        edycja = true;
        uzytkk = uzytkownicy.getRowData();
        return "Edycja";
    }

    public String addUser() {
        edycja = false;
        uzytkk = new Uzytkownicy();
        return "Edycja";
    }

    public Uzytkownicy getUzytkk() {
        return uzytkk;
    }

    public void setUzytkk(Uzytkownicy uzytkk) {
        this.uzytkk = uzytkk;
    }

    public String save() {
        UzytkownikKontroler uzytkownicyKontroler
                = new UzytkownikKontroler();
        if (edycja) {
            uzytkownicyKontroler.aktualizujUzytkownika(uzytkk);
        } else {
            uzytkownicyKontroler.dodajUzytkownika(uzytkk);
        }
        updateUzytkownicy();
        return "Index";
    }

    public void deleteCredential() {
        Uprawnienia u = uprawnienia.getRowData();
        UprawnieniaKontroler uprawnienieKontroler = new UprawnieniaKontroler();
        uprawnienieKontroler.usunUprawnienie(u);
        updateUprawnienia();
    }
    Uprawnienia uprawnieniek;
    boolean editCredentials;

    public Uprawnienia getUprawnieniek() {
        return uprawnieniek;
    }

    public void setUprawnienia(Uprawnienia uprawniniek) {
        this.uprawnieniek = uprawnieniek;
    }

    public String editCredential() {
        editCredentials = true;
        uprawnieniek = uprawnienia.getRowData();
        return "EdycjaUprawnien";
    }

    public String addCredential() {
        editCredentials = false;
        uprawnieniek = new Uprawnienia();
        return "EdycjaUprawnien";
    }

    public String saveCredentials() {
        UprawnieniaKontroler uprawnieniaKontroler
                = new UprawnieniaKontroler();
        if (editCredentials) {
            uprawnieniaKontroler.aktualizujUprawnienie(uprawnieniek);
        } else {
            uprawnieniaKontroler.dodajUprawnienie(uprawnieniek);
        }
        updateUprawnienia();
        return "Index";
    }

}
