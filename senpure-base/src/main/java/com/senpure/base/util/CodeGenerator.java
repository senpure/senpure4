package com.senpure.base.util;

import java.util.Random;


public class CodeGenerator {
    /**
     * true为可以重复
     */
    private boolean repeat;
    /**
     * true 为大小写敏感
     */
    private boolean sensitive;
    private SOURCESTYLE sourceStyle;
    private int codeLength;
    private String code;

    private Random random;
    private Offset[] offset;
    private static final int DEFAULT_CODE_LENGTH = 4;

    public static enum SOURCESTYLE {
        NUMBER, LOWER_LETTER, UPPER_LETTER, LETTER, NUMBER_LOWER_LETTER, NUMBER_UPPER_LETTER, NUMBER_LETTER
    }

    ;
    private static final char[] SOURCE = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};


    private CodeGenerator() {
        init(SOURCESTYLE.NUMBER_LETTER, true, false, 4);
    }

    private CodeGenerator(SOURCESTYLE sourceStyle, boolean repeat,
                          boolean sensitive, int codeLength) {
        init(sourceStyle, repeat, sensitive, codeLength);
    }

    public static CodeGenerator getInstance() {
        return new CodeGenerator();

    }

    public static CodeGenerator getInstance(SOURCESTYLE sourceStyle,
                                            boolean repeat, boolean sensitive, int codeLength) {
        if (codeLength < 1 || codeLength > 10) {
            codeLength = DEFAULT_CODE_LENGTH;
        }
        return new CodeGenerator(sourceStyle,
                repeat, sensitive, codeLength);

    }

    public String getCode() {
        return this.code;
    }

    public String refresh() {
        generateCode();
        return code;
    }

    public boolean isEquals(String code) {
        if (sensitive) {
            return this.code.equals(code);
        }
        if (code == null) {
            return false;
        }
        return this.code.toUpperCase().equals(code.toUpperCase());
    }

    private void init(SOURCESTYLE sourceStyle, boolean repeat,
                      boolean sensitive, int codeLength) {
        random = new Random();

        this.sensitive = sensitive;
        this.sourceStyle = sourceStyle;
        this.repeat = repeat;
        this.codeLength = codeLength;

        calculateOffset();

        generateCode();

    }

    private void generateCode() {
        StringBuilder sb = new StringBuilder();

        int ol = offset.length;

        char c = '-';
        for (int i = 0; i < codeLength; i++) {
            boolean next = false;
            do {
                Offset of = offset[random.nextInt(ol)];

                c = SOURCE[random.nextInt(of.end - of.start) + of.start];
                if (!repeat) {
                    int sbl = sb.length();
                    for (int j = 0; j < sbl; j++) {
                        char before = sb.charAt(j);
                        if (sensitive) {
                            if (before == c) {
                                next = true;
                                break;
                            }

                        } else {
                            if (Character.toLowerCase(c) == Character
                                    .toLowerCase(before)) {
                                next = true;
                                break;
                            }

                        }
                    }
                }
            } while (next);
            sb.append(c);

        }

        this.code = sb.toString();
    }


    private void calculateOffset() {
        switch (sourceStyle) {
            case NUMBER:

                offset = new Offset[1];
                offset[0] = new Offset(0, 10);
                break;
            case LOWER_LETTER:

                offset = new Offset[1];
                offset[0] = new Offset(10, 36);
                break;
            case UPPER_LETTER:

                offset = new Offset[1];
                offset[0] = new Offset(36, 62);
                break;

            case LETTER:

                offset = new Offset[1];
                offset[0] = new Offset(10, 62);
                break;

            case NUMBER_LOWER_LETTER:

                offset = new Offset[1];
                offset[0] = new Offset(0, 36);
                break;
            case NUMBER_UPPER_LETTER:

                offset = new Offset[2];
                offset[0] = new Offset(0, 10);
                offset[1] = new Offset(36, 62);
                break;
            case NUMBER_LETTER:

                offset = new Offset[1];
                offset[0] = new Offset(0, 62);
                break;
            default:
                offset = new Offset[1];
                offset[0] = new Offset(0, 62);
                break;
        }
    }

    private class Offset {

        private int start;
        private int end;

        public Offset(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }

    }

}
