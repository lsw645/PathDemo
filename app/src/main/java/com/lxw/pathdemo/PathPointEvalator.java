package com.lxw.pathdemo;

import android.animation.TypeEvaluator;

/**
 * description... //TODO
 *
 * @author lsw
 * @version 1.0, 2017/4/14
 * @see //TODO
 * @since JDK 1.8
 */

public class PathPointEvalator implements TypeEvaluator<PathPoint> {
    @Override
    public PathPoint evaluate(float fraction, PathPoint startValue, PathPoint endValue) {
        float x = 0;
        float y = 0;
        switch (endValue.operation) {
            case PathPoint.MOVE:
                x = endValue.x;
                y = endValue.y;
                break;
            case PathPoint.LINE:
                x = startValue.x + (endValue.x - startValue.x) * fraction;
                y = startValue.y + (endValue.y - startValue.y) * fraction;
                break;
            case PathPoint.CUBIC:
                float t = 1 - fraction;
                x = t * t * t * startValue.x +
                        3 * t * t * fraction * endValue.x0 +
                        3 * t * fraction * fraction * endValue.x1 +
                        fraction * fraction * fraction * endValue.x;
                y = t * t * t * startValue.y +
                        3 * t * t * fraction * endValue.y0 +
                        3 * t * fraction * fraction * endValue.y1 +
                        fraction * fraction * fraction * endValue.y;
                break;
        }

        return PathPoint.moveTo(x, y);
    }
}
