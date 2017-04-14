package com.lxw.pathdemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/14
 * @see //TODO
 * @since JDK 1.8
 */

public class AnimatorPath {
    private List<PathPoint> mPathPointList=new ArrayList<>();

    public  void moveTo(int x ,int y){
            mPathPointList.add(PathPoint.moveTo(x,y));
    }


    public void lineTo(int x,int y){
        mPathPointList.add(PathPoint.lineTo(x,y));
    }

    public void cubicTo(int x, int y, int x0, int y0, int x1, int y1){
        mPathPointList.add( PathPoint.cubicTo(x,  y, x0, y0,  x1, y1))  ;
    }

    public Collection<PathPoint> getPionts(){
        return mPathPointList;
    }
}
