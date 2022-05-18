package ru.mirea.mahmoud.b.i.fregmentviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    public MutableLiveData<String> selectedItem = new MutableLiveData<String>();
    public void setData(String itm){
        selectedItem.setValue(itm);
    }
    public LiveData<String> getSelectedItem(){
        return selectedItem;
    }
}
