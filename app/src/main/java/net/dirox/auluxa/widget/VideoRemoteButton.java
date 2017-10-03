package net.dirox.auluxa.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

import net.dirox.auluxa.utils.Polygon.Polygon;

/**
 * Created by an on 6/23/2017.
 */

public class VideoRemoteButton extends View {

    protected final float TEXT_SIZE_IN_SP = 14;
    protected final float LINE_RADIUS = 30;
    protected float LINE_WIDTH = 4;

    protected Button mButtonType;
    protected int mViewSize;
    protected boolean mIsFocus;

    protected Path mBorderPath;
    protected Polygon mBorderPolygon; // to check constain point
    protected Paint mBorderLinePaint;

    protected Point locationOnParent;

    public VideoRemoteButton(Context context, Button button) {
        super(context);
        mButtonType = button;
        mIsFocus = false;

        CornerPathEffect cornerPathEffect = new CornerPathEffect(LINE_RADIUS);
        mBorderLinePaint = new Paint();
        mBorderLinePaint.setAntiAlias(true);
        mBorderLinePaint.setColor(Color.BLACK);
        mBorderLinePaint.setStyle(Paint.Style.STROKE);
        mBorderLinePaint.setStrokeWidth(LINE_WIDTH);
        mBorderLinePaint.setPathEffect(cornerPathEffect);

        mBorderPath = new Path();
        locationOnParent = new Point(0, 0);
    }

    public void setLocationOnParent(int x, int y) {
        locationOnParent.set(x, y);
    }

    protected boolean checkContainPoint(float x, float y) {
        if (mBorderPolygon == null) {
            return false;
        }

        mIsFocus = mBorderPolygon.contains(new net.dirox.auluxa.utils.Polygon.Point(x - locationOnParent.x, y - locationOnParent.y));
        invalidate();
        return mIsFocus;
    }

    public void onTouchUp() {
        mIsFocus = false;
        invalidate();
    }

    public Button getButton() {
        return mButtonType;
    }

    public String getButtonText(Button button) {
        switch (button) {
            case VOL_UP:
                return "VOL +";
            case VOL_DOWN:
                return "VOL -";
            case CH_UP:
                return "CH +";
            case CH_DOWN:
                return "CH -";
            case CENTRE:
                return "CENTRE";
            default:
                return "";
        }
    }

    public Path getPath(Button button, int width, int height, int pathWidth) {
        int pathHalfWidth = pathWidth / 2;

        Path _path = new Path();
        switch (button) {
            case VOL_UP:
                _path.moveTo(pathHalfWidth, pathHalfWidth);
                _path.lineTo(pathHalfWidth, height - pathHalfWidth);
                _path.lineTo(width - pathHalfWidth, pathHalfWidth);

                mBorderPolygon = Polygon.Builder()
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(pathHalfWidth, pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(pathHalfWidth, height - pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(width - pathHalfWidth, pathHalfWidth))
                        .build();
                break;

            case VOL_DOWN:
                _path.moveTo(pathHalfWidth, pathHalfWidth);
                _path.lineTo(pathHalfWidth, height - pathHalfWidth);
                _path.lineTo(width - pathHalfWidth, width - pathHalfWidth);

                mBorderPolygon = Polygon.Builder()
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(pathHalfWidth, pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(pathHalfWidth, height - pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(width - pathHalfWidth, width - pathHalfWidth))
                        .build();
                break;

            case CH_UP:
                _path.moveTo(pathHalfWidth, pathHalfWidth);
                _path.lineTo(width - pathHalfWidth, height - pathHalfWidth);
                _path.lineTo(width - pathHalfWidth, pathHalfWidth);

                mBorderPolygon = Polygon.Builder()
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(pathHalfWidth, pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(width - pathHalfWidth, height - pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(width - pathHalfWidth, pathHalfWidth))
                        .build();
                break;

            case CH_DOWN:
                _path.moveTo(pathHalfWidth, height - pathHalfWidth);
                _path.lineTo(width - pathHalfWidth, height - pathHalfWidth);
                _path.lineTo(width - pathHalfWidth, pathHalfWidth);

                mBorderPolygon = Polygon.Builder()
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(pathHalfWidth, height - pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(width - pathHalfWidth, height - pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(width - pathHalfWidth, pathHalfWidth))
                        .build();
                break;

            case CENTRE:
                _path.moveTo(width / 2, pathHalfWidth);
                _path.lineTo(width - pathHalfWidth, height / 2);
                _path.lineTo(width / 2, height - pathHalfWidth);
                _path.lineTo(pathHalfWidth, height / 2);
                _path.lineTo(width / 2, pathHalfWidth);

                mBorderPolygon = Polygon.Builder()
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(width / 2, pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(width - pathHalfWidth, height / 2))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(width / 2, height - pathHalfWidth))
                        .addVertex(new net.dirox.auluxa.utils.Polygon.Point(pathHalfWidth, height / 2))
                        .build();
                break;
        }
        _path.close();
        return _path;
    }

    public enum Button {
        NONE("NONE"),
        VOL_UP("VOL_UP"), VOL_DOWN("VOL_DOWN"), CH_UP("CH_UP"), CH_DOWN("CH_DOWN"),
        CENTRE("CENTRE"),
        CENTRE_ARROW_LEFT("CENTRE_ARROW_LEFT"), CENTRE_ARROW_RIGHT("CENTRE_ARROW_RIGHT"),
        CENTRE_ARROW_UP("CENTRE_ARROW_UP"), CENTRE_ARROW_DOWN("CENTRE_ARROW_DOWN"),
        CENTRE_CENTRE("CENTRE_CENTRE");

        public String buttonType;

        Button(String type) {
            buttonType = type;
        }
    }

    public interface RemoteButtonInteract {
        void onButtonPressed(Button button);
    }
}
