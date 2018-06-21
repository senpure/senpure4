package com.senpure.base.util;

import java.util.*;


public class HashArraySenstiveWord {

    public static HashArraySenstiveWord getInstance() {
        HashArraySenstiveWord senstiveWord = new HashArraySenstiveWord();
        senstiveWord.init();
        return senstiveWord;
    }

    private HashArraySenstiveWord() {
    }

    private void init() {
        for (char i = 32; i < 256; i++) {
            addDisturbChar(i);
        }
        addDisturbChar('》');
        addDisturbChar('《');
        addDisturbChar('？');
    }

    private class Node {
        char value;
        boolean end = false;
        Node[] nodes;

        public Node(char value) {
            this.value = value;
        }

        private Node check(char value, boolean add) {
            if (nodes == null) {
                if (add) {
                    Node node = new Node(value);
                    nodes = new Node[]{node};
                    return node;
                }
                return null;
            }
            for (Node node : nodes) {
                if (node.value == value) {
                    return node;
                }
            }
            if (add) {
                nodes = Arrays.copyOf(nodes, nodes.length + 1);
                Node node = new Node(value);
                nodes[nodes.length - 1] = node;
                return node;
            }
            return null;
        }
    }

    private class Replace {
        int start, end;
    }

    private class Result {
        List<Replace> replaces;
        boolean validate;

        public Result(boolean validate) {
            this.validate = validate;
        }

        public Result() {
        }
    }

    private HashMap<Character, Node> nodes = new HashMap(2048);
    private HashSet<Character> disturbs = new HashSet<>();

    /**
     * @param word 要屏蔽的词语
     */
    public void addSenstiveWord(String word) {
        char value = word.charAt(0);
        Node node = nodes.get(value);
        if (node == null) {
            node = new Node(value);
            nodes.put(value, node);
        }
        for (int i = 1; i < word.length(); i++) {
            node = node.check(word.charAt(i), true);
        }
        node.end = true;
    }

    public void addDisturbChar(Character charcter) {
        disturbs.add(charcter);
    }

    public boolean validate(String str) {
        if (StringUtil.isNullOrEmpty(str)) {
            return true;
        }
        return filter(str, false).validate;
    }

    public String filter(String str) {
        Result result = filter(str, true);
        if (result.validate) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        for (Replace r : result.replaces) {
            int readyCount = r.end - r.start;
            int count = 0;
            while (count++ < readyCount) {
                sb.replace(r.start, ++r.start, "*");
            }
        }
        return sb.toString();
    }

    private Result filter(String str, boolean deep) {
        if (StringUtil.isNullOrEmpty(str)) {
            return new Result(true);
        }
        List<Replace> allReplaces = new ArrayList<>();
        List<Replace> replaces = new ArrayList<>();
        int start = 0;
        int wordStart = 0;
        Node parent = null;
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            char value = sb.charAt(i);
            // System.out.println(value);
            if (parent != null) {
                Node current = parent.check(value, false);
                //没有后续的词
                if (current == null) {
                    //排除干扰字符
                    if (disturbs.contains(value)) {
                        Replace replace = new Replace();
                        replace.start = start;
                        replace.end = i;
                        //  System.out.println("end:"+value);
                        replaces.add(replace);
                        start = i + 1;
                        //  System.out.println("start:"+value);
                        continue;
                    }
                    //找到要过滤的词语
                    else if (parent.end) {
                        Replace replace = new Replace();
                        replace.start = start;
                        replace.end = i;
                        //  System.out.println("end:"+value);
                        replaces.add(replace);
                        allReplaces.addAll(replaces);
                        replaces.clear();
                        parent = null;
                        if (deep) {
                            continue;
                        } else {
                            break;
                        }
                    } else {//不是要过滤的词语

                        replaces.clear();
                        i = wordStart;
                        parent = null;
                        continue;
                    }
                } else {
                    parent = current;
                }
            } else {
                parent = nodes.get(value);
                if (parent != null) {
                    start = i;
                    //  System.out.println("start:"+value);
                    wordStart = i;
                }
            }

        }
        if (parent != null && parent.end) {
            Replace replace = new Replace();
            replace.start = start;
            replace.end = sb.length();
            replaces.add(replace);
            allReplaces.addAll(replaces);
        }
        Result result = new Result();
        if (allReplaces.isEmpty()) {
            result.validate = true;
        } else {
            result.validate = false;
            result.replaces = allReplaces;
        }
        return result;
    }

    public static void main(String[] args) {

        HashArraySenstiveWord senstiveWord = HashArraySenstiveWord.getInstance();
        String[] senstive = {"毛泽东", "草拟吗", "操你妈", "共产党"};
        System.out.print("屏蔽词：");
        for (String s : senstive) {
            System.out.print(s + " ");
            senstiveWord.addSenstiveWord(s);
        }
        System.out.println("");
        String str = "毛 泽 东天天向上，共?？产-《》党外岁，操你妈的产品";
        System.out.println("原字符：" + str);
        System.out.println("和谐后：" + senstiveWord.filter(str));
        System.out.println("和谐后：" + senstiveWord.filter(str).replace("*","他"));
    }
}
