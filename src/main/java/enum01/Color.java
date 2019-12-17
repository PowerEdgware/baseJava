//// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
//// Jad home page: http://www.geocities.com/kpdus/jad.html
//// Decompiler options: packimports(3) 
//// Source File Name:   EnumDemo.java
//
//package enum01;
//
//
//final class Color extends Enum
//{
//
//    private Color(String s, int i)
//    {
//        super(s, i);
//    }
//
//    public static Color[] values()
//    {
//        Color acolor[];
//        int i;
//        Color acolor1[];
//        System.arraycopy(acolor = ENUM$VALUES, 0, acolor1 = new Color[i = acolor.length], 0, i);
//        return acolor1;
//    }
//
//    public static Color valueOf(String s)
//    {
//        return (Color)Enum.valueOf(enum01/Color, s);
//    }
//
//    public static final Color RED;
//    public static final Color GREEN;
//    public static final Color BLUE;
//    private static final Color ENUM$VALUES[];
//
//    static 
//    {
//        RED = new Color("RED", 0);
//        GREEN = new Color("GREEN", 1);
//        BLUE = new Color("BLUE", 2);
//        ENUM$VALUES = (new Color[] {
//            RED, GREEN, BLUE
//        });
//    }
//}
