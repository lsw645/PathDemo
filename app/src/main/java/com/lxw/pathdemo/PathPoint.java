package com.lxw.pathdemo;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/14
 * @see //TODO
 * @since JDK 1.8
 */

public class PathPoint {
    public static  final int MOVE =0;
    public static  final int LINE =1;
    public static  final int CUBIC =2;

    public int operation;
    public float x,y;
    public float x0,y0;
    public float x1,y1;

    private PathPoint(int operation,float x, float y) {
        this.operation=operation;
        this.x = x;
        this.y = y;
    }



    private PathPoint(int operation,float x, float y, float x0, float y0, float x1, float y1) {
        this.operation=operation;
        this.x = x;
        this.y = y;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public static  PathPoint moveTo(float x ,float y){
        return  new PathPoint(MOVE,x,y);
    }

    public static  PathPoint lineTo(float x,float y){
        return new PathPoint(LINE,x,y);
    }

    public static PathPoint cubicTo(float x, float y, float x0, float y0, float x1, float y1){
        return  new PathPoint(CUBIC,x,  y, x0, y0,  x1, y1);
    }


}
