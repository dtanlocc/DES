public class DataEncryptionStandard {
    String plainText;
    String key;

    //hoan vi ban dau
    int[] IP = {   57, 49, 41, 33, 25, 17, 9, 1,
                            59, 51, 43, 35, 27, 19, 11, 3,
                            61, 53, 45, 37, 29, 21, 13, 5,
                            63, 55, 47, 39, 31, 23, 15, 7,
                            56, 48, 40, 32, 24, 16, 8, 0,
                            58, 50, 42, 34, 26, 18, 10, 2,
                            60, 52, 44, 36, 28, 20, 12, 4,
                            62, 54, 46, 38, 30, 22, 14, 6 };
    //hoan vi cuoi cung
    int[] IP1 = {    39, 7, 47, 15, 55, 23, 63, 31,
                                    38, 6, 46, 14, 54, 22, 62, 30,
                                    37, 5, 45, 13, 53, 21, 61, 29,
                                    36, 4, 44, 12, 52, 20, 60, 28,
                                    35, 3, 43, 11, 51, 19, 59, 27,
                                    34, 2, 42, 10, 50, 18, 58, 26,
                                    33, 1, 41, 9, 49, 17, 57, 25,
                                    32, 0, 40, 8, 48, 16, 56, 24 };

    int[] PC1 = {   56, 48, 40, 32, 24, 16, 8,
                    0, 57, 49, 41, 33, 25, 17,
                    9, 1, 58, 50, 42, 34, 26,
                    18, 10, 2, 59, 51, 43, 35,
                    62, 54, 46, 38, 30, 22, 14,
                    6, 61, 53, 45, 37, 29, 21,
                    13, 5, 60, 52, 44, 36, 28,
                    20, 12, 4, 27, 19, 11, 3 };

    int[] PC2 = {   13, 16, 10, 23, 0, 4, 2, 27,
                    14, 5, 20, 9, 22, 18, 11, 3,
                    25, 7, 15, 6, 26, 19, 12, 1,
                    40, 51, 30, 36, 46, 54, 29, 39,
                    50, 44, 32, 47, 43, 48, 38, 55,
                    33, 52, 45, 41, 49, 35, 28, 31 };

    int[] expand = {    31, 0, 1, 2, 3, 4,
                        3, 4, 5, 6, 7, 8,
                        7, 8, 9, 10, 11, 12,
                        11, 12, 13, 14, 15, 16,
                        15, 16, 17, 18, 19, 20,
                        19, 20, 21, 22, 23, 24,
                        23, 24, 25, 26, 27, 28,
                        27, 28, 29, 30, 31, 0 };

    int[] pbox = {  15, 6, 19, 20, 28, 11, 27, 16, //khoi tao pbox
                    0, 14, 22, 25, 4, 17, 30, 9,
                    1, 7, 23, 13, 31, 26, 2, 8,
                    18, 12, 29, 5, 21, 10, 3, 24 };

    int[][][] sbox = {
                    { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                    { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                    { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                    { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } },

                    { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                    { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                    { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                    { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },

                    { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                    { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                    { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                    { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } },

                    { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                    { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                    { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                    { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } },

                    { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                    { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                    { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                    { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },

                    { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                    { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                    { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                    { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },

                    { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                    { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                    { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                    { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },

                    { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                    { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                    { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                    { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } }
    };
    int[] P = { 15, 6, 19, 20, 28, 11, 27, 16,
                0, 14, 22, 25, 4, 17, 30, 9,
                1, 7, 23, 13, 31, 26, 2, 8,
                18, 12, 29, 5, 21, 10, 3, 24 };

    int[] shiftLeftBits = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
    DataEncryptionStandard(String text, String key){
        this.plainText = text;
        this.key = key;
//        System.out.println(encrypt(text,key));
    }


    public String hextoBin(String hexa){ //convert hex to bin
        String result = "";
        int n = hexa.length()*4;
        result = Long.toBinaryString(Long.parseUnsignedLong(hexa,16));
        while(result.length() < n) //de buoc ket qua tra ve so ki tu *4
            result = "0" + result;
        return result;
    }

    public String binToHex(String bin){ //convert bin to hex
        String result = "";
        int n = bin.length()/4;
        result = Long.toHexString(Long.parseUnsignedLong(bin,2));
        while(result.length() < n)
            result = "0" + result;
        result = result.toUpperCase();
        return result;
    }
    public String leftCircularShift(String temp, int numBits){
        String output ="";
        temp = hextoBin(temp);
        output = temp.substring(numBits) + temp.substring(0,numBits);
        output = binToHex(output);
        return output;
    }

String permutation(int[] sequence, String input)
{
    String output = "";
    input = hextoBin(input);
    for (int i = 0; i < sequence.length; i++)
        output += input.charAt(sequence[i]);
    output = binToHex(output);
    return output;
}

    // xor 2 hexadecimal strings
    String xor(String a, String b)
    {
        // hexadecimal to decimal(base 10)
        long t_a = Long.parseUnsignedLong(a, 16);
        // hexadecimal to decimal(base 10)
        long t_b = Long.parseUnsignedLong(b, 16);
        // xor
        t_a = t_a ^ t_b;
        // decimal to hexadecimal
        a = Long.toHexString(t_a);
        // prepend 0's to maintain length
        while (a.length() < b.length())
            a = "0" + a;
        return a;
    }



    // preparing 16 keys for 16 rounds
    String[] getKeys(String key)
    {
        String keys[] = new String[16];
        // first key permutation
        key = permutation(PC1, key);
        for (int i = 0; i < 16; i++) {
            key = leftCircularShift(
                    key.substring(0, 7), shiftLeftBits[i])
                    + leftCircularShift(key.substring(7, 14),
                    shiftLeftBits[i]);
            // second key permutation
            keys[i] = permutation(PC2, key);
        }
        return keys;
    }

    // s-box lookup
    String sBox(String input)
    {
        String output = "";
        input = hextoBin(input);
        for (int i = 0; i < 48; i += 6) {
            String temp = input.substring(i, i + 6);
            int num = i / 6;
            int row = Integer.parseInt(
                    temp.charAt(0) + "" + temp.charAt(5), 2);
            int col = Integer.parseInt(
                    temp.substring(1, 5), 2);
            output += Integer.toHexString(
                    sbox[num][row][col]);
        }
        return output;
    }

    String round(String input, String key, int num)
    {
        // fk
        String left = input.substring(0, 8);
        String temp = input.substring(8, 16);
        String right = temp;
        // Expansion permutation
        temp = permutation(expand, temp);
        // xor temp and round key
        temp = xor(temp, key);
        // lookup in s-box table
        temp = sBox(temp);
        // Straight D-box
        temp = permutation(pbox, temp);
        // xor
        left = xor(left, temp);
        System.out.println("Round "
                + (num + 1) + " "
                + right.toUpperCase()
                + " " + left.toUpperCase() + " "
                + key.toUpperCase());

        // swapper
        return right + left;
    }

    String encrypt(String plainText, String key)
    {
        int i;
        plainText = binToHex(plainText);
        System.out.println("plaintext : "+plainText);
        // get round keys
        key = binToHex(key);
        String keys[] = getKeys(key);

        // IP(M)
        plainText = permutation(IP, plainText);
        System.out.println(
                "IP(M) =  "
                        + plainText.toUpperCase());
        System.out.println(
                "       L0="
                        + plainText.substring(0, 8).toUpperCase()
                        + " R0="
                        + plainText.substring(8, 16).toUpperCase() + "\n");


        System.out.println("Round    "+"L\t\t R\t\t   key");
        //run 16 rounds
        for (i = 0; i < 16; i++) {
            plainText = round(plainText, keys[i], i);
        }

        // 32-bit swap
        plainText = plainText.substring(8, 16)
                + plainText.substring(0, 8);

        // IP^-1
        plainText = permutation(IP1, plainText);
        return plainText;
    }
    String decrypt(String plainText, String key)
    {
        int i;
//        plainText = binToHex(plainText);
        System.out.println("plaintext : "+plainText);
        key = binToHex(key);
        // get round keys
        String keys[] = getKeys(key);

        // initial permutation
        plainText = permutation(IP, plainText);
        System.out.println(
                "IP(M) =  "
                        + plainText.toUpperCase());
        System.out.println(
                "       L0="
                        + plainText.substring(0, 8).toUpperCase()
                        + " R0="
                        + plainText.substring(8, 16).toUpperCase() + "\n");

        // 16-rounds
        System.out.println("Round    "+"L\t\t R\t\t   key");
        for (i = 15; i > -1; i--) {
            plainText = round(plainText, keys[i], 15 - i);
        }

        // 32-bit swap
        plainText = plainText.substring(8, 16)
                + plainText.substring(0, 8);
        plainText = permutation(IP1, plainText);
        return plainText;
    }

    public static void main(String[] args) {
        String text = "0111010100001110101000011101010000111010100001110101000011101011";
        String key = "0111010100101110101000011101011000111010100001110101010011101011";
        DataEncryptionStandard test = new DataEncryptionStandard(text,key);
        System.out.println("ENCRYPT");
        String cipherText = test.encrypt(text,key);
        System.out.println("Ciper text = "+cipherText+"\n---------------------------------------------------------------\n");
        System.out.println("DECRYPT");
        System.out.println("Ciper text = "+ test.decrypt(cipherText,key));
    }
}
