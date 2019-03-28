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
    public WordFX(short address, short value) {
        this.address = new SimpleStringProperty(BaseConverter.convertValue(address, BaseConverter.Hexadecimal));
        this.value = new SimpleStringProperty(BaseConverter.convertValue(value, BaseConverter.Hexadecimal));
    }
    /**
     *  Short is better for this app, it was adjusted a bit to handle it
     * Generate a single word
     * @param address
     * @param value
     */
    public WordFX(int address, int value){
        this.address = new SimpleStringProperty(BaseConverter.convertValue((short)address, BaseConverter.Hexadecimal));
        this.value = new SimpleStringProperty(BaseConverter.convertValue((short)value, BaseConverter.Hexadecimal));
    }

    public WordFX(int address, String value) {
        this.address = new SimpleStringProperty(BaseConverter.convertValue(address, BaseConverter.Hexadecimal));
        this.value = new SimpleStringProperty(value);
    }

    public String getAddress() {return address.get();}
    public String getValue() {return value.get();}

    public short getAddressInt() {return Short.parseShort(address.get(), 16);}
    //Java can't fucking parse FFF0 EVEN THOUGH THIS IS A VALID SHORT VALUE
    //SO I HAD TO USE THIS HACK
    //WTF JAVA?
    public short getValueShort() {return (short)Integer.parseInt(value.get(),16);}

    public SimpleStringProperty addressProperty() {return address;}
    public SimpleStringProperty valueProperty() {return value;}

    public void setAddress(int val) {address = new SimpleStringProperty(BaseConverter.convertValue(val, BaseConverter.Hexadecimal));}
    public void setValue(short val) {value = new SimpleStringProperty(BaseConverter.convertValue(val, BaseConverter.Hexadecimal));}
    public void setValue(String val) {value = new SimpleStringProperty(val);}
}
