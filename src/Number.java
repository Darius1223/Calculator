public class Number {
    String roman;
    int arabic;
    String type = "";

    Number() {
        roman = "";
        arabic = 0;

        type = "arabic";
    }

    Number(int number) {
        roman = arabicToRoman(number);
        arabic = number;

        type = "arabic";
    }

    Number(String number) {
        try {
            if (isArabic(number)) {
                arabic = Integer.parseInt(number);
                roman = arabicToRoman(Integer.parseInt(number));
                isValid();
                type = "arabic";
            } else {
                if (isRoman(number)) {
                    roman = number;
                    arabic = romanToArabic(number);
                    isValid();
                    type = "roman";
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.printf("Error! '%s' is not number!", number);
            System.exit(-1);
        }
    }

    Number(int number, String type) {
        roman = arabicToRoman(number);
        arabic = number;

        this.type = type;
    }

    private void isValid() {
        try {
            if (arabic < 1 || arabic > 10) {
                throw new Error();
            }
        } catch (Error ValueDoesNotInRange) {
            System.out.printf("ERROR: The value '%s' does not meet in range [1; 10]", arabic);
            System.exit(-1);
        }
    }


    @Override
    public String toString() {
        if (type.equals("arabic")) {
            return "" + arabic;
        } else {
            return roman;
        }
    }

    // проверка на принадлежность к арабским числам
    boolean isArabic(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    // проверка на принадлежность к римским числам
    boolean isRoman(String number) {
        for (String sym : number.split("")
        ) {
            switch (sym) {
                case "I", "V", "X", "L", "C":
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    int romanToArabic(String number) {
        int result = 0;

        try {
            result = switch (number) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> throw new IllegalArgumentException();
            };
        } catch (IllegalArgumentException e) {
            System.out.printf("ERROR: The value '%s' does not meet in range", number);
            System.exit(-1);
        }
        return result;
    }

    String arabicToRoman(int number) {
        if (number == 0) {
            return "Zero (0)";
        }

        int[] arabicDict = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanDict = "M CM D CD C XC L XL X IX V IV I".split(" ");
        StringBuilder result = new StringBuilder();

        if (number < 0) {
            number = number * (-1);
            result = new StringBuilder("-");
        }
        for (int i = 0; i < arabicDict.length; i++) {
            for (int j = 0; j < (int) number / arabicDict[i]; j++) {
                result.append(romanDict[i]);
            }
            number = number % arabicDict[i];
        }


        return result.toString();
    }


    int add(Number number) {
        return arabic + number.arabic;
    }

    int mul(Number number) {
        return arabic * number.arabic;
    }

    int sub(Number number) {
        return arabic - number.arabic;
    }

    int div(Number number) {
        return arabic / number.arabic;
    }
}
