package rowley.lessonslearnedexamples.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;

import rowley.lessonslearnedexamples.R;

public class TintableImageView extends ImageView {
    private ColorStateList stateList;
    public static final int COLOR_FILTER_COLOR_TAG_ID = -10;

    public TintableImageView(Context context) {
        super(context);
        init(context, null);
    }

    public TintableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TintableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TintableImageView);
        stateList = a.getColorStateList(R.styleable.TintableImageView_srcColorState);
        a.recycle();
    }

    public void setDrawableColorStateList(ColorStateList sateList) {
        this.stateList = sateList;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if(stateList != null) {
            int color = stateList.getColorForState(getDrawableState(), Color.TRANSPARENT);
            setColorFilter(color);
            setTag(COLOR_FILTER_COLOR_TAG_ID, color);
        }
    }
}
