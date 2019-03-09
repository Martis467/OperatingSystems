package utillities;


import com.google.common.base.Strings;

public enum BaseConverter {

    Binary,
    Decimal,
    Hexadecimal;

    public String convertValue(int val, BaseConverter base)
    {
        switch (base){
            case Binary:
                String bin = Integer.toBinaryString(val);
                return Strings.padStart(bin, 32, '0');
            case Hexadecimal:
                String hex = Integer.toHexString(val);
                return Strings.padStart(hex, 4, '0');
            default:
                    return String.valueOf(val);
        }
    }
}
