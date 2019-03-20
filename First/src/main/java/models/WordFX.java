package models;

import javafx.beans.property.SimpleStringProperty;
import utillities.BaseConverter;

public class WordFX {

    private SimpleStringProperty address;
    private SimpleStringProperty value;

    /**
     * Generate a single word
     * @param address
     * @param value
     */
    public WordFX(int address, int value) {
        this.address = new SimpleStringProperty(BaseConverter.convertValue(address, BaseConverter.Hexadecimal));
        this.value = new SimpleStringProperty(BaseConverter.convertValue(value, BaseConverter.Hexadecimal));
    }

    public WordFX(int address, String value) {
        this.address = new SimpleStringProperty(BaseConverter.convertValue(address, BaseConverter.Hexadecimal));
        this.value = new SimpleStringProperty(value);
    }

    public String getAddress() {return address.get();}
    public String getValue() {return value.get();}

    public int getAddressInt() {return Integer.parseInt(address.get(), 16);}
    public int getValueInt() {return Integer.parseInt(value.get(),16);}

    public SimpleStringProperty addressProperty() {return address;}
    public SimpleStringProperty valueProperty() {return value;}

    public void setAddress(int val) {address = new SimpleStringProperty(BaseConverter.convertValue(val, BaseConverter.Hexadecimal));}
    public void setValue(int val) {value = new SimpleStringProperty(BaseConverter.convertValue(val, BaseConverter.Hexadecimal));}
}
