package utillities;


import com.google.common.base.Strings;

public enum BaseConverter {

    Binary,
    Decimal,
    Hexadecimal;

    /**
     * Converst an integer to a hexadecimal string
     * @param val
     * @param base
     * @return
     */
    public static String convertValue(int val, BaseConverter base) {
        /**Java hacks <link>https://stackoverflow.com/a/13357092/9414567<link/>*/
        switch (base){
            case Binary:
                String bin = Integer.toBinaryString(val);
                return Strings.padStart(bin, 32, '0').toUpperCase();
            case Hexadecimal:
                String hex = Integer.toHexString(val & 0xffff);
                return Strings.padStart(hex, 4, '0').toUpperCase();
            default:
                    return String.valueOf(val).toUpperCase();
        }
    }

    public static int converToDecimal(String val, BaseConverter base){
        switch (base){
            case Binary:
                return Integer.parseInt(val, 2);

            case Hexadecimal:
                return Integer.parseInt(val, 16);

            default:
                    return Integer.parseInt(val);
        }

    }

    public static String getHexRegex(){ return "\\p{XDigit}+";}
}
